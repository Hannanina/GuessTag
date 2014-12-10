package com.example.guesstag;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class WaitHostActivity extends ActionBarActivity {

	private String[] listOfPlayers = new String[10];
	private OnItemClickListener clickList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_host);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfPlayers);

		ListView listView = (ListView) findViewById(R.id.listOfPlayers);
		listView.setAdapter(adapter);

		clickList = new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				//Intent intent = new Intent(this, WaitRoomGuest.class);
			//	intent.putExtra("id", position);
			//	startActivity(intent);
			}
		};

		listView.setOnItemClickListener(clickList);

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
		Intent intent = new Intent(this, HostActivity.class);
		startActivity(intent);
	}
}