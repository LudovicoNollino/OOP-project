package it.univpm.TwitterHashtagAnalytics.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

/**
 * L' interfaccia APIControl_Interface viene utilizzata per le chiamate API
 */
public interface APIControl_Interface {
	
	
	/**
	 * Metodo astratto che effettua una GET per ottenere dati sui tweet
	 *
	 * @return un ArrayList di posts 
	 */
	public abstract ArrayList<Posts> getPosts();
	
	
	/**
	 * Metodo astratto che effettua una GET per ottenere dati sugli utenti
	 *
	 * @return un ArrayList di utenti
	 */
	public abstract ArrayList<Utenti> getUtenti();
	
	
	/**
	 * Metodo che restituisce una stringa corrispondente all'URL per effettuare la ricerca
	 *
	 * @return stringa corrispondente all'URL utilizzato per effettuare la ricerca
	 * @throws UnsupportedEncodingException la non supportata encoding exception
	 */
	public abstract String buildQuery() throws UnsupportedEncodingException;
	
	
	/**
	 * Metodo astratto che effettua il salvataggio dei dati
	 *
	 * @return stringa con conferma di salvataggio avvenuto
	 * @throws MalformedURLException se URL è scritta in modo errato
	 * @throws UnsupportedEncodingException la non supportata encoding exception
	 * @throws IOException Signals se si verifica un eccezione I/O
	 * @throws ParseException un errore di analisi
	 */
	public abstract String retrieveData() throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException;
	
	}
