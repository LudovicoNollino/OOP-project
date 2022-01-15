package it.univpm.TwitterHashtagAnalytics.calls;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;

public class getData {
	
	private ArrayList<Posts> tweets;
	
	public getData(ArrayList<Posts> tweets) {
		this.tweets = tweets;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject seeData() {

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
			prop.put("tweet",tweet);

			list.add(prop);
		}
		data.put("list",list);
		return data;
	}
}
