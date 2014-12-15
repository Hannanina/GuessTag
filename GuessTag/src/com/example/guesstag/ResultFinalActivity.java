package com.example.guesstag;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultFinalActivity extends ActionBarActivity {
	
	SessionManager sm = SessionManager.getSessionManager();
	HighscoreList hl = HighscoreList.getHighscoreList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_final);
	
	    HighscoreListItem item = new HighscoreListItem(4, 70, "game1",
    			"diffSetting", 12 , 50);
        hl.getAllScores().add(item);
	    HighscoreListItem item2 = new HighscoreListItem(2, 100, "game2",
    			"diffSetting", 12 , 100);
        hl.getAllScores().add(item2);
	    HighscoreListItem item3 = new HighscoreListItem(2, 50, "game3",
    			"diffSetting", 12 , 70);
        hl.getAllScores().add(item3);
        
        SharedPreferences listOfScores = getSharedPreferences("preferences",0);
        hl.saveChanges(listOfScores);
        hl.loadChanges(listOfScores);

        
    	TextView textview = (TextView)findViewById(R.id.score);
		textview.setText(String.valueOf(item.getPoints()));
	}
	
	public void onClickHighscore(View view) {
		Intent intent = new Intent(this, HighscoreActivity.class);
		startActivity(intent);
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
