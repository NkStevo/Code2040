package com.stevenkuranga.code2040;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DatingGame {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		//Creates the JSONObject
		JSONObject json = new JSONObject();
		
		//Creates the given JSONObject and accompanying parser
		JSONParser parser = new JSONParser();
		JSONObject jsonGet = new JSONObject();
		
		json.put("token", "d89cb10811572aec44090ccb2805de48");

		//Parses the string given into a JSONObject
		jsonGet = (JSONObject) parser.parse(PostRequest.post(
				"http://challenge.code2040.org/api/dating", json));
		
		//Creates a formatter which parses the string into a LocalDateTimeObject
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse((CharSequence) jsonGet.get("datestamp"), formatter);
		
		//Adds the interval to the given datestamp
		dateTime = dateTime.plusSeconds((long) jsonGet.get("interval"));
		json.put("datestamp", (dateTime + "Z"));
		
		//Prints the response
		System.out.println(PostRequest.post(
				"http://challenge.code2040.org/api/dating/validate", json));
	}

}
