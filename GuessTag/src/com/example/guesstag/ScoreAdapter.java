package com.example.guesstag;

import java.util.ArrayList;

import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ScoreAdapter extends ArrayAdapter<String> {
	
	HighscoreList hl = HighscoreList.getHighscoreList();
	
	public ScoreAdapter(Context context, ArrayList<String> highscore){
		super(context, 0, highscore);
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       String s = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_list_item, parent, false);
       } 
       // Lookup view for data population
       TextView game_name = (TextView) convertView.findViewById(R.id.game_name);
       TextView nr_of_guesses = (TextView) convertView.findViewById(R.id.nr_of_guesses);
       TextView total_time_spent = (TextView) convertView.findViewById(R.id.total_time_spent);
       TextView points = (TextView) convertView.findViewById(R.id.points);

       String[] name = new String[hl.count()];
       int i=0;
       for (HighscoreListItem item: hl.getAllScores()){
           name[i]= item.getGameName();
           i++;
       }
       
       game_name.setText(name[i]);
//       nr_of_guesses.setText(book.getAuthor());
//       total_time_spent.setText(book.getAuthor());
//       points.setText(book.getAuthor());

       // Return the completed view to render on screen
       return convertView;
   }
}
