package com.example.guesstag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class InstaAPIManager {
	String accessToken = "15170786.1fb234f.196d7fd71cfa40a9aa12140c522a5c99";
	JSONObject level2;
	JSONObject level3;
	Bitmap bmp;
	
	public void initiateConnection() {
		// img1 = (ImageView) findViewById(R.id.imageView1);

		try {
			URL example = new URL(
					"https://api.instagram.com/v1/media/popular?access_token="
							+ accessToken);

			URLConnection tc = example.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));
			imageParsing(in);

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void imageParsing(BufferedReader in) {
		try {
			String line;
			while ((line = in.readLine()) != null) {
				JSONObject ob = new JSONObject(line);

				JSONArray object = ob.getJSONArray("data");

		//		for (int i = 0; i < object.length(); i++) {

					JSONObject level1 = (JSONObject) object.get(1);
					level2 = (JSONObject) level1.getJSONObject("images");

					level3 = (JSONObject) level2
							.getJSONObject("standard_resolution");
					String imageURL = level3.get("url").toString();

					Log.d("API DEBUGGING FEEDBACK",
							"JSONOBject retreived from Insta API: " + imageURL);
					URL url = new URL(imageURL);
					 bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
					 Log.d("API DEBUGGING FEEDBACK",
								"Bitmap generated: " +  bmp.toString());

		//		}

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Bitmap getBitmap(){
		return bmp;
	}
}
