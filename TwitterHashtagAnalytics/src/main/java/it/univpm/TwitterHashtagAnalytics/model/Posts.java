package it.univpm.TwitterHashtagAnalytics.model;

import java.util.ArrayList;

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
	ArrayList<String> hashtags;
	private long id_tweet;
	private int retweets;
	private int likes;
	//private int replies;
	
	public Posts (Posts post) {
		setCr_date(post.getCr_date());
		setHashtags(post.getHashtags());
		setId_tweet(post.id_tweet);
		setLikes(post.likes);
		setRetweets(post.retweets);
	}
	
	
	
    public Posts(String cr_date, Long id_tweet, ArrayList<String> hashtags, int retweets, int likes){
    	setCr_date(cr_date);
    	setHashtags(hashtags);
    	setId_tweet(id_tweet);
    	setLikes(likes);
    	setRetweets(retweets);
    }

	public String getCr_date() {return cr_date;}

	public void setCr_date(String cr_date) {this.cr_date = cr_date;}

	public long getId_tweet() {return id_tweet;}

	public void setId_tweet(long id_tweet) {this.id_tweet = id_tweet;}

	public ArrayList<String> getHashtags() {return hashtags;}

	public void setHashtags(ArrayList<String> hashtags) {this.hashtags = hashtags;}

	public int getRetweets() {return retweets;}

	public void setRetweets(int retweets) {this.retweets = retweets;}

	public int getLikes() {return likes;}

	public void setLikes(int likes) {this.likes = likes;}
}
