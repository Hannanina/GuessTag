package com.example.guesstag;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class HostActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

	SeekBar diffSetting;
	TextView difficultyText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host);
		
		diffSetting = (SeekBar)findViewById(R.id.difficulty_setting);
		diffSetting.setOnSeekBarChangeListener(this);
		difficultyText = (TextView)findViewById(R.id.difficulty_setting_level);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.host, menu);
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
	
	
	
	/**
	 * onClick should transfer players to host waiting screen,
	 * store difficulty and game name variables in the SessionManager and
	 * start the networking...
	 * @param view
	 */
	public void onClickCreate(View view) {
		//Intent intent = new Intent(this, NewActivity.class);
		//startActivity(intent);
	}
	
	public void onClickBack(View view) {
		Intent intent = new Intent(this, NewActivity.class);
		startActivity(intent);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		if(diffSetting.getProgress() == 0){
			difficultyText.setText("easy");
			difficultyText.setTextColor(Color.GREEN);
		}else if(diffSetting.getProgress() == 1){
			difficultyText.setText("medium");
			difficultyText.setTextColor(Color.YELLOW);
		}else{
			difficultyText.setText("hard");
			difficultyText.setTextColor(Color.RED);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		//difficultyText.setText(diffSetting.getProgress());
	}
}