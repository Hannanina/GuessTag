package com.example.guesstag;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;

public class HighscoreList {
	/*
	 * game name
	 * points
	 * guesses
	 * time spent
	 */
	
    private ArrayList<HighscoreListItem> allScores;
	private static HighscoreList highscoreList = new HighscoreList();
	
	private HighscoreList(){
		this.allScores = new ArrayList <HighscoreListItem>();
	}
	
	public static HighscoreList getHighscoreList() {
		return highscoreList;
	}
	
	public int count(){
		return allScores.size();
	}
	
    public HighscoreListItem getScore(int index) {
        return allScores.get(index);
    }
    public ArrayList<HighscoreListItem> getAllScores() {
        return allScores;
    }
    

	  public void saveChanges(SharedPreferences listOfScores ) {
	        Gson gson = new Gson();
	        String json = gson.toJson(allScores);
	        SharedPreferences.Editor editor = listOfScores.edit();
	        editor.putString("highscore list", json );
	        editor.commit();
	    }


	    public void loadChanges(SharedPreferences listOfScores ) {
	        Type listType = new TypeToken<ArrayList<HighscoreListItem>>() {}.getType();
	        Gson gson = new Gson();
	        String highscoreParameters = listOfScores.getString("highscore list", "");

	        if (highscoreParameters!="") {
	        	allScores = gson.fromJson(highscoreParameters, listType);
	        }
	    }

}
