package it.univpm.TwitterHashtagAnalytics.service;

/**
 * La classe APIError rileva errori sulle chiamate API.
 */
public class APIError {

	String exception;

	/**
	 * Getter & Setter della classe APIError
	 *
	 */
	public String getException() {
		return exception;
	}


	public void setException(String exception) {
		this.exception = exception;
	}	
}