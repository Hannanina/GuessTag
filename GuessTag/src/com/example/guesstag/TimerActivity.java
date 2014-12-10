package com.example.guesstag;


import android.R;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TimerActivity extends ActionBarActivity {

	ProgressBar mProgressBar;
	CountDownTimer mCountDownTimer;
	int i=1;
	TextView timerValue = (TextView)findViewById(R.id.timerValue);

    @Override
    public void onCreate(Bundle savedInstanceState) {
       
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		
		mProgressBar=(ProgressBar)findViewById(R.id.circularProgressbar);
		mProgressBar.setProgress(i);
		if(SessionManager.getSessionManager().getDiffSetting().equals("easy")) {
		
			mCountDownTimer=new CountDownTimer(60000,600) {

		        @Override
		        public void onTick(long millisUntilFinished) {
		    		timerValue.setText(millisUntilFinished / 1000 + " sec");
		            i++;
		            mProgressBar.setProgress(i);

		        }
		        
		        @Override
		        public void onFinish() {
		    		SessionManager.getSessionManager().calculatePoints(Integer.parseInt((timerValue.getText().toString())));
		    		//Intent intent = new Intent(this, ResultRound.class);
		    		//startActivity(intent);
		    		
		        }
		    };
		    mCountDownTimer.start();
		}
		else if(SessionManager.getSessionManager().getDiffSetting().equals("medium")) {
		
			mCountDownTimer=new CountDownTimer(30000,300) {

		        @Override
		        public void onTick(long millisUntilFinished) {
		    		timerValue.setText(millisUntilFinished / 1000 + " sec");
		            i++;
		            mProgressBar.setProgress(i);

		        }

		        @Override
		        public void onFinish() {
		    		SessionManager.getSessionManager().calculatePoints(Integer.parseInt((timerValue.getText().toString())));
		    		//Intent intent = new Intent(this, ResultRound.class);
		    		//startActivity(intent);
		    		
		        }
		    };
		    mCountDownTimer.start();
		}
		else {
		
			mCountDownTimer=new CountDownTimer(15000,150) {

		        @Override
		        public void onTick(long millisUntilFinished) {
		    		timerValue.setText(millisUntilFinished / 1000 + " sec");
		            i++;
		            mProgressBar.setProgress(i);

		        }

		        @Override
		        public void onFinish() {
		    		SessionManager.getSessionManager().calculatePoints(Integer.parseInt((timerValue.getText().toString())));
		    		//Intent intent = new Intent(this, ResultRound.class);
		    		//startActivity(intent);
		    		
		        }
		    };
		    mCountDownTimer.start();
		}
		
    }
    
    public void stopTimer() {
    	mCountDownTimer.cancel();
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
}
