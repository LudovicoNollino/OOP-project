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
	
	@GetMapping( value = "/get_tweet")
	
	public ResponseEntity<Object> getPosts(

			@RequestParam (name="q", defaultValue = "%23GreenDay") String hash,
			@RequestParam (name="lang", defaultValue = "en") String lang,
			@RequestParam (name="count", defaultValue = "25") int count){
	
		call = new APIControl(hash, lang, count);
		
	return new ResponseEntity<>(call.retrieveData(), HttpStatus.OK);
	}
	
	//rotta GET per i metadati
	
	@GetMapping(value ="/metadati")
	
	public ResponseEntity<Object> showMetadati() {
		
		getData metadati = new getData();
		return new ResponseEntity<>(metadati.showMetadati(), HttpStatus.OK);
	}
	
	// rotta GET che mostra i dati dei tweet salvati in precedenza
	
	@GetMapping(value = "/tweet-data")
	public ResponseEntity<Object> showData() {
		
		getData dati = new getData(call.getPosts(), call.getUtenti());
		
		return new ResponseEntity<>(dati.showData(), HttpStatus.OK);
		}
	
	//rotta GET che filtra i tweet salvati per hashtags inseriti
	
	/*@GetMapping(value ="/posts/filter/hashtag")
	public ResponseEntity<Object> HashtagFilter(@RequestParam(name ="hashtag") String hash) {
		
		HashtagFilter hashf = new HashtagFilter(hash,call.getPosts(), call.getUtenti());
		
		return new ResponseEntity<>(hashf.filter(), HttpStatus.OK);
		
	}
	
	//rotta GET che filtra i tweet salvati per numero di retweet, reply e like
	
	@GetMapping(value ="/posts/retweets-likes-replies")
	public ResponseEntity<Object> PublicMetricsFilter(
			
			@RequestParam(name ="favourites_count", required = false) int Likes,
			@RequestParam(name ="retweet_count", required = false)int retweets) {
		
		PublicMetricsFilter likes = new PublicMetricsFilter(likes, call.getPosts(), call.getUtenti());
		
		PublicMetricsFilter retw = new PublicMetricsFilter(retw, call.getPosts(), call.getUtenti());
		
		//qualcosa per le replies
		
		return new ResponseEntity<>(likes.filter(), retw.filter(), HttpStatus.OK);
		
	}
	*/
	
}
