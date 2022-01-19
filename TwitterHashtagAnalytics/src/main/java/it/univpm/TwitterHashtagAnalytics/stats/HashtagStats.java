package it.univpm.TwitterHashtagAnalytics.stats;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import it.univpm.TwitterHashtagAnalytics.model.Posts;

public class HashtagStats implements StatsInterface{

	private String hashtag;
	private ArrayList<Posts> tweets;
	
	public HashtagStats (String hashtag, ArrayList<Posts> tweets) {
		
		this.hashtag = hashtag;
		this.tweets = tweets;
	}
	
	//metodo per effettuare le statistiche sui ntweets salvati
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject Statistics() {
		
		JSONObject hstat = new JSONObject();
		float count = 0;
		float max = 0;
		float min = 1;
		for (int j=0; j<tweets.size(); j++) {
			
			if (hashtag != null) {
				if (tweets.get(j).getHashtags().contains(hashtag)) {
					
					if(max < tweets.get(j).getHashtags().size()) {
						max = tweets.get(j).getHashtags().size();
					}
					if(min > tweets.get(j).getHashtags().size()) {
						min = tweets.get(j).getHashtags().size();
					}
						count++;
					
					}
				
				}		
				
			}
		
		float media = ((min+max)/tweets.size());
		
		hstat.put("Totale dei tweet contenenti l'hashtag #" + hashtag , count);
		hstat.put("Massimo numero di hashtag contenuto in un tweet", max);
		hstat.put("Minimo numero di hastag contenuto in un tweet" , min);
		hstat.put("Media degli hashtag per tweet", media);
		
		return hstat;
		}	
	}
