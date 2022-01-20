 package it.univpm.TwitterHashtagAnalytics.model;

/**
 * Parametri per implementare il Costruttore.
 */

public class Utenti {
	
	private Long id_utente;
	
	private String name;
	
	private Long followers;
	
	/**
	 * Costruttore per la classe Utenti
	 *
	 * @param Utenti the utenti
	 */
	public Utenti (Utenti Utenti) {
		setId_utente(Utenti.getId_utente());
		setName(Utenti.getName());
		setFollowers(Utenti.getFollowers());
	}
	
	/**
	 * Costruttore
	 *
	 * @param id_utente è il codice identificativo dell'utente
	 * @param name è il nome dell'utente
	 * @param followers è il numero di followers dell'utente
	 */
	public Utenti(Long id_utente, String name, Long followers){
		setId_utente(id_utente);
		setName(name);
		setFollowers(followers);
    }

	/**
	 * Getters & setters di ogni parametro del costruttore
	 *
	 */
	public Long getId_utente() {return id_utente;}

	public void setId_utente(Long id_utente) {this.id_utente = id_utente;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public Long getFollowers() {return followers;}

	public void setFollowers(Long followers) {this.followers = followers;}

}
