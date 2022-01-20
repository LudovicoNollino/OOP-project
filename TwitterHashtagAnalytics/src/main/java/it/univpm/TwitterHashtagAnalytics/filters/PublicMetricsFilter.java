package it.univpm.TwitterHashtagAnalytics.filters;


import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;



/**
 * Questa classe effettua il filtraggio sui tweet per numero di likes, retweets e followers
 * 
 * @author Simone Recchia
 * @author Ludovico Nollino
 */
public class PublicMetricsFilter{
	
	/*
	 * Attributi
	 *  */
	private int likes;
	
	private int followers;
	
	private int retweet;
	
	private ArrayList<Posts> tweets;
	
	private ArrayList<Utenti> users;
	
	
	
	/**
	 * Costruttore
	 *
	 * @param likes è il numero dei likes dei post
	 * @param followers è il numero di followers dell'utente che ha effettuato il tweet
	 * @param retweet è il numero di retweets dei post
	 * @param tweets è la lista dei post salvati
	 * @param users è la lista degli utenti salvati
	 */
	public PublicMetricsFilter(int likes, int followers, int retweet, ArrayList<Posts> tweets, ArrayList<Utenti> users) {
		
		this.likes = likes;
		this.followers = followers;
		this.retweet = retweet;
		this.tweets = tweets;
		this.users = users;
				
	}
	
	//Metodo che ritorna i post con un numero like retweet w followers MAGGIORE di quello inserito
	
	/**
	 * Questo metodo memorizza in un JSONObject i tweets che hanno un numero superiore di likes e retweets di quello specificato
	 * e gli utenti che hanno un numero di followers superiore di quello specificato
	 *
	 * @return JSONObject con post con un numero like retweet e followers maggiore di quello inserito
	 */
	public JSONObject filter () {
			
		JSONObject pu = new JSONObject();
		JSONArray list = new JSONArray();
		
		for(int i = 0; i < tweets.size(); i++) {
			JSONObject post_user = null;
			
			if(tweets.get(i) != null) {
				if(likes <= tweets.get(i).getLikes() && retweet <= tweets.get(i).getRetweets() && followers <= users.get(i).getFollowers()) {
					post_user = new JSONObject();
					
					post_user.put("Id del tweet filtrato", tweets.get(i).getId_tweet());
					post_user.put("Numero di likes", tweets.get(i).getLikes());
					post_user.put("Numero di retweets", tweets.get(i).getRetweets());
					post_user.put("Nome dell'utente che ha postato il tweet", users.get(i).getName());
					post_user.put("Numero di followers", users.get(i).getFollowers());
				}
				if(post_user != null) {
					list.add(post_user);
				}else if (list == null) {
					pu.put("Il filtro non ha rilevato post che rispettano i parametri inseriti ", null);
				}
				pu.put("Lista filtrata secondo i parametri inseriti", list);
			}
		}
		return pu;
	}
}
