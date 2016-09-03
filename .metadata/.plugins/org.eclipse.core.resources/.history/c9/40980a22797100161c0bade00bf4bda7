package com.stevenkuranga.code2040;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NeedleSearch {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		//Creates the token JSON object
		JSONObject json = new JSONObject();
		
		//Creates the given JSON object and accompanying parser
		JSONParser parser = new JSONParser();
		JSONObject jsonGet;
		
		//Creates string to hold the "needle
		String target;
				
		json.put("token", "d89cb10811572aec44090ccb2805de48");
		
		//Parses the string given into a JSON object
		jsonGet = (JSONObject) parser.parse(PostRequest.post(
				"http://challenge.code2040.org/api/haystack", json));
		
		target = (String) jsonGet.get("needle");
		
		//Converts "haystack" array into ArrayList
		ArrayList<Object> haystack = new ArrayList<Object>(
				Arrays.asList((Object[]) ((JSONArray) jsonGet.get("haystack")).toArray()));
		
		//Stores the index of the needle
		json.put("needle", haystack.indexOf(target));
		
		//Prints the response
		System.out.println(PostRequest.post(
				"http://challenge.code2040.org/api/haystack/validate", json));
	}
}
