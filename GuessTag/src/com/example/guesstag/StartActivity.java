package com.example.guesstag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

public class StartActivity extends ActionBarActivity{
	
	HighscoreList hl = HighscoreList.getHighscoreList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		

        SharedPreferences listOfScores = getSharedPreferences("preferences",0);
        hl.loadChanges(listOfScores);
        
        popup();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_menu, menu);
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
	
	public void popup() {
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.user_name_popup , null, false), 100, 100, true);
			    
		// The code below assumes that the root container has an id called 'main'
		pw.showAtLocation(this.findViewById(R.id.button_start), Gravity.CENTER, 0, 0); 
	}
	
	public void onClickOk(View view) {
		
	}
	
	public void onClickStart(View view) {
		Intent intent = new Intent(this, NewActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Highscore not implemented yet.
	 */
	public void onClickHighscore(View view) {
		Intent intent = new Intent(this, HighscoreActivity.class);
		startActivity(intent);
	}
	
	public void onClickRules(View view) {
		Intent intent = new Intent(this, RulesActivity.class);
		startActivity(intent);
	}
}
