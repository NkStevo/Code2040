package com.stevenkuranga.code2040;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;

public class Registration {
	public static void main(String[] args) {
		HttpURLConnection connection = null;
		JSONObject json = new JSONObject();
		
		//Creates the JSON file
		json.put("token", "d89cb10811572aec44090ccb2805de48");
		json.put("github", "https://github.com/NkStevo/Code2040");
		
		try {
			//Opens connection to registration endpoint
			URL url = new URL("http://challenge.code2040.org/api/register");
			connection = (HttpURLConnection) url.openConnection();
			
			//Formats connection properties
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Language", "en-US");
			
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//Sends request to endpoint
			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			String urlParameter = json.toJSONString();
			URLEncoder.encode(urlParameter,"UTF-8");
			dos.writeBytes(urlParameter);
			dos.close();
			
			//Gets response from endpoint
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer response = new StringBuffer();
			
			String resLine;
			
			while ((resLine = br.readLine()) != null) {
				response.append(resLine);
				response.append("\n");
			}

			//Prints the final response
			System.out.println(response);
			
			br.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//Closes connection after work has been completed
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
