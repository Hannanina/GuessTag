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
    private ArrayList<HighscoreListItem> easyScores;
    private ArrayList<HighscoreListItem> mediumScores;
    private ArrayList<HighscoreListItem> hardScores;


	private static HighscoreList highscoreList = new HighscoreList();
	
	private HighscoreList(){
		this.allScores = new ArrayList <HighscoreListItem>();
		this.easyScores = new ArrayList <HighscoreListItem>();
		this.mediumScores = new ArrayList <HighscoreListItem>();
		this.hardScores = new ArrayList <HighscoreListItem>();

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
    
    public ArrayList<HighscoreListItem> getEasyScores(){
    	return easyScores;
    }    
    public ArrayList<HighscoreListItem> getMediumScores(){
    	return mediumScores;
    }
    
    public ArrayList<HighscoreListItem> getHardScores(){
    	return hardScores;
    }

	  public void saveChanges(SharedPreferences listOfScores ) {
	        Gson gson = new Gson();
	        String json = gson.toJson(easyScores);
	        String json2 = gson.toJson(mediumScores);
	        String json3 = gson.toJson(hardScores);

	        SharedPreferences.Editor editor = listOfScores.edit();
	        editor.putString("highscore list easy", json );
	        editor.putString("highscore list medium", json2 );
	        editor.putString("highscore list hard", json3 );

	        editor.commit();
	    }


	    public void loadChanges(SharedPreferences listOfScores ) {
	        Type listType = new TypeToken<ArrayList<HighscoreListItem>>() {}.getType();
	        Gson gson = new Gson();
	        String highscoreParameters = listOfScores.getString("highscore list easy", "");
	        String highscoreParameters2 = listOfScores.getString("highscore list medium", "");
	        String highscoreParameters3 = listOfScores.getString("highscore list hard", "");


	        if (highscoreParameters!="" && highscoreParameters2!="" && highscoreParameters3!="") {
	        	easyScores = gson.fromJson(highscoreParameters, listType);
	        	mediumScores = gson.fromJson(highscoreParameters2, listType);
	        	hardScores = gson.fromJson(highscoreParameters3, listType);

	        }
	    }

}
