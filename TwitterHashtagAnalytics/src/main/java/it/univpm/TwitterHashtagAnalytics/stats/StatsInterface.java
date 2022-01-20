package it.univpm.TwitterHashtagAnalytics.stats;

import java.util.HashMap;

/**
 * Interfaccia che contiene il metodo statistics implementato dalle classi DailyTweetsStats
 * e HashtagStats.
 */
public interface StatsInterface {
	
	/**
	 * Costruttore del metodo astratto Statistics.
	 *
	 * @return HashMap con coppia di chiave di tipo Stringa per il messaggio
	 * di output e valore di tipo float per l'output dei calcoli effettuati
	 */
	public abstract HashMap<String, Float> Statistics(); 
	
	}
