package com.example.guesstag;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputTagActivity extends ActionBarActivity {
	
	SessionManager sm =  SessionManager.getSessionManager();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_tag);
	}
	public void onClickSubmit (){

        EditText hashtagEdit = (EditText)findViewById(R.id.input_hashtag);
        sm.checkInputTag(hashtagEdit.getText().toString());
        
		//ToDO: check if hashtag has been used, if so make textView visible
		TextView textView = (TextView) findViewById(R.id.used_hashtag);
        textView.setVisibility(View.INVISIBLE);
        
		 SharedPreferences listOfHashtags = getSharedPreferences("preferences",0);
	     sm.saveChanges(listOfHashtags);
	    
		Intent intent = new Intent(this, TimerActivity.class);
		startActivity(intent);
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
