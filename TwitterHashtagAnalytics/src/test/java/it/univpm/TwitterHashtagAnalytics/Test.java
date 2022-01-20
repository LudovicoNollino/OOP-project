package it.univpm.TwitterHashtagAnalytics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.univpm.TwitterHashtagAnalytics.service.APIControl;

/**
 * Classe test per verificare la correttezza del metodo buildQuery().
 */
class Test {
	
	private APIControl test1;
	
	private String[] array1 = {"test"};
	
	/**
	 * Setup delle variabili per il test.
	 *
	 * @throws Exception eccezione generale
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		test1 = new APIControl(array1, "it", 5);
	}

	/**
	 * Teardown delle variabili per il test.
	 *
	 * @throws Exception eccezione generale
	 */
	@AfterEach
	void tearDown() throws Exception {}

	/**
	 * Prova 1.
	 *
	 * @throws MalformedURLException Eccezione generata nel caso in cui c'è un errore durante la creazione dell'URL
	 * @throws IOException Eccezione generata nel caso in cui c'è un errore di input output.
	 */
	@org.junit.jupiter.api.Test
	void prova1() throws MalformedURLException, IOException{
		
		assertEquals(test1.buildQuery(), "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?lang=it&count=5&q=%23test");
	}
	
}