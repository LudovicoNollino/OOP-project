package it.univpm.TwitterHashtagAnalytics.service;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

import java.io.IOException;
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
import org.json.simple.parser.ParseException;

public class APIControl implements APIControl_Interface{
	
	//Parametri
	/*
	 * @param hashes è un array di hashtag che contiene tutti gli array che l'utente desidera ricercare
	 * @param lang è il linguaggio in cui si vogliono ricercare i post (di default su italiano)
	 * @param count è il conteggio di post che vogliono essere visualizzati dall'utente (di default su 10)
	 * @param linkBase corrisponde alla base del link per collegarsi alla api al quale andremo poi ad aggiungere i campi per effettuare la query come richiesto dall'utente
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
	public String retrieveData() throws IOException, ParseException{
	
		//La variabile inline sarà usata come appoggio per la lettura delle righe del file JSON restituito dall'API
				String inline = "";
			
					URL url = new URL(this.buildQuery());
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					int responsecode = conn.getResponseCode();					
					if(responsecode != 200)
						throw new RuntimeException("HttpResponseCode: " +responsecode);
					else
					{
						//Lo Scanner leggerà i dati dallo stream JSON
						Scanner sc = new Scanner(conn.getInputStream());
						while(sc.hasNext())
						{
							inline+=sc.nextLine();
						}
						sc.close();
					}
					
					//Il JSONParser prende tutti i dati restituiti e li scompone in coppie di chiavi e valori
					JSONParser parse = new JSONParser();
					JSONObject jobj = (JSONObject)parse.parse(inline);
					//Viene popolato il JSONArray con i JSONObject che contengono i dati di livello 1
					JSONArray jsonarr_1 = (JSONArray) jobj.get("statuses");
				
					//Salvataggio informazioni sui tweet
					//Viene dichiarato un DateTimeFormatter per la conversione della data dal formato del tweet al formato yyyy-mm-dd
					DateTimeFormatter form = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
					
					for(int i=0;i<jsonarr_1.size();i++){
						
						JSONObject tmp = (JSONObject) jsonarr_1.get(i);
						String date = (String) tmp.get("created_at");
						//Nelle due righe successive viene preso il valore della stringa "created_at" e viene effettuato il parsing della data
						LocalDateTime localdatetime = LocalDateTime.parse(date, form);
						date = localdatetime.toLocalDate().toString();
						Long id = (Long) tmp.get("id");
						Long retweet = (Long) tmp.get("retweet_count");
						Long likes = (Long) tmp.get("favorite_count");

						//Viene popolato un JSONArray con i JSONObject che contengono i dati di livello 2 (hashtag)
						JSONObject jsonobj_2 = (JSONObject) tmp.get("entities");
						JSONArray hash = (JSONArray) jsonobj_2.get("hashtags");
						
						ArrayList<String> str_data1 = new ArrayList<String>();
						
						for(int j=0;j<hash.size();j++){
							
						// All'interno di questo ciclo viene preso il contenuto del campo hashtag
						// e viene salvato in formato generalizzato (tutto in minuscolo) per facilitare l'utilizzo delle stringhe successivamente
							JSONObject jsonobj_3 = (JSONObject) hash.get(j);
							String temp = (String) jsonobj_3.get("text");
							str_data1.add(temp.toLowerCase());
						}
						
						Posts newPost = new Posts(date, id, str_data1, retweet, likes);
						tweets.add(newPost);
						
						//Salvataggio informazioni sugli utenti
						JSONObject mtp = (JSONObject) tmp.get("user");
						Long id_utente = (Long) mtp.get("id");
						String name = (String) mtp.get("name");
						Long followers = (Long) mtp.get("followers_count");
						
						Utenti newUtente = new Utenti(id_utente, name, followers);
						users.add(newUtente);
						
						
					}
					conn.disconnect();
					
					return "Salvataggio dei dati avvenuto.";
	}
}