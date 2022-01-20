package it.univpm.TwitterHashtagAnalytics.stats;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

	/**
	 * Classe per l'implementazione delle statistiche sui tweet giornalieri.
	 */
	public class DailyTweetsStats implements StatsInterface{

		/** ArrayList popolato con i tweet. */
		private ArrayList<Posts> tweets;
		
		/** ArrayList popolato con gli utenti. */
		private ArrayList<Utenti> users;
		
		/** Parametro data necessario per effettuare la ricerca. */
		private String date;
		
		/**
		 * Costruttore della classe.
		 *
		 * @param date Data da cercare
		 * @param tweets ArrayList dei tweet
		 * @param users ArrayList degli users
		 */
		public DailyTweetsStats (String date, ArrayList<Posts> tweets, ArrayList<Utenti> users) {
			
			this.date = date;
			this.users = users;
			this.tweets = tweets;
		}
		
		/**
		 * Statistiche.
		 *
		 * @return HashMap contenente le statistiche
		 */
		@Override
		public HashMap<String, Float> Statistics() {
			
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
			
			HashMap<String, Float> dstats = new HashMap<String, Float>();

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