package com.example.guesstag;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class WaitHostActivity extends Activity implements
		NetworkingEventHandler {

	// private String[] listOfPlayers = new String[10];
	private ArrayList<String> listOfPlayers = new ArrayList<String>();
	ArrayList<String> tempListOfPlayers;

	ArrayAdapter<String> adapter;
	TextView waiting_for_players;
	String gameNameStr;
	String hostname;
	Gson gson;

	private NetworkingManager manager;

	private OnItemClickListener clickList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_host);
		Bundle bundle = getIntent().getExtras();
		gameNameStr = bundle.getString("GameName");
		gson = new Gson();
		// listOfPlayers =
		// SessionManager.getSessionManager().getListOfPlayers();

		manager = new NetworkingManager(this, "group6", "host");
		// manager.monitorKeyOfUser("listOfPlayers", "user1");
		// monitorListOfPlayers("user1", "user1");
		// String hostname = SessionManager.getSessionManager().getUserName();
		hostname = SessionManager.getSessionManager().getUserName();
		// monitorListOfPlayers(gameNameStr);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfPlayers);

		Button b;
		b = (Button) findViewById(R.id.button_start_game);

		ListView listView = (ListView) findViewById(R.id.listOfPlayers);
		listView.setAdapter(adapter);

		clickList = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			}
		};

		listView.setOnItemClickListener(clickList);
		listOfPlayers.add(hostname);
		String s = gson.toJson(listOfPlayers);
		manager.saveValueForKeyOfUser("listOfPlayers", gameNameStr, s);
		manager.monitorKeyOfUser("listOfPlayers", gameNameStr);
	}

	public void monitorListOfPlayers(String gamename) {
		// manager.saveValueForKeyOfUser("listOfPlayers", hostname, guestname);

		manager.monitorKeyOfUser("listOfPlayers", gamename);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wait_host, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClickStartGame(View view) {
		Intent intent = new Intent(this, SelectTaggerActivity.class);
		startActivity(intent);
	}

	public void onClickBack(View view) {
		manager.ignoreKeyOfUser("listOfPlayers", gameNameStr);
		Intent intent = new Intent(this, HostActivity.class);
		startActivity(intent);
	}

	@Override
	public void savedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

		Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
				"JSONOBject retreived in method savedValueForKeyOfUser: KEY= "
						+ key + "USER= " + user + " JSONSTRING "
						+ json.toString());

		/*
		 * try {
		 * 
		 * if(json.get("value").toString() != "" && json.get("value").toString()
		 * != null) {
		 * 
		 * listOfPlayers.add(json.get("value").toString()); }
		 * 
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		/*
		 * try {
		 * 
		 * 
		 * for (int i = 0; i < 10; i++) { if
		 * (json.getJSONArray("records").getJSONObject(i)
		 * .getString("key").equals("listOfPlayers")) {
		 * listOfPlayers.add(json.getJSONArray("records").getJSONObject(i)
		 * .getString("value")); } } } catch (JSONException e) {
		 * Log.e(NetworkingManager.TAG_ERROR, e.getMessage()); }
		 */

		// adapter.notifyDataSetChanged();
	}

	@Override
	public void loadedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletedKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void monitoringKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignoringKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChangedForKeyOfUser(JSONObject json, String key,
			String user) {
		// TODO Auto-generated method stub
		// waiting_for_players = (TextView)
		// findViewById(R.id.waiting_for_players);

		Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
				"WaitHostActivity: valueChangedForKeyOfUser: KEY= " + key
						+ "USER= " + user + " JSONSTRING " + json.toString());

		try {

			for (int i = 0; i < 100; i++) {
				if (json.getJSONArray("records").getJSONObject(i)
						.getString("key").equals("listOfPlayers")) {

					tempListOfPlayers = gson.fromJson(json.getString("value")
							.toString(), new TypeToken<ArrayList<String>>() {
					}.getType());
					
					listOfPlayers.clear();
					listOfPlayers.addAll(tempListOfPlayers);
				}
			}
		} catch (JSONException e) {
			Log.e(NetworkingManager.TAG_ERROR, e.getMessage());
		}

		adapter.notifyDataSetChanged();

	}

	@Override
	public void lockedKeyofUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlockedKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}
}