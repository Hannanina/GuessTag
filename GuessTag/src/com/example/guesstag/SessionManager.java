package com.example.guesstag;

import java.util.ArrayList;

public class SessionManager {

	//Attributes
	private Score score;
	private String tag;
	private int nrOfGuesses;
	private int timeSpent;
	private ArrayList<String> usedTags;
	private ArrayList<String> listOfPlayers;
	
	//Variables to check tags
	private Boolean correctFormatTag = true;
	private Boolean correctGuessTag = false;
	
	private static SessionManager sessionManager = new SessionManager();
	
	private SessionManager() {
		
	}
	
	//SessionManager is a singleton class,
	//so all interaction with should be through this static call.
	public static SessionManager getSessionManager() {
		return sessionManager;
	}
	
	public Score getScore() {
		return score;
	}
	
	//Should be called early to fill relevant information into Score class.
	public void setScore(int nrOfPlayers, int points, String gameName, String diffSetting) {
		this.score.setNrOfPlayers(nrOfPlayers);
		this.score.setPoints(points);
		this.score.setGameName(gameName);
		this.score.setDiffSetting(diffSetting);
	}
	
	public String getDiffSetting() {
		return getScore().getDiffSetting();
	}
	
	//Should only check tag format and if tag has been used before
	public Boolean checkInputTag(String tag) {
		correctFormatTag = true;
		tag = tag.trim();
		
		if(tag.equals("")){
			correctFormatTag = false;
		}
		
		int size = usedTags.size();
		if(size != 0) {
		
			for(int i = 0; i < size; i++) {
			
				if(tag.equals(usedTags.get(i))) {
					correctFormatTag = false;
					break;
				}
			}	
		}
		
		return correctFormatTag;
	}
	
	//Checks guessed tag against attribute tag.
	public Boolean checkGuessTag(String tag) {
		correctGuessTag = false;
		
		if(this.tag.equals(tag)) {
			correctGuessTag = true;
		}
		return correctGuessTag;
	}
}
