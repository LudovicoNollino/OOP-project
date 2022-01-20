package it.univpm.TwitterHashtagAnalytics.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.univpm.TwitterHashtagAnalytics.service.APIError;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<APIError> handleNumberFormatException (NumberFormatException e){
		APIError error = new APIError();
		error.setException("Si è cercato di applicare un filtro prima di effettuare la chiamata GET all'API");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<APIError> (error, status);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<APIError> handleNullPointerException (NullPointerException e){
		APIError error = new APIError();
		error.setException("Impossibile invocare il metodo call.getPosts perchè this.call è null");
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<APIError> (error, status);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IOException.class)
	public ResponseEntity<APIError> handleMalformedURLException (IOException e){
		APIError error = new APIError();
		error.setException("L'URL non è stato generato nel modo corretto, controllare parametri inseriti");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<APIError> (error, status);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<APIError> handleParseException (ParseException e){
		APIError error = new APIError();
		error.setException("Errore nel parsing dei dati, effettuare una nuova chiamata");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<APIError> (error, status);
	}	
}
