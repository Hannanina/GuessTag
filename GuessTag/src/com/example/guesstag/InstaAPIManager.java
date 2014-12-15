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

public class InstaAPIManager {
	String accessToken;

	public void initiateConnection() {
		try {
			URL example = new URL(
					"https://api.instagram.com/v1/users/self/media/recent?access_token="
							+ accessToken);

			URLConnection tc = example.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tc.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONObject ob = new JSONObject(line);

				JSONArray object = ob.getJSONArray("data");

				for (int i = 0; i < object.length(); i++) {

					JSONObject jo = (JSONObject) object.get(i);
					JSONObject nja = (JSONObject) jo.getJSONObject("photos");

					JSONObject purl3 = (JSONObject) nja
							.getJSONObject("thumbnail");
					// PhotoSet set = new PhotoSet();
					// set.setThumb(purl3.getString("url"));
					// thesets.add(set);

					// Log.i(TAG, "" + purl3.getString("url"));
				}

			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}

	}
}
