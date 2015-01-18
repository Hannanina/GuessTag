package com.example.guesstag;

import java.util.ArrayList;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * This class is invoked when the score for a game session
 *  is modified and in need of updating.
 * 
 * @author group 6
 * 
 */

public class ScoreAdapter extends ArrayAdapter<HighscoreListItem> {
	
	HighscoreList hl = HighscoreList.getHighscoreList();
	
	public ScoreAdapter(Context context, ArrayList<HighscoreListItem> highscore){
		super(context, 0, highscore);
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       HighscoreListItem items = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
    	   convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_list_item, parent, false);
       } 
       // Lookup view for data population
       TextView game_name = (TextView) convertView.findViewById(R.id.game_name);
       TextView nr_of_guesses = (TextView) convertView.findViewById(R.id.nr_of_guesses);
       TextView total_time_spent = (TextView) convertView.findViewById(R.id.total_time_spent);
       TextView points = (TextView) convertView.findViewById(R.id.points);

       game_name.setText(items.getGameName());
       nr_of_guesses.setText(String.valueOf(items.getTotalNrOfGuesses()));
       total_time_spent.setText(String.valueOf(items.getTotalTimeSpent()));
       points.setText(String.valueOf(items.getPoints()));
       
       
      

       // Return the completed view to render on screen
       return convertView;
   }
}
