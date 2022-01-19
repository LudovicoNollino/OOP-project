package it.univpm.TwitterHashtagAnalytics.calls;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APIControl implements APIControl_Interface{
	
	//Parametri
	/*
	 * @param hash è un array di hashtag che contiene tutti gli array che l'utente desidera ricercare
	 * @param lang è il linguaggio in cui si vogliono ricercare i post (di default su italiano)
	 * @param count è il conteggio di post che vogliono essere visualizzati dall'utente (di default su 10)
	 * @param linkBase corrisponde alla base del link per collegarsi alla api al quale andremo poi ad aggiungere i campi per effettuare la query come richiesto dall'utente
	 * @param linkFinale è il link che verrà inoltrato all'API per ottenere i dati sui tweet 
	 */
	
	final static String linkBase ="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?";
	String[] hashes;
	String lang;
	int count;
	
	ArrayList<Posts> tweets = new ArrayList<Posts>();
	ArrayList<Utenti> users = new ArrayList<Utenti>();
	
	//Costruttore
	
	
	public APIControl(String[] hashes, String lang, int count){
		
		this.hashes = hashes;
		this.lang = lang;
		this.count = count;
	}
	
	@Override
	public ArrayList<Posts> getPosts() {return tweets;}
	
	@Override
	public ArrayList<Utenti> getUtenti() {return users;}
	
	//Metodo che crea il link da inoltrare all'API per effettuare la richiesta e ricevere i dati
	
	@Override
	public String buildQuery() throws UnsupportedEncodingException {
			String bld = linkBase;
			
			bld = bld + "lang=" + lang + "&count=" + count;
			
			
			for (int i = 0; i < hashes.length; i++) {
				String hash = this.hashes[i];

				bld += "&q=" + URLEncoder.encode("#" + hash, "UTF-8");
				
			}

			return bld;
			
	}
	
	@Override
	public String retrieveData(){
	
		//inline will store the JSON data streamed in string format
				String inline = "";
			
				try
				{
					URL url = new URL(this.buildQuery());
					//Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					//Set the request to GET or POST as per the requirements
					conn.setRequestMethod("GET");
					//Use the connect method to create the connection bridge
					conn.connect();
					//Get the response status of the Rest API
					int responsecode = conn.getResponseCode();					
					//Iterating condition to if response code is not 200 then throw a runtime exception
					//else continue the actual process of getting the JSON data
					if(responsecode != 200)
						throw new RuntimeException("HttpResponseCode: " +responsecode);
					else
					{
						//Scanner functionality will read the JSON data from the stream
						Scanner sc = new Scanner(conn.getInputStream());
						while(sc.hasNext())
						{
							inline+=sc.nextLine();
						}
						System.out.println(inline);
						//Close the stream when reading the data has been finished
						sc.close();
					}
					
					//JSONParser reads the data from string object and break each data into key value pairs
					JSONParser parse = new JSONParser();
					//Type caste the parsed json data in json object
					JSONObject jobj = (JSONObject)parse.parse(inline);
					//Store the JSON object in JSON array as objects (For level 1 array element)
					JSONArray jsonarr_1 = (JSONArray) jobj.get("statuses");
					
					// tweets info
					//Get data for Results array
					
					DateTimeFormatter form = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
					
					for(int i=0;i<jsonarr_1.size();i++)
					{
						JSONObject tmp = (JSONObject) jsonarr_1.get(i);
						//Store the JSON objects in an array
						String date = (String) tmp.get("created_at");
						LocalDateTime localdatetime = LocalDateTime.parse(date, form);
						date = localdatetime.toLocalDate().toString();
						Long id = (Long) tmp.get("id");
						Long retweet = (Long) tmp.get("retweet_count");
						Long likes = (Long) tmp.get("favorite_count");

						//Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
						JSONObject jsonobj_2 = (JSONObject) tmp.get("entities");
						JSONArray hash = (JSONArray) jsonobj_2.get("hashtags");
						
						ArrayList<String> str_data1 = new ArrayList<String>();
						
						//Get data for the Address Components array
						
						for(int j=0;j<hash.size();j++)
						{
							//Same just store the JSON objects in an array
							//Get the index of the JSON objects and print the values as per the index
							JSONObject jsonobj_3 = (JSONObject) hash.get(j);
							//Store the data as String objects
							String temp = (String) jsonobj_3.get("text");
							str_data1.add(temp);
						}
						
						Posts newPost = new Posts(date, id, str_data1, retweet, likes);
						tweets.add(newPost);
						
						//users info 
						JSONObject mtp = (JSONObject) tmp.get("user");
						Long id_utente = (Long) mtp.get("id");
						String name = (String) mtp.get("name");
						Long followers = (Long) mtp.get("followers_count");
						
						Utenti newUtente = new Utenti(id_utente, name, followers);
						users.add(newUtente);
						
						
					}
					//Disconnect the HttpURLConnection stream
					conn.disconnect();
				}
				catch (UnsupportedEncodingException e) {
					// Restituire JSON con errore specifico
				}
				catch( RuntimeException e ) {
					// Errore dell'API
				}
				catch(Exception e){
					// Restituisce un'eccezione generale ritornata dal codice
				}
				return "Salvataggio dei dati avvenuto.";
			}
	
	}		























/*	try {
		URL url = new URL("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "applications/json");
		
		int response = conn.getResponseCode();
		
		if(conn.getResponseCode() != 200) {
			throw new RuntimeException("httpResponseCode: " + response);
		}
		else {
			StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
            
            System.out.println(informationString);
            
            JSONParser parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
            
            System.out.println(dataObject.get(0));

            JSONObject countryData = (JSONObject) dataObject.get(0);

            System.out.println(countryData.get("woeid"));
		}
	}catch (Exception e) {
        e.printStackTrace();}*/


