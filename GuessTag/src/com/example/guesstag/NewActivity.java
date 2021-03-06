package com.example.guesstag;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_menu, menu);
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
	
	public void onClickHost(View view) {
		Intent intent = new Intent(this, HostActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Highscore not implemented yet.
	 */
	public void onClickJoin(View view) {
		Intent intent = new Intent(this, JoinGameActivity.class);
		startActivity(intent);
	}
	
	public void onClickBack(View view) {
		Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}
}
