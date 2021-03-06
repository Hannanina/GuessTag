package com.example.guesstag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class is invoked in the beginning of a game session, when
 * one player is chosen to input the hashtag for guessing. this feature 
 * is not followed due to time limit. In this version, host is always the 
 * player who inputs hashtag.
 * 
 * @author group 6
 * 
 */

public class SelectTaggerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_tagger);
	}

	public void onClickSelect() {
		// toDo: disable button for other players and bring them to guessTag
		// Activity
		Intent intent = new Intent(this, InputTagActivity.class);
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_tagger, menu);
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
}
