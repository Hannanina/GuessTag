package com.example.guesstag;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BookAdapter extends ArrayAdapter<String> {

	
	public BookAdapter(Context context, ArrayList<String> highscore){
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
       //TextView list_element_text_title = (TextView) convertView.findViewById(R.id.list_element_text_title);
       //TextView list_element_text_author = (TextView) convertView.findViewById(R.id.list_element_text_author);
       // Populate the data into the template view using the data object
       //list_element_text_title.setText(book.getTitle());
       //list_element_text_author.setText(book.getAuthor());
       // Return the completed view to render on screen
       return convertView;
   }
}
