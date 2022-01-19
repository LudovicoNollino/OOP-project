package it.univpm.TwitterHashtagAnalytics.calls;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

public interface APIControl_Interface {
	
	//Metodo astratto che effettua una GET per ottenere dati sui tweet
	
	public abstract ArrayList<Posts> getPosts();
	
	//Metodo astratto che effettua una GET per ottenere dati sugli utenti
	
	public abstract ArrayList<Utenti> getUtenti();
	
	//Metodo astratto che effettua la ricerca tramite hashtag
	
	public abstract String buildQuery() throws UnsupportedEncodingException;
	
	//Metodo astratto che effettua il salvataggio dei dati
	
	public abstract String retrieveData() throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException;
	
	
	
	}
