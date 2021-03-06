package com.example.guesstag;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * This class is invoked when one game session is ended either fail or success,
 * it displays a final result of the game session just finished which contains
 * items such as score, time spent etc.
 * 
 * @author group 6
 * 
 */

public class ResultFinalActivity extends Activity {

	SessionManager sm = SessionManager.getSessionManager();
	HighscoreList hl = HighscoreList.getHighscoreList();
	HighscoreListItem item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_final);


		item = new HighscoreListItem(sm.getScore().getNrOfPlayers(), sm
				.getScore().getPoints(), sm.getScore().getGameName(),
				sm.getDiffSetting(), sm.getNrOfGuesses(),
				calcFinalTime(sm.getTotalTimeSpent()));

		if (item.getDiffSetting().equals("easy")) {
			hl.getEasyScores().add(item);
		} else if (item.getDiffSetting().equals("medium")) {
			hl.getMediumScores().add(item);
		} else {
			hl.getHardScores().add(item);
		}

		SharedPreferences listOfScores = getSharedPreferences("preferences", 0);
		hl.saveChanges(listOfScores);
		hl.loadChanges(listOfScores);

		TextView textview = (TextView) findViewById(R.id.score);
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

	public String calcFinalTime(int time) {
		String finalTime;
		Integer minutes;
		Integer seconds;

		minutes = time / 60;
		seconds = time % 60;

		finalTime = minutes.toString() + ":" + seconds.toString();
		System.out.println("Final time is: " + finalTime);
		return finalTime;
	}
}
