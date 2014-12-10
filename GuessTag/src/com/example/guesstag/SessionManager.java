package com.example.guesstag;

import java.util.ArrayList;

public class SessionManager {

	//Attributes
	private Score score;
	private String tag;
	private int nrOfGuesses;
	private int roundsPlayed;
	private int totalNrOfGuesses;
	private int totalTimeSpent;
	private ArrayList<String> usedTags;
	private ArrayList<String> listOfPlayers;
	
	//Variables to check tags
	private Boolean correctFormatTag = true;
	private Boolean correctGuessTag = false;
	
	private static SessionManager sessionManager = new SessionManager();
	
	private SessionManager() {
		nrOfGuesses = 0;
		roundsPlayed = 0;
		totalNrOfGuesses = 0;
		totalTimeSpent = 0;
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
	
	public ArrayList<String> getListOfPlayers() {
		return listOfPlayers;
	}

	public void setListOfPlayers(ArrayList<String> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
	}
	
	//Called to check whether played round was last round
	public int getRoundsPlayed() {
		return roundsPlayed;
	}
	
	//Called when a round is finished
	public void incrementRoundsPlayed() {
		roundsPlayed++;
	}
	
	public void calculatePoints(int time) {
		totalTimeSpent = totalTimeSpent + (30 - time);
		totalNrOfGuesses = totalNrOfGuesses + nrOfGuesses;
		if(score.getDiffSetting().equals("easy")) {
			
			score.setPoints(score.getPoints() + (time/nrOfGuesses)); 
		}
		else if(score.getDiffSetting().equals("medium")) {
			
			score.setPoints(score.getPoints() + (time/nrOfGuesses)*2); 
		}
		else {
			
			score.setPoints(score.getPoints() + (time/nrOfGuesses)*4); 
		}
		
		nrOfGuesses = 0;
	}
	
	//Should only check tag format and if tag has been used before
	public Boolean checkInputTag(String tag) {
		correctFormatTag = true;
		tag = tag.trim();
		
		if(tag.equals("")){
			correctFormatTag = false;
			return correctFormatTag;
		}
		
		int size = usedTags.size();
		if(size != 0) {
		
			for(int i = 0; i < size; i++) {
			
				if(tag.equals(usedTags.get(i))) {
					correctFormatTag = false;
					return correctFormatTag;
				}
			}	
		}
		
		usedTags.add(tag);
		return correctFormatTag;
	}
	
	//Checks guessed tag against attribute tag.
	public Boolean checkGuessTag(String tag) {
		correctGuessTag = false;
		
		if(this.tag.equals(tag)) {
			correctGuessTag = true;
		}
		nrOfGuesses++;
		return correctGuessTag;
	}
}
