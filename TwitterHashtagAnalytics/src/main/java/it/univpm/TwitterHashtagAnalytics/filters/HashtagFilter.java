package it.univpm.TwitterHashtagAnalytics.filters;


import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.TwitterHashtagAnalytics.model.Posts;

//implemennta la FilterInterface e filtra per hashtag

public class HashtagFilter{
	
	private String hashtag;
	private ArrayList<Posts> tweets ;
	
	//Costruttore
	
	public HashtagFilter (String hashtag, ArrayList<Posts> tweets) {
		
		this.tweets = tweets;
		this.hashtag = hashtag;
				
	}
	
	//Metodo che memorizza i post con l'hahstag inserito come filtro
	
	@SuppressWarnings("unchecked")
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