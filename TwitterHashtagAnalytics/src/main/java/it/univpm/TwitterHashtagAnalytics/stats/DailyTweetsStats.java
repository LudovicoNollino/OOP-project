package it.univpm.TwitterHashtagAnalytics.stats;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

	public class DailyTweetsStats implements StatsInterface{

		private ArrayList<Posts> tweets;
		private ArrayList<Utenti> users;
		private String date;
		
		public DailyTweetsStats (String date, ArrayList<Posts> tweets, ArrayList<Utenti> users) {
			
			this.date = date;
			this.users = users;
			this.tweets = tweets;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public JSONObject Statistics() {
			
			JSONObject dstats = new JSONObject();
			float count = 0;
			float maxLikes = 0;
			float minLikes = 1;
			float maxRetweets = 0;
			float minRetweets = 1;
			float maxFollowers = 0;
			float minFollowers = 1;
			
			for (int j=0; j<tweets.size(); j++) {
				
				if (tweets.get(j).getCr_date().contains(date)) {
				
				if (tweets.get(j).getLikes() != 0 && tweets.get(j).getRetweets() != 0 && users.get(j).getFollowers() != 0) {
					if(maxLikes < tweets.get(j).getLikes()) {
							maxLikes = tweets.get(j).getLikes();}
					if(minLikes > tweets.get(j).getLikes()) {
							minLikes = tweets.get(j).getLikes();}
					
					if(maxRetweets < tweets.get(j).getRetweets()) {
						maxRetweets = tweets.get(j).getRetweets();}
					if(minRetweets > tweets.get(j).getRetweets()) {
						minRetweets = tweets.get(j).getRetweets();}
					
					if(maxFollowers < users.get(j).getFollowers()) {
						maxFollowers = users.get(j).getFollowers();}
					if(minFollowers > users.get(j).getFollowers()) {
						minFollowers = users.get(j).getFollowers();}
					
					}
				count++;
				}
			}
			
			float mediaLikes = ((minLikes+maxLikes)/tweets.size());
			float mediaRetweets = ((minRetweets+maxRetweets)/tweets.size());
			float mediaFollowers = ((minFollowers+maxFollowers)/tweets.size());
			
			dstats.put("Totale dei tweet analizzati nella data scelta"  , count);
			dstats.put("Massimo numero di likes dei tweet analizzati", maxLikes);
			dstats.put("Minimo numero di likes dei tweet analizzati" , minLikes);
			dstats.put("Media dei likes dei tweet analizzati", mediaLikes);
			
			dstats.put("Massimo numero di retweets dei tweet analizzati", maxRetweets);
			dstats.put("Minimo numero di retweets dei tweet analizzati" , minRetweets);
			dstats.put("Media dei retweets dei tweet analizzati", mediaRetweets);
			
			dstats.put("Massimo numero di followers tra gli utenti dei tweet analizzati", maxFollowers);
			dstats.put("Minimo numero di followers tra gli utenti dei tweet analizzati" , minFollowers);
			dstats.put("Media dei followers", mediaFollowers);
			
			return dstats;			
		}
}