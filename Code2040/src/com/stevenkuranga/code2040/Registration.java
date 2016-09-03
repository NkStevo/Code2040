package com.stevenkuranga.code2040;

import org.json.simple.JSONObject;

public class Registration {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//Creates the JSONObject
		JSONObject json = new JSONObject();
		
		json.put("token", "d89cb10811572aec44090ccb2805de48");
		json.put("github", "https://github.com/NkStevo/Code2040");
		
		//Prints the response
		System.out.println(PostRequest.post(
				"http://challenge.code2040.org/api/register", json));
	}
}
