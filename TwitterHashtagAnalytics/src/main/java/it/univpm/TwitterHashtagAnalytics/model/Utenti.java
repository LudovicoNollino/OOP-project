 package it.univpm.TwitterHashtagAnalytics.model;

/**
 * Parametri per implementare il Costruttore
 * 
 * @param id_utente indica l'identificativo dell' utente
 * @param name indica il nome dell'utente che ha effettuato il tweet
 * @param followers indica il numero di followers che ha l'utente
 */

public class Utenti {
	private Long id_utente;
	private String name;
	private Long followers;
	
	public Utenti (Utenti Utenti) {
		setId_utente(Utenti.getId_utente());
		setName(Utenti.getName());
		setFollowers(Utenti.getFollowers());
	}
	
	public Utenti(Long id_utente, String neme, Long followers){
		setId_utente(id_utente);
		setName(name);
		setFollowers(followers);
    }

	public Long getId_utente() {return id_utente;}

	public void setId_utente(Long id_utente) {this.id_utente = id_utente;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public Long getFollowers() {return followers;}

	public void setFollowers(Long followers) {this.followers = followers;}

}
