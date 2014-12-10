package com.example.guesstag;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

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

public class JoinGameActivity extends ActionBarActivity implements NetworkingEventHandler {

	private ArrayList<String> listOfGames = new ArrayList<String>();
	private OnItemClickListener clickList;
	private TextView serverMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_game);
		listOfGames.add("First Game");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfGames);

		ListView listView = (ListView) findViewById(R.id.listOfGames);
		listView.setAdapter(adapter);

		clickList = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// Intent intent = new Intent(this, WaitRoomGuest.class);
				// intent.putExtra("id", position);
				// startActivity(intent);
			}
		};

		listView.setOnItemClickListener(clickList);

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

	public void onClickJoin(View view) {

		// Intent intent = new Intent(this, NewActivity.class);
		// startActivity(intent);
	}

	public void valueChangedForKeyOfUser(JSONObject json, String key,
			String user) {
		// TODO Auto-generated method stub

		// TODO Do something with returned values?

		Log.d(NetworkingManager.TAG_EVENT_COMPLETE,
				"JSONOBject retreived in method valueChanged + "
						+ "forKeyOfUser: " + json.toString());
		try {
			serverMsg.setText(json.getJSONArray("records").getJSONObject(0)
					.getString("value"));
		} catch (JSONException e) {
			Log.e(NetworkingManager.TAG_ERROR, e.getMessage());
		}

		listOfGames.add(serverMsg.toString());

	}

	@Override
	public void savedValueForKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub
		
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
	public void lockedKeyofUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockedKeyOfUser(JSONObject json, String key, String user) {
		// TODO Auto-generated method stub
		
	}
}
