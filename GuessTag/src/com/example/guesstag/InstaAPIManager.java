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

import android.util.Log;
import android.widget.ImageView;

public class InstaAPIManager {
	String accessToken = "15170786.1fb234f.196d7fd71cfa40a9aa12140c522a5c99 HTTP/1.1";
	ImageView img1;
	JSONObject nja;
	
	public void initiateConnection() {
//	img1 = (ImageView) findViewById(R.id.imageView1);

		try {
			URL example = new URL(
					"https://api.instagram.com/v1/media/popular?access_token=" + accessToken);

			URLConnection tc = example.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONObject ob = new JSONObject(line);

				JSONArray object = ob.getJSONArray("data");

		//		for (int i = 0; i < object.length(); i++) {

					JSONObject jo = (JSONObject) object.get(1);
					nja = (JSONObject) jo.getJSONObject("standard_resolution");

					JSONObject purl3 = (JSONObject) nja
							.getJSONObject("thumbnail");
								        
					Log.d("API DEBUGGING FEEDBACK","JSONOBject retreived from Insta API + "+ nja.toString());	
					// PhotoSet set = new PhotoSet();
					// set.setThumb(purl3.getString("url"));
					// thesets.add(set);

					// Log.i(TAG, "" + purl3.getString("url"));
				}

		//	}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}

	}
	public JSONObject getImage(){
		return nja;
	}
	
	public void imageParsing(){
    //   Bitmap bitmap = BitmapFactory.decodeStream(nja);

	}
}
