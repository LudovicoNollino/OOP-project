package it.univpm.TwitterHashtagAnalytics.model;

/**
 * Parametri per implementare il Costruttore
 * 
 * @param cr_date indica la data di creazione del tweet
 * @param id_tweet indica l'identificativo del tweet
 * @param hashtags indica gli hashtag presenti nel tweet
 * @param retweet indica il numero di retweet
 * @param likes indica il numero di like
 * @param replies (in sospeso)
 *
 */

public class Posts {
	
	private String cr_date;
	private long id_tweet;
	private String[] hashtags;
	private int retweets;
	private int likes;
	//private int replies;
	
	public Posts () {
		this.cr_date = "";
		this.id_tweet = 0;
		this.hashtags = null;
		this.retweets = 0;
		this.likes = 0;
		//this.replies = 0; 
	}

	public String getCr_date() {
		return cr_date;
	}

	public void setCr_date(String cr_date) {
		this.cr_date = cr_date;
	}

	public long getId_tweet() {
		return id_tweet;
	}

	public void setId_tweet(long id_tweet) {
		this.id_tweet = id_tweet;
	}

	public String[] getHashtags() {
		return hashtags;
	}

	public void setHashtags(String[] hashtags) {
		this.hashtags = hashtags;
	}

	public int getRetweets() {
		return retweets;
	}

	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
}
