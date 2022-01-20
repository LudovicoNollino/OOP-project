package it.univpm.TwitterHashtagAnalytics.stats;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.TwitterHashtagAnalytics.model.Posts;

public class HashtagStats implements StatsInterface{

	private String hashtag;
	private ArrayList<Posts> tweets;
	
	public HashtagStats (String hashtag, ArrayList<Posts> tweets) {
		
		this.hashtag = hashtag;
		this.tweets = tweets;
	}
	
	//metodo per effettuare le statistiche sui ntweets salvati
	@Override
	public HashMap<String, Float> Statistics() {
		
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
		
		HashMap<String, Float> hstat = new HashMap<String, Float>();

		hstat.put("Totale dei tweet contenenti l'hashtag #" + hashtag , count);
		hstat.put("Media degli hashtag per tweet", media);
		hstat.put("Massimo numero di hashtag contenuto in un tweet", max);
		hstat.put("Minimo numero di hastag contenuto in un tweet" , min);
		
		return hstat;
		}	
	}
