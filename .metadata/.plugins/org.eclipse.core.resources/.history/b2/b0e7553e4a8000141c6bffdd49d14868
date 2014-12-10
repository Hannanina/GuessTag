package com.example.guesstag;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TimerActivity extends ActionBarActivity {

	ProgressBar mProgressBar;
	CountDownTimer mCountDownTimer;
	int i=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
       
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		
		mProgressBar=(ProgressBar)findViewById(R.id.circularProgressbar);
		mProgressBar.setProgress(i);
		mCountDownTimer=new CountDownTimer(30000,300) {

		        @Override
		        public void onTick(long millisUntilFinished) {
		    		TextView textview = (TextView)findViewById(R.id.textView1);
		    		textview.setText(millisUntilFinished / 1000 + " sec");
		            i++;
		            mProgressBar.setProgress(i);

		        }

		        @Override
		        public void onFinish() {
		    		TextView textview = (TextView)findViewById(R.id.textView1);
		    		textview.setText("Time is up");
		    		
		        }
		    };
		    mCountDownTimer.start();
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
