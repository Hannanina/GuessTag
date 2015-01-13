package com.example.guesstag;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class JoinGameActivity extends Activity implements
		NetworkingEventHandler {

	private ArrayList<String> listOfGames = new ArrayList<String>();
	private OnItemClickListener clickList;
	private TextView testing;
	private NetworkingManager manager;
	
	Gson gson=new Gson();
	ArrayAdapter<String> adapter;
	String clickedName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_game);

		manager = new NetworkingManager(this, "group6", "client");

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfGames);

		ListView listView = (ListView) findViewById(R.id.listOfGames);
		listView.setAdapter(adapter);

		clickList = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				 clickedName=(String)adapter.getItem(position);

				goToWaitGuestActivity(clickedName);
				

			}
		};

		listView.setOnItemClickListener(clickList);
		manager.loadValueForKeyOfUser("listOfGames", "games");
	}

	public void goToWaitGuestActivity(String name) {
		Intent intent = new Intent(this, WaitGuestActivity.class);
		intent.putExtra("GameName", name);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join_game, menu);
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
		Intent intent = new Intent(this, NewActivity.class);
		startActivity(intent);
	}

	public void onClickRefresh(View view) {
		manager.loadValueForKeyOfUser("listOfHosts", "hosts");
		//manager.loadValueForKeyOfUser("createGame", "user1");

	}

	public void valueChangedForKeyOfUser(JSONObject json, String key,
			String user) {

	}

	@Override
	public void savedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub
		testing = (TextView) findViewById(R.id.testing);

//		Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
//				"JSONOBject retreived in method loadedValue + "
//						+ "forKeyOfUser: " + json.toString());

		ArrayList<String> tempListOfGames;
		try {
			tempListOfGames = gson.fromJson(json.get("value").toString(), new TypeToken<ArrayList<String>>() {}.getType());
		
			if(!(tempListOfGames == null)){
				listOfGames.addAll(tempListOfGames);
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			String[] host1 = gson.fromJson((String) json.get("value"),
					String[].class);
            listOfGames.addAll(Arrays.asList(host1));
		
			Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
					"jsonHosts parsed from gson: " + listOfGames.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		adapter.notifyDataSetChanged();
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
	public void lockedKeyofUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlockedKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub

	}
}
