package com.example.guesstag;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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

/**
 * This class is invoked when a host player just create s game session. host
 * player is able to see a list of all other guest players whoever joint in this
 * session.
 * 
 * @author group 6
 * 
 */

public class WaitHostActivity extends Activity implements
		NetworkingEventHandler {

	// private String[] listOfPlayers = new String[10];
	private ArrayList<String> listOfPlayers = new ArrayList<String>();
	ArrayList<String> tempListOfPlayers = new ArrayList<String>();
	ArrayList<String> listOfGames = new ArrayList<String>();

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

		manager = new NetworkingManager(this, "group6", "host");
	
		hostname = SessionManager.getSessionManager().getUserName();


		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfPlayers);

		ListView listView = (ListView) findViewById(R.id.listOfPlayersHost);
		listView.setAdapter(adapter);

		Button b;
		b = (Button) findViewById(R.id.button_start_game);

		clickList = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
			}
		};

		listView.setOnItemClickListener(clickList);
		listOfPlayers.add(hostname);
		String s = gson.toJson(listOfPlayers);
		manager.lockKeyOfUser("listOfPlayers", gameNameStr);
		manager.saveValueForKeyOfUser("listOfPlayers", gameNameStr, s);

		manager.monitorKeyOfUser("listOfPlayers", gameNameStr);
	}

	public void monitorListOfPlayers(String gamename) {

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
		manager.ignoreKeyOfUser("listOfPlayers", gameNameStr);
		manager.lockKeyOfUser("listOfGames", "games");
		manager.loadValueForKeyOfUser("listOfGames", "games");
		SessionManager.getSessionManager().setListOfPlayers(listOfPlayers);
		manager.saveValueForKeyOfUser("gameReady", gameNameStr, "true");
		Intent intent = new Intent(this, InputTagActivity.class);
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


		manager.unlockKeyOfUser("listOfPlayers", gameNameStr);
	}

	@Override
	public void loadedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub
		try {
			Log.d(NetworkingManager.TAG_EVENT_COMPLETE, json.get("value")
					.toString());
			tempListOfPlayers.clear();
			tempListOfPlayers = gson.fromJson(json.getString("value")
					.toString(), new TypeToken<ArrayList<String>>() {
			}.getType());
			if (!(tempListOfPlayers == null)) {
				listOfGames.clear();
				listOfGames.addAll(tempListOfPlayers);
			}

			listOfGames.remove(gameNameStr);

			String jstring = gson.toJson(listOfGames);
			manager.saveValueForKeyOfUser("listOfGames", "games", jstring);

			Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
					"New Player added to the game: " + " GAMENAME "
							+ gameNameStr + " JSONSTRING " + jstring.toString());

			manager.unlockKeyOfUser("listOfGames", "games");

		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		manager.lockKeyOfUser("listOfPlayers", gameNameStr);
		try {
			Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
					"WaitHostActivity: valueChangedForKeyOfUser: " + " KEY= "
							+ key + "USER= " + user + " JSONSTRING "
							+ json.toString());


			tempListOfPlayers.clear();
			tempListOfPlayers = gson.fromJson(json.getJSONArray("records")
					.getJSONObject(0).getString("value").toString(),
					new TypeToken<ArrayList<String>>() {
					}.getType());

			manager.unlockKeyOfUser("listOfPlayers", gameNameStr);
			listOfPlayers.clear();
			listOfPlayers.addAll(tempListOfPlayers);
			// }
			// }
		} catch (JSONException e) {
			Log.e(NetworkingManager.TAG_ERROR, e.getMessage());
		}
		System.out.println("IM OUTSIDE " + listOfPlayers.toString());
		// adapter.clear();
		// adapter.addAll(listOfPlayers);
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