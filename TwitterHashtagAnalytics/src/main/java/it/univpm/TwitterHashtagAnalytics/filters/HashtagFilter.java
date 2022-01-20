package it.univpm.TwitterHashtagAnalytics.filters;


import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.TwitterHashtagAnalytics.model.Posts;



/**
 * Questa classe effettua il filtraggio sui tweet contenenti l'hashtag scelto scelto
 * 
 * @author Simore Recchia
 * @author Ludovico Nollino
 *
 */

public class HashtagFilter{
	
	/*
	 * Attributi
	 * */
	private String hashtag;
	private ArrayList<Posts> tweets ;
	
	
	/**
	 * Costruttore
	 *
	 * @param hashtag è l'hashtag per il quale si vuole effettuare il filtraggio
	 * @param tweets è la lista di tweet salvata
	 */
	public HashtagFilter (String hashtag, ArrayList<Posts> tweets) {
		
		this.tweets = tweets;
		this.hashtag = hashtag;
				
	}
	
	/*
	 * Questo metodo serve per memorizzare in un JSONObject i tweets con l'hashtag scelto
	 * 
	 * @return  JSONObject contenente la lista dei tweet con l'hashtag selezionato*/
	
	public JSONObject filter () {
		
		JSONObject hash = new JSONObject();
		JSONArray list = new JSONArray();
				
		for (int i=0; i<tweets.size(); i++) {
			
			JSONObject post = null;
			
			if(tweets.get(i).getHashtags() != null) {
				if(tweets.get(i).getHashtags().contains(hashtag)) {
					post = new JSONObject();
					post.put("created_at",tweets.get(i).getCr_date());
					post.put("id",tweets.get(i).getId_tweet());
					post.put("hashtag",tweets.get(i).getHashtags());
				}
			}
				if(post != null) {
					list.add(post);
				}else if (list == null) {
						hash.put("Motivo: l'hashtag inserito non è contenuto in nessun post", null);}
				}
				hash.put("Lista filtrata di tweet che contengono l'hashtag " + hashtag, list);

		return hash;
	}
}