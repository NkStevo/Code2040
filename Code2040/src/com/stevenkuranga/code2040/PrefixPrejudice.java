package com.stevenkuranga.code2040;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PrefixPrejudice {

	public static void main(String[] args) throws ParseException {
		//Creates the token JSONObject
		JSONObject json = new JSONObject();
				
		//Creates the given JSONObject and accompanying parser
		JSONParser parser = new JSONParser();
		JSONObject jsonGet;
				
		//Creates string to hold the prefix
		String prefix;

		json.put("token", "d89cb10811572aec44090ccb2805de48");

		//Parses the string given into a JSON object
		jsonGet = (JSONObject) parser.parse(PostRequest.post(
				"http://challenge.code2040.org/api/prefix", json));

		prefix = (String) jsonGet.get("prefix");

		//Converts string array into ArrayList
		ArrayList<Object> strArray = new ArrayList<Object>(
				Arrays.asList((Object[]) ((JSONArray) jsonGet.get("array")).toArray()));

		Iterator itr = strArray.iterator();

		//Iterates through ArrayList and removes any strings with the given prefix
		while (itr.hasNext()) {
			Object node = itr.next();
			
			if (node.toString().startsWith(prefix)) {
				itr.remove();
			}
		}
		
		//Adds the altered ArrayList to a JSONArray
		JSONArray postArray = new JSONArray();
		postArray.addAll(strArray);
		
		json.put("array", postArray);
		
		//Prints the response
		System.out.println(PostRequest.post(
				"http://challenge.code2040.org/api/prefix/validate", json));
	}

}
