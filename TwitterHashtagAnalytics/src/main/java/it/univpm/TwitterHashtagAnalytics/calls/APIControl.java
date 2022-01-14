package it.univpm.TwitterHashtagAnalytics.calls;

import it.univpm.TwitterHashtagAnalytics.model.Posts;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APIControl {
	
	//Parametri
	
	final static String linkBase ="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?";
	String linkFinale;
	String hash1,hash2,hash3;
	String lang;
	int count;
	
public APIControl(String hash1, String hash2, String hash3, String lang, int count){
	
	try {
		URL url = new URL("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "applications/json");
		
		int response = conn.getResponseCode();
		
		if(conn.getResponseCode() != 200) {
			throw new RuntimeException("httpResponseCode: " + response);
		}
		else {
			StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
            
            System.out.println(informationString);
            
            JSONParser parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
            
            System.out.println(dataObject.get(0));

            JSONObject countryData = (JSONObject) dataObject.get(0);

            System.out.println(countryData.get("woeid"));
		}
	}catch (Exception e) {
        e.printStackTrace();}
}
}

