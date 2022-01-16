/*package it.univpm.TwitterHashtagAnalytics.filters;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

//implemennta la FilterInterface e filtra per hashtag

public class HashtagFilter implements FilterInterface {
	
	private String hashtag;
	private ArrayList<Posts> tweets ;
	private ArrayList<Utenti> users ;
	
	//Costruttore
	
	public HashtagFilter (String hashtag, ArrayList<Posts> tweets, ArrayList<Utenti> users) {
		
		this.hashtag = hashtag;
		this.tweets = tweets;
		this.users = users;
				
	}
	
	//Metodo che memorizza i post con l'hahstag inserito come filtro
	
	@Override
	public JSONObject filter () {
		
		JSONObject hash = new JSONObject();
		JSONObject list = new JSONObject();
				
		for (int i=0; i<tweets.size(); i++) {
			
			JSONObject post = null;
			
			
			
			
		}
	}
	

}
*/