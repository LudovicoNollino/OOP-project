package it.univpm.TwitterHashtagAnalytics.filters;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

/**
 * Questa classe effettua filtraggio sui tweet di una data scelta
 * 
 * @author Simone Recchia
 * @author Ludovico Nollino
 */

public class DailyFilter {
	
	/*
	 * Attributi
	 * 
	 * */
	
	private String date;
	private ArrayList<Posts> tweets;
	private ArrayList<Utenti> users;
		
		/*
		 * Costruttore
		 * 
		 * @param date è la data per il filtraggio
		 * @param tweets indica la lista di tweets salvati
		 * @param users indica la lista di utente salvati
		 * */
		public DailyFilter(String date, ArrayList<Posts> tweets, ArrayList<Utenti> users) {
			
			this.date = date;
			this.tweets = tweets;
			this.users = users;
			
		}
		
		/*
		 *Questo metodo serve per memorizzare in un JSONObject i tweets scritti nella data scelta
		 *
		 * @return JSONObject contenente i tweet scritti nel giorno scelto e la lista dei tweet di quel giorno
		 * @return JSONObject contenente i la lista di utenti che hanno postato nella data scelta
		 * */
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