package it.univpm.TwitterHashtagAnalytics.controller;

import org.springframework.web.bind.annotation.RestController;

import it.univpm.TwitterHashtagAnalytics.calls.APICall;
import it.univpm.TwitterHashtagAnalytics.calls.APIControl;

import java.util.concurrent.Callable;

import org.junit.validator.PublicClassValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "applications/json")
public class AppRestController {
	/**
	 * rotta GET per la ricerca del post tramite uno degli hashtag inseriti dall'utente 
	 *con annesso salvataggio
	 **/
	APIControl call; 
	@GetMapping( value = "/posts/get-tweet")
	public ResponseEntity<Object> getPosts(
			@RequestParam (name="hashtag1") String hash1,
			@RequestParam (name="hashtag2", required = false) String hash2,
			@RequestParam (name="hashtag3", required = false) String hash3,
			@RequestParam (name="lang", defaultValue = "it") String lang,
			@RequestParam (name="count", defaultValue = "10") int count){
	call = new APIControl(hash1, hash2, hash3, lang, count);
	return new ResponseEntity<Object>(call.saveData(), HttpStatus.OK);
	}
	
	//rotta GET per i metadati
	@GetMapping(value ="/posts/metadati")
	public ResponseEntity<Object> showMetadati() {
		GetDati metadati = new GetDati();
		//return new ResponseEntity<>(metadati.showMetadati(), HttpStatus.OK);
	}
	
	// rotta GET che mostra i dati dei tweet salvati in precedenza
	@GetMapping(value = "/posts/tweet-data")
	public ResponseEntity<Object> showDati() {
		GetDati dati = new GetDati(call.getPosts, call.getUtenti);
		//return new ResponseEntity<>(metadati.showMetadati(), HttpStatus.OK);
	}
	
	
	
	}
