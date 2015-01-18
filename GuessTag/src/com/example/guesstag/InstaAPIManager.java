package com.example.guesstag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * This class implements the connection part with Instagram server and invokes
 * APIs. It also parses images from received URLs.
 * 
 * 
 * 
 * @author group 6
 * 
 */

public class InstaAPIManager {
	String accessToken = "15170786.1fb234f.196d7fd71cfa40a9aa12140c522a5c99";
	JSONObject level2;
	JSONObject level3;
	String tag;
	URL example;
	private ArrayList<Bitmap> listOfURLs = new ArrayList<Bitmap>();

	SessionManager sm = SessionManager.getSessionManager();

	private static InstaAPIManager instaManager = new InstaAPIManager();

	private InstaAPIManager() {
	}

	public static InstaAPIManager getInstaAPIManager() {
		return instaManager;
	}

	public void initiateConnection() {

		tag = sm.getTagName();

		try {
			example = new URL("https://api.instagram.com/v1/tags/" + "snow"
					+ "/media/recent?access_token=" + accessToken + "&count=6");

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

				for (int i = 0; i < object.length(); i++) {

					JSONObject level1 = (JSONObject) object.get(i);
					level2 = (JSONObject) level1.getJSONObject("images");

					level3 = (JSONObject) level2
							.getJSONObject("standard_resolution");

					String imageURL = level3.get("url").toString();

					URL url = new URL(imageURL);
					Bitmap bmp = BitmapFactory.decodeStream(url
							.openConnection().getInputStream());
					Log.d("API DEBUGGING FEEDBACK",
							"Bitmap generated: " + bmp.toString());

					listOfURLs.add(bmp);
					System.out.println("INSIDE IMAGE PARSING!!! ");

				}
				Log.d("API DEBUGGING FEEDBACK", "IMAGE URLs from Insta API: "
						+ listOfURLs.toString());

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

	public ArrayList<Bitmap> getBitmap() {
		return listOfURLs;
	}
}
