package com.stevenkuranga.code2040;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;

public class PostRequest {
	public static String post(String urlStr, JSONObject json) {
		HttpURLConnection connection = null;
		
		try {
			//Opens connection to registration endpoint
			URL url = new URL(urlStr);
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

			br.close();
			is.close();
			
			//Returns the final response
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//Closes connection after work has been completed
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
