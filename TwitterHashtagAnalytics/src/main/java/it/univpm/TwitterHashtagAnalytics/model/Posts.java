package it.univpm.TwitterHashtagAnalytics.model;

import java.util.ArrayList;

/**
 * Parametri per implementare il Costruttore.
 */

public class Posts {
	
	
	private String cr_date;
	
	ArrayList<String> hashtags;
	
	private long id_tweet;
	
	private long retweets;
	
	private long likes;
	
	
	/**
	 * Costruttore per i la classe Posts
	 *
	 * @param post è il singolo elemento contenuto in Posts
	 */
	public Posts (Posts post) {
		setCr_date(post.getCr_date());
		setHashtags(post.getHashtags());
		setId_tweet(post.id_tweet);
		setLikes(post.likes);
		setRetweets(post.retweets);
	}
	
	
	
    /**
     * Costruttore
     *
     * @param cr_date è la data di creazione del post
     * @param id_tweet è il codice identificativo del tweet
     * @param hashtags sono gli hashtags contenuti nel tweet
     * @param retweets è il numero di retweetts di un post
     * @param likes è il numero di likes di un post
     */
    public Posts(String cr_date, Long id_tweet, ArrayList<String> hashtags, Long retweets, Long likes){
    	setCr_date(cr_date);
    	setHashtags(hashtags);
    	setId_tweet(id_tweet);
    	setLikes(likes);
    	setRetweets(retweets);
    }

    /*
     * Getters & setters di ogni parametro del cotruttore
     * */
	public String getCr_date() {return cr_date;}

	public void setCr_date(String cr_date) {this.cr_date = cr_date;}

	public long getId_tweet() {return id_tweet;}

	public void setId_tweet(long id_tweet) {this.id_tweet = id_tweet;}

	public ArrayList<String> getHashtags() {return hashtags;}

	public void setHashtags(ArrayList<String> hashtags) {this.hashtags = hashtags;}

	public Long getRetweets() {return retweets;}

	public void setRetweets(Long retweets) {this.retweets = retweets;}

	public Long getLikes() {return likes;}

	public void setLikes(Long likes) {this.likes = likes;}
}
