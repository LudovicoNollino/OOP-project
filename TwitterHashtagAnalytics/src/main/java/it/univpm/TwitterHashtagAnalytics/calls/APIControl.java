package it.univpm.TwitterHashtagAnalytics.calls;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

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
	String linkFinale;
	String[] hash;
	String lang;
	int count;
	
	ArrayList<Posts> tweets = new ArrayList<Posts>();
	ArrayList<Utenti> users = new ArrayList<Utenti>();
	
	//Costruttore
	
	
	public APIControl(String[] hash, String lang, int count){
		
		for(int i = 0 ; i<hash.length ; i++) {
			
			this.hash[i] = hash[i].replaceAll("#", "%23");
		
		}
		this.lang = lang;
		this.count = count;
	}
	
	@Override
	public ArrayList<Posts> getPosts() {return tweets;}
	
	@Override
	public ArrayList<Utenti> getUtenti() {return users;}
	
	//Metodo che crea il link da inoltrare all'API per effettuare la richiesta e ricevere i dati
	
	@Override
	public String buildQuery() {
		
		for (String hashtag : hash) {
			
			linkFinale = linkBase + "q=" + hashtag + "&";
			
		}
		linkFinale = linkFinale + "lang" + lang + "&count" + count;
		
		return linkFinale;
	}
	
	@Override
	public ArrayList<Posts> retrieveData(){
		
		JSONObject data = new JSONObject();
		
		try {
			//Apro la connessione
			URL url = new URL(this.buildQuery());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "applications/json");
			
			//Memorizzo il codice di risposta del modulo http
			int response = conn.getResponseCode();
			
			if(response != 200) {
				//Connessione non andata a buon fine
				throw new RuntimeException("httpResponseCode :" + response);
			}else {
				//Connessione andata a buon fine vengono letti i dati dallo stream
				InputStream inputStream = conn.getInputStream();
			    BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			    StringBuilder responseStrBuilder = new StringBuilder();

			    String inputStr;
			    while ((inputStr = streamReader.readLine()) != null)
			        responseStrBuilder.append(inputStr);
			    inputStream.close();	
			    
			    data = (JSONObject) JSONValue.parseWithException(streamReader);
			
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONArray statuses = (JSONArray) data.get("statuses");
		//if(statuses.isEmpty()) throw new Exception("ERROR: no tweets found");
		
		for(int i = 0 ; i<statuses.size() ; i++) {
			JSONObject tweet = (JSONObject) statuses.get(i);
		
		//Vengono popolati i campi del model
		String date = (String) tweet.get("created_at");
		Long id = (Long) tweet.get("id");
		int retweet = (int) tweet.get("retweet_count");
		int likes = (int) tweet.get("favorites_count");
		
		JSONObject entities = (JSONObject) tweet.get("entities");
		JSONArray hash = (JSONArray) entities.get("hashtags");
		ArrayList<String> hashtag = new ArrayList<String>();
		for (int j = 0 ; j<hash.size() ; j++) {
				JSONObject content = (JSONObject) hash.get(j);
				String testo = (String) content.get("text");
				hashtag.add(testo);
		}
		
		Posts post = new Posts(date, id, hashtag, retweet, likes);
		tweets.add(post);
		
		}
		
		return tweets;
		
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


