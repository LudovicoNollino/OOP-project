package it.univpm.TwitterHashtagAnalytics.filters;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

public class DailyFilter {
		
	private String date;
	private ArrayList<Posts> tweets;
	private ArrayList<Utenti> users;
		
		public DailyFilter(String date, ArrayList<Posts> tweets, ArrayList<Utenti> users) {
			
			this.date = date;
			this.tweets = tweets;
			this.users = users;
			
		}
		
		@SuppressWarnings("unchecked")
		public JSONObject filter() {
			
			JSONObject dt = new JSONObject();
			JSONArray list = new JSONArray();
			
			for (int i=0; i<tweets.size(); i++) {
				
				JSONObject data = null;
				
				if(tweets.get(i).getHashtags() != null) {
					if(tweets.get(i).getCr_date().contains(date)) {
						data = new JSONObject();
						data.put("Data di creazione",tweets.get(i).getCr_date());
						data.put("Id",tweets.get(i).getId_tweet());
						data.put("hashtag",tweets.get(i).getHashtags());
						data.put("Nome dell'utente che ha postato il tweet", users.get(i).getName());
						data.put("Numero di followers", users.get(i).getFollowers());
					}
				}
					if(data != null) {
						list.add(data);
					}else if (list == null) {
							dt.put("Motivo: nessun tweet corrisponde ai parametri inseriti ", null);}
					}
					dt.put("Lista filtrata di tweet pubblicati il giorno " + date, list);

			return dt;
			
		}
}