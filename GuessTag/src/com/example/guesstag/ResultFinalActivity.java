package com.example.guesstag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultFinalActivity extends ActionBarActivity {
	
	SessionManager sm = SessionManager.getSessionManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_final);
		TextView textview = (TextView)findViewById(R.id.score);
		textview.setText(String.valueOf(sm.getScore().getPoints()));
		//todo: add final Score to your Highscore
		
	}
	
	public void onClickHighscore(View view) {
		//Intent intent = new Intent(this, HighscoreActivity.class);
	//	startActivity(intent);
	}
	
	public void onClickEnd(View view) {
		Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_final, menu);
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
