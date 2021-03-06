package com.example.guesstag;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * This class is invoked when a game session is started which means that a
 * hashtag has been selected and the guest players try to guess the correct
 * hashtag. It only display the timer on the host players screen.
 * 
 * @author group 6
 * 
 */

public class TimerActivity extends Activity {

	ProgressBar mProgressBar;
	CountDownTimer mCountDownTimer;
	int i = 0;
	TextView timerValue;
	HighscoreList hl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);

		hl = HighscoreList.getHighscoreList();
		timerValue = (TextView) findViewById(R.id.timerValue);
		mProgressBar = (ProgressBar) findViewById(R.id.circularProgressbar);
		mProgressBar.setProgress(i);
		SessionManager.getSessionManager().getScore().setDiffSetting("medium");

		if (SessionManager.getSessionManager().getDiffSetting().equals("easy")) {

			mCountDownTimer = new CountDownTimer(60000, 600) {

				@Override
				public void onTick(long millisUntilFinished) {
					timerValue.setText(String
							.valueOf(millisUntilFinished / 1000));
					i++;
					mProgressBar.setProgress(i);
				}

				@Override
				public void onFinish() {
					SessionManager.getSessionManager().incrementRoundsPlayed();
					goToResult(SessionManager.getSessionManager()
							.getRoundsPlayed());
				
				}
			}.start();
		} else if (SessionManager.getSessionManager().getDiffSetting()
				.equals("medium")) {

			mCountDownTimer = new CountDownTimer(30000, 300) {

				@Override
				public void onTick(long millisUntilFinished) {
					timerValue.setText(String
							.valueOf(millisUntilFinished / 1000));
					i++;
					mProgressBar.setProgress(i);
				}

				@Override
				public void onFinish() {
					SessionManager.getSessionManager().incrementRoundsPlayed();
					goToResult(SessionManager.getSessionManager()
							.getRoundsPlayed());
			
				}
			}.start();
		} else {

			mCountDownTimer = new CountDownTimer(15000, 150) {

				@Override
				public void onTick(long millisUntilFinished) {
					timerValue.setText(String
							.valueOf(millisUntilFinished / 1000));
					i++;
					mProgressBar.setProgress(i);

				}

				@Override
				public void onFinish() {
					SessionManager.getSessionManager().incrementRoundsPlayed();
					goToResult(SessionManager.getSessionManager()
							.getRoundsPlayed());
					
				}
			}.start();
		}
	}

	public void stopTimer() {
		mCountDownTimer.cancel();
		SessionManager.getSessionManager().incrementRoundsPlayed();
		goToResult(SessionManager.getSessionManager().getRoundsPlayed());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timer, menu);
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

	// Checks number of rounds played and navigate to either next round or final
	// result
	public void goToResult(int roundsPlayed) {

		if (roundsPlayed < 6) {
			Intent intent = new Intent(this, ResultRoundActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, ResultFinalActivity.class);
			startActivity(intent);
		}
	}
}
