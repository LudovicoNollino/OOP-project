package it.univpm.TwitterHashtagAnalytics.filters;

import org.json.simple.JSONObject;

//interfaccia per i filtri

public interface FilterInterface {
	
	//restituisce un JSONObject per i dati filtrati
	
	public abstract JSONObject filter();

}
