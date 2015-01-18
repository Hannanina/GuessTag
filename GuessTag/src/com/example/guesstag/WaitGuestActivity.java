package com.example.guesstag;

import java.util.ArrayList;
import java.util.Arrays;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This class is invoked when a guest player joins a game session that is still
 * hosted. guest player is able to see a list of all other players in the same
 * session.
 * 
 * @author group 6
 * 
 */

public class WaitGuestActivity extends Activity implements
		NetworkingEventHandler {

	private boolean backClick;
	private Gson gson = new Gson();
	private ArrayList<String> listOfPlayers = new ArrayList<String>();
	ArrayList<String> tempListOfPlayers = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	TextView waiting_for_players;
	// String hostname;
	String gameNameStr;
	String guestName;

	private NetworkingManager manager;

	private OnItemClickListener clickList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_guest);

		backClick = false;

		manager = new NetworkingManager(this, "group6", "guest");
		guestName = SessionManager.getSessionManager().getUserName();

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfPlayers);

		ListView listView = (ListView) findViewById(R.id.listOfPlayers);
		listView.setAdapter(adapter);

		clickList = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		};

		listView.setOnItemClickListener(clickList);
		monitorListOfPlayers();
		manager.monitorKeyOfUser("gameReady", gameNameStr);
	}

	public void monitorListOfPlayers() {
		manager.lockKeyOfUser("listOfPlayers", gameNameStr);
		manager.loadValueForKeyOfUser("listOfPlayers", gameNameStr);
		manager.monitorKeyOfUser("listOfPlayers", gameNameStr);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wait_guest, menu);
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

	public void onClickBack(View view) {
		backClick = true;
		manager.ignoreKeyOfUser("listOfPlayers", gameNameStr);
		manager.ignoreKeyOfUser("gameReady", gameNameStr);
		manager.lockKeyOfUser("listOfPlayers", gameNameStr);
		manager.loadValueForKeyOfUser("listOfPlayers", gameNameStr);
		Intent intent = new Intent(this, JoinGameActivity.class);
		startActivity(intent);
	}

	public void onClickFake(View view) {
		Intent intent = new Intent(this, GuessTagActivity.class);
		startActivity(intent);
	}

	@Override
	public void savedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub
		if (backClick == false) {

			try {
				Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
						"WaitGuestActivity: loadedValueForKeyOfUser: "
								+ " KEY " + key + " USER " + user
								+ " JSONSTRING " + json.get("value").toString());
				tempListOfPlayers.clear();
				tempListOfPlayers = gson.fromJson(json.getString("value")
						.toString(), new TypeToken<ArrayList<String>>() {
				}.getType());
				if (!(tempListOfPlayers == null)) {
					listOfPlayers.clear();
					listOfPlayers.addAll(tempListOfPlayers);
				}

				listOfPlayers.add(guestName);

				String jstring = gson.toJson(listOfPlayers);
				manager.saveValueForKeyOfUser("listOfPlayers", gameNameStr,
						jstring);

				Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
						"New Player added to the game: " + " PLAYERNAME "
								+ guestName + " JSONSTRING "
								+ jstring.toString());

				manager.unlockKeyOfUser("listOfPlayers", gameNameStr);

			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				Log.d(NetworkingManager.TAG_EVENT_COMPLETE, json.get("value")
						.toString());
				tempListOfPlayers.clear();
				tempListOfPlayers = gson.fromJson(json.getString("value")
						.toString(), new TypeToken<ArrayList<String>>() {
				}.getType());
				if (!(tempListOfPlayers == null)) {
					listOfPlayers.clear();
					listOfPlayers.addAll(tempListOfPlayers);
				}

				listOfPlayers.remove(guestName);

				String jstring = gson.toJson(listOfPlayers);
				manager.saveValueForKeyOfUser("listOfPlayers", gameNameStr,
						jstring);

				Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
						"New Player added to the game: " + " PLAYERNAME "
								+ guestName + " JSONSTRING "
								+ jstring.toString());

				manager.unlockKeyOfUser("listOfPlayers", gameNameStr);

			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// adapter.notifyDataSetChanged();
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
		try {
			System.out
					.println("OUTSIDE VALUECHANGED IN WAITGUEST. JSONSTRING IS: "
							+ json.getJSONArray("records").getJSONObject(0)
									.getString("value").toString());

			manager.lockKeyOfUser("gameReady", gameNameStr);
			if (json.getJSONArray("records").getJSONObject(0)
					.getString("value").toString().equals("true")) {
				System.out
						.println("INSIDE VALUECHANGED IN WAITGUEST. JSONSTRING IS: "
								+ json.getJSONArray("records").getJSONObject(0)
										.getString("value").toString());
				manager.unlockKeyOfUser("gameReady", gameNameStr);
				Intent intent = new Intent(this, GuessTagActivity.class);
				startActivity(intent);
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		manager.unlockKeyOfUser("gameReady", gameNameStr);
		manager.lockKeyOfUser("listOfPlayers", gameNameStr);
		Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
				"WaitGuestActivity: valueChangedForKeyOfUser: KEY= " + key
						+ "USER= " + user + " JSONSTRING " + json.toString());
		System.out.println("I AM OUTSIDE WAITGUESTACTIVITY!!!!!!!!!!!!!!!!!!");

		try {

			tempListOfPlayers.clear();
			tempListOfPlayers = gson.fromJson(json.getJSONArray("records")
					.getJSONObject(0).getString("value").toString(),
					new TypeToken<ArrayList<String>>() {
					}.getType());
			listOfPlayers.clear();
			listOfPlayers.addAll(tempListOfPlayers);
			System.out.println("I AM INSIDE WAITGUESTACTIVITY!:   "
					+ json.getJSONArray("records").getJSONObject(0)
							.getString("value").toString());

		} catch (JSONException e) {
			Log.e(NetworkingManager.TAG_ERROR, e.getMessage());
		}
		manager.unlockKeyOfUser("listOfPlayers", gameNameStr);

		adapter.notifyDataSetChanged();
		System.out
				.println("I AM OUTSIDE AGAIN!:   " + listOfPlayers.toString());

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
