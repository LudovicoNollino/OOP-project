package it.univpm.TwitterHashtagAnalytics.calls;

import java.util.ArrayList;
import it.univpm.TwitterHashtagAnalytics.model.Posts;
import it.univpm.TwitterHashtagAnalytics.model.Utenti;

public interface APIControl_Interface {
	
	//Metodo astratto che effettua una GET per ottenere dati sui tweet
	
	public abstract ArrayList<Posts> getPosts();
	
	//Metodo astratto che effettua una GET per ottenere dati sugli utenti
	
	public abstract ArrayList<Utenti> getUtenti();
	
	//Metodo astratto che effettua la ricerca tramite hashtag
	
	public abstract String buildQuery();
	
	//Metodo astratto che effettua il salvataggio dei dati
	
	public abstract ArrayList<Posts> retrieveData();
	
	
	
	}
