package it.univpm.TwitterHashtagAnalytics.controller;

import it.univpm.TwitterHashtagAnalytics.calls.APIControl;
import it.univpm.TwitterHashtagAnalytics.calls.getData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "application/json")
public class AppRestController {
	
	/**
	 * rotta GET per la ricerca del post tramite almeno uno degli hashtag inseriti dall'utente 
	 *con annesso salvataggio
	 **/
	
	APIControl call; 
	
	@GetMapping( value = "/posts/get-tweet")
	
	public ResponseEntity<Object> getPosts(

			@RequestParam (name="hashtags") String[] hash,
			@RequestParam (name="lang", defaultValue = "it") String lang,
			@RequestParam (name="count", defaultValue = "10") int count){
	
		call = new APIControl(hash, lang, count);
		
	return new ResponseEntity<Object>(call.retrieveData(), HttpStatus.OK);
	}
	
	//rotta GET per i metadati
	
/*	@GetMapping(value ="/posts/metadati")
	
	public ResponseEntity<Object> showMetadati() {
		
		GetDati metadati = new GetDati();
		return new ResponseEntity<>(metadati.showMetadati(), HttpStatus.OK);
	}*/
	
	// rotta GET che mostra i dati dei tweet salvati in precedenza
	
	@GetMapping(value = "/posts/tweet-data")
	public ResponseEntity<Object> showData() {
		
		getData dati = new getData(call.retrieveData());
		
		return new ResponseEntity<>(dati.seeData(), HttpStatus.OK);
	}
}
