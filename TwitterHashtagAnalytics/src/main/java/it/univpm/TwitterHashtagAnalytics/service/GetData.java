package it.univpm.TwitterHashtagAnalytics.service;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;


/**
 *La classe GetData permette di visualizzare dati e metadati.
 */
public class GetData  {
	
	private ArrayList<Posts> tweets;
	
	private ArrayList<Utenti> users;
	
	
	/**
	 * Costruttore
	 *
	 * @param tweets è la lista dei tweets
	 * @param users è la lista degli utenti
	 */
	public GetData(ArrayList<Posts> tweets, ArrayList<Utenti> users) {
		this.tweets = tweets;
		this.users = users;
		
	}
	
	
	/**
	 * Metodo per la visualizzazione dei dati.
	 *
	 * @return JSONObject
	 */
	@SuppressWarnings(value = { "unchecked" })
	public JSONObject showData() {

		JSONObject data = new JSONObject();
		JSONArray info = new JSONArray();

		//per ogni tweet viene generato un JSONObject "content" da mettere dentro un JSONArray "info"
		//contenuto dentro un JSONObject "data" che viene poi ritornato
		
		for(int j=0; j<tweets.size(); j++) {

			JSONObject tweet = new JSONObject();
			JSONObject content = new JSONObject();
			JSONObject user = new JSONObject();

			tweet.put("created_at",tweets.get(j).getCr_date());
			tweet.put("id",tweets.get(j).getId_tweet());
			tweet.put("hashtags",tweets.get(j).getHashtags());
			tweet.put("likes",tweets.get(j).getLikes());
			tweet.put("retweets",tweets.get(j).getRetweets());
			
			content.put("tweet", tweet);		
			
			user.put("name",users.get(j).getName());
			user.put("id",users.get(j).getId_utente());
			user.put("followers",users.get(j).getFollowers());
			
			content.put("user", user);
			
			info.add(content);
		}
		data.put("Dati su tweet e utenti trovati",info);
		return data;
	}
	
		
	/**
	 * Costruttore di default e metodo per i METADATI.
	 */
	public GetData () {}
	
	/**
	 * Metodo che mostra i metadati.
	 *
	 * @return JSONObject popolato con i campi che descrivono i metadati
	 */
	public JSONObject showMetadati () {
		
		JSONObject metadati = new JSONObject();
		JSONObject content = new JSONObject();
		JSONArray info = new JSONArray();
		JSONObject tweet = new JSONObject();
		JSONObject user = new JSONObject();
		
		tweet.put("date","String");
		tweet.put("id","long");
		tweet.put("hashtags","ArrayList<String>");
		tweet.put("likes","int");
		tweet.put("retweets","int");
		content.put("tweet",tweet);
		
		user.put("name","String");
		user.put("id","long");
		user.put("followers","long");
		content.put("Users", user);
		
		info.add(content);
		metadati.put("Metadati",content);

		return metadati;
	}
		
}

