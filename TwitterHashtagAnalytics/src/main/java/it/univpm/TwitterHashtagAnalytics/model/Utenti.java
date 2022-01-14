package it.univpm.TwitterHashtagAnalytics.model;

/**
 * Parametri per implementare il Costruttore
 * 
 * @param id_utente indica l'identificativo dell' utente
 * @param name indica il nome dell'utente che ha effettuato il tweet
 * @param followers indica il numero di followers che ha l'utente
 */

public class Utenti {
	private long id_utente;
	private String name;
	private int followers;
	
	public Utenti () {
		this.id_utente = 0;
		this.name = "";
		this.followers = 0; 
	}

	public long getId_utente() {
		return id_utente;
	}

	public void setId_utente(long id_utente) {
		this.id_utente = id_utente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

}
