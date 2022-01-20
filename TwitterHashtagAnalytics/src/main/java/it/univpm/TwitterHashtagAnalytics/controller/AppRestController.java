package it.univpm.TwitterHashtagAnalytics.controller;

import it.univpm.TwitterHashtagAnalytics.service.APIControl;
import it.univpm.TwitterHashtagAnalytics.service.GetData;
import it.univpm.TwitterHashtagAnalytics.filters.*;
import it.univpm.TwitterHashtagAnalytics.stats.DailyTweetsStats;
import it.univpm.TwitterHashtagAnalytics.stats.HashtagStats;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

			@RequestParam (name="Hashtag", defaultValue = "%23univpm") String[] hashes,
			@RequestParam (name="Lang", defaultValue = "it") String lang,
			@RequestParam (name="Count", defaultValue = "5") int count) throws IOException, ParseException{
	
		call = new APIControl(hashes, lang, count);

		return new ResponseEntity<>(call.retrieveData(), HttpStatus.OK);
		}
	
	//rotta GET per i metadati
	
	@GetMapping(value ="/metadati")
	public ResponseEntity<Object> showMetadati() {
		
		GetData metadati = new GetData();
		return new ResponseEntity<>(metadati.showMetadati(), HttpStatus.OK);
	}
	
	// rotta GET che mostra i dati dei tweet salvati in precedenza
	
	@GetMapping(value = "/tweet-data")
	public ResponseEntity<Object> showData() {
		
		GetData dati = new GetData(call.getPosts(), call.getUtenti());
		
		return new ResponseEntity<>(dati.showData(), HttpStatus.OK);
		}
	
	//rotta GET che filtra i tweet salvati per hashtags inseriti
	
	@GetMapping(value ="/filter/hashtag")
	public ResponseEntity<Object> HashtagFilter(@RequestParam(name ="Hashtag") String hash) {
		
			HashtagFilter hashf = new HashtagFilter(hash, call.getPosts());
			return new ResponseEntity<>(hashf.filter(), HttpStatus.OK);
	}
	
	//rotta GET che filtra i tweet salvati per numero di retweet, reply e like
	
	@GetMapping(value ="/filter/retweets-likes-followers")
	public ResponseEntity<Object> PublicMetricsFilter(
			
			@RequestParam(name = "Likes") int likes,
			@RequestParam(name = "Retweet") int retweets,
			@RequestParam(name = "Followers") int followers){
		
		PublicMetricsFilter pmf = new PublicMetricsFilter(likes, retweets, followers, call.getPosts(), call.getUtenti());
		return new ResponseEntity<>(pmf.filter(), HttpStatus.OK);
	}
	
	//Rotta GET che filtra i tweet per data di pubblicazione
	
	@GetMapping(value = "/filter/daily")
	public ResponseEntity<Object> DateFilter(@RequestParam(name = "Data") String date){
		
			DailyFilter df = new DailyFilter(date, call.getPosts(), call.getUtenti());
			return new ResponseEntity<>(df.filter(), HttpStatus.OK);
	}
	
	//Rotta GET che effettua le statistiche sugli hashtag
	
	@GetMapping(value = "/stats/hashtag")
	public ResponseEntity<Object> HashStats(@RequestParam(name = "Hashtag") String hashtag){
		
		HashtagStats hs = new HashtagStats(hashtag, call.getPosts());
		
		return new ResponseEntity<>(hs.Statistics(), HttpStatus.OK);
	}
	
	//Rotta GET che effettua le statistiche sui tweet giornalieri in riferimento alle loro metriche pubbliche
	
	@GetMapping(value = "/stats/daily")
	public ResponseEntity<Object> DailyStats(@RequestParam(name = "Data") String date){
		
		DailyTweetsStats ds = new DailyTweetsStats(date, call.getPosts(), call.getUtenti());
		
		return new ResponseEntity<>(ds.Statistics(), HttpStatus.OK);
	}

}
