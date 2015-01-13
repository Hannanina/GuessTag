package com.example.guesstag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class RulesActivity extends Activity {

	InstaAPIManager im =  InstaAPIManager.getInstaAPIManager();
	ImageView imageview1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rules);
		/*
		setContentView(R.layout.insta_images);

		new Thread() {
			public void run() {
				im.initiateConnection();
				
			}
		}.start();
		imageview1 = (ImageView) findViewById(R.id.imageView1);
		imageview1.setImageBitmap(im.getBitmap());
		*/
	}

	public void initiate() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rules, menu);
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

	public void onClickBack(View view) {
		Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}
}
