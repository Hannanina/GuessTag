package com.example.guesstag;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This class implements the activity which is invoked when a host player tries to
 * create a hashtag for the guest players to guess.
 * @author group 6
 *
 */


public class InputTagActivity extends Activity {
	
	SessionManager sm =  SessionManager.getSessionManager();

	private TextView textView;
	private EditText editText;
	private boolean goodTag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_tag);
		
		editText = (EditText)findViewById(R.id.input_hashtag);
		textView = (TextView)findViewById(R.id.used_hashtag);
		textView.setVisibility(View.INVISIBLE);
	}
	public void onClickSubmit (View view){
		

        sm.setTagName(editText.getText().toString());

            
		Intent intent = new Intent(this, TimerActivity.class);
		startActivity(intent);
	}
	
	public boolean checkIfUsed(String hashtag) {
		boolean canUseTag = true;
		
		
		return canUseTag;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_tag, menu);
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
