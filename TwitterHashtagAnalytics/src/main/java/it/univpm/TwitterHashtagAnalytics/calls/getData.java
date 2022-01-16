package it.univpm.TwitterHashtagAnalytics.calls;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

//la classe permette di visualizzare dati e metadati

public class getData {
	
	private ArrayList<Posts> tweets;
	private ArrayList<Utenti> users;
	
	//costruttore e metodo per i DATI
	
	public getData(ArrayList<Posts> tweets, ArrayList<Utenti> users) {
		this.tweets = tweets;
		this.users = users;
		
	}
	
	//metodo per la visualizzazione dei dati
	
	public JSONObject showData() {

		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();

		//per ogni tweet viene generato un JSONObject "prop" da mettere dentro un JSONArray "list"
		//contenuto dentro un JSONObject "data" che viene poi ritornato
		for(int k=0; k<tweets.size(); k++) {

			JSONObject tweet = new JSONObject();
			JSONObject prop = new JSONObject();

			tweet.put("created_at",tweets.get(k).getCr_date());
			tweet.put("id",tweets.get(k).getId_tweet());
			tweet.put("hashtags",tweets.get(k).getHashtags());
			tweet.put("favourites_count",tweets.get(k).getLikes());
			tweet.put("retweet_count",tweets.get(k).getRetweets());
			//tweet.put("in_replt_to",tweets.get(k).getReply());
			
			list.add(prop);
			prop.put("tweet",tweet);

			list.add(prop);
		}
		data.put("list",list);
		return data;
	}
	
	
	//costruttore di default e metodo per i METADATI
	
	public getData () {}
	public JSONObject showMetadati () {
		JSONObject metadati = new JSONObject();
		JSONObject prop = new JSONObject();
		JSONArray list = new JSONArray();
		JSONObject tweet = new JSONObject();
		
		tweet.put("created_at","String");
		tweet.put("id","long");
		tweet.put("hashtags","ArrayList<String>");
		tweet.put("favourites_count","int");
		tweet.put("retweet_count","int");
	//	tweet.put("in_reply_to",)
		prop.put("tweet",tweet);

		list.add(prop);
		metadati.put("list",prop);

		return metadati;
	}
		
}

