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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * AppRestController gestisce tutte le rotte disponibili
 * 
 * @author Simone Recchia
 * @author Ludovico Nollino
 * */

@RestController
@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "application/json")
public class AppRestController {
	
	/**
	 * rotta GET per la ricerca del post tramite almeno uno degli hashtag inseriti dall'utente 
	 *con annesso salvataggio
	 *
	 *@param hashes è un ArrayList di stringhe contenente tutti gli hashtags da ricercare
	 *@param lang è la lingua della ricerca
	 *@param count è il numero di risultati che si vuole visualizzare 
	 *
	 * @return una stringa che contiene la conferma del salvataggio
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
	
	/*
	 * rotta GET per i metadati
	 * 
	 * @return JSONObject che contiene i metadati, ovvero le informazioni di ogni tipo di dato visualizzato
	 * */
	
	@GetMapping(value ="/metadati")
	public ResponseEntity<Object> showMetadati() {
		
		GetData metadati = new GetData();
		return new ResponseEntity<>(metadati.showMetadati(), HttpStatus.OK);
	}
	
	/*
	 * rotta GET che mostra i dati dei tweet salvati in precedenza
	 * 
	 * @return JSONObject che contiene i dati dei post precedentemente ricercati e salvati
	 * */ 
	
	@GetMapping(value = "/tweet-data")
	public ResponseEntity<Object> showData() {
		
		GetData dati = new GetData(call.getPosts(), call.getUtenti());
		
		return new ResponseEntity<>(dati.showData(), HttpStatus.OK);
		}
	
	/*
	 * rotta GET che filtra i tweet salvati per hashtags inseriti
	 * 
	 * @param hash è l'hashtag passato per effettuare il filtraggio
	 * @return JSONObject che contiene i tweet filtrati con l'hashtag inserito dall'utente
	 * */
	
	@GetMapping(value ="/filter/hashtag")
	public ResponseEntity<Object> HashtagFilter(@RequestParam(name ="Hashtag") String hash) {
		
			HashtagFilter hashf = new HashtagFilter(hash, call.getPosts());
			return new ResponseEntity<>(hashf.filter(), HttpStatus.OK);
	}
	
	/*
	 * rotta GET che filtra i tweet salvati per numero di retweet, reply e like
	 * 
	 * @param likes è il numero di likes passato per effettuare il filtraggio
	 * @param retweets è il numero di retweets passato per effettuare il filtraggio
	 * @param followers è il numero di followers passato per effettuare il filtraggio
	 * @return JSONObject che contiene i tweet con un numero di like, retweet e followers superiore a quello inserito
	 * */
	
	@GetMapping(value ="/filter/retweets-likes-followers")
	public ResponseEntity<Object> PublicMetricsFilter(
			
			@RequestParam(name = "Likes") int likes,
			@RequestParam(name = "Retweet") int retweets,
			@RequestParam(name = "Followers") int followers){
		
		PublicMetricsFilter pmf = new PublicMetricsFilter(likes, retweets, followers, call.getPosts(), call.getUtenti());
		return new ResponseEntity<>(pmf.filter(), HttpStatus.OK);
	}
	
	/*
	 * Rotta GET che filtra i tweet per data di pubblicazione
	 * 
	 * @param date è la data per il filtraggio
	 * @return JSONObject che contiene i tweet postati nel giorno inserito
	 * */
	
	@GetMapping(value = "/filter/daily")
	public ResponseEntity<Object> DateFilter(@RequestParam(name = "Data") String date){
		
			DailyFilter df = new DailyFilter(date, call.getPosts(), call.getUtenti());
			return new ResponseEntity<>(df.filter(), HttpStatus.OK);
	}
	
	/*
	 * Rotta GET che effettua le statistiche sugli hashtag
	 * 
	 * @param hashtag è l'hashtag passato per effettuare le statistiche
	 * @return JSONObject che contiene numero massimo, numero minimo e media degli hashtag per tweet
	 * */
	
	@GetMapping(value = "/stats/hashtag")
	public ResponseEntity<Object> HashStats(@RequestParam(name = "Hashtag") String hashtag){
		
		HashtagStats hs = new HashtagStats(hashtag, call.getPosts());
		
		return new ResponseEntity<>(hs.Statistics(), HttpStatus.OK);
	}
	
	/*
	 * Rotta GET che effettua le statistiche sui tweet giornalieri in riferimento alle loro metriche pubbliche
	 * 
	 * @param date è la data inserita per effettuare le statistiche
	 * @return JSONObject che contiene numero massimo, numero minimo e media di like, 
	 * retweet e followers dei tweet postati in un giorno
	 * */
	
	@GetMapping(value = "/stats/daily")
	public ResponseEntity<Object> DailyStats(@RequestParam(name = "Data") String date){
		
		DailyTweetsStats ds = new DailyTweetsStats(date, call.getPosts(), call.getUtenti());
		
		return new ResponseEntity<>(ds.Statistics(), HttpStatus.OK);
	}

}
