package it.univpm.TwitterHashtagAnalytics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.univpm.TwitterHashtagAnalytics.service.APIControl;

class Test {
	
	private APIControl test1;
	private String[] array1 = {"test"};
	
	@BeforeEach
	void setUp() throws Exception {
		
		test1 = new APIControl(array1, "it", 5);
	}

	@AfterEach
	void tearDown() throws Exception {}

	@org.junit.jupiter.api.Test
	void prova1() throws MalformedURLException, IOException{
		
		assertEquals(test1.buildQuery(), "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?lang=it&count=5&q=%23test");
	}
	
}