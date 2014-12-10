package com.example.guesstag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class JoinGameActivity extends ActionBarActivity {

	private String[] listOfGames = new String[10];
	private OnItemClickListener clickList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_game);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listOfGames);

		ListView listView = (ListView) findViewById(R.id.listOfGames);
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

	public void onClickCreate(View view) {

		// Intent intent = new Intent(this, NewActivity.class);
		// startActivity(intent);
	}
}
