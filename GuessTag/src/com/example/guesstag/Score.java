package com.example.guesstag;

/**
 * This class creates instance of score which consists of many items such as
 * number of players, points(based on difficulty, time spent etc.)
 * 
 * @author group 6
 * 
 */

public class Score {
	
	private int nrOfPlayers;
	private int points;
	private String gameName;
	private String diffSetting;
	
	public Score(int nrOfPlayers, int points, String gameName, String diffSetting) {
		
		this.nrOfPlayers = nrOfPlayers;
		this.points = points;
		this.gameName = gameName;
		this.diffSetting = diffSetting;
	}

	public int getNrOfPlayers() {
		return nrOfPlayers;
	}

	public void setNrOfPlayers(int nrOfPlayers) {
		this.nrOfPlayers = nrOfPlayers;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getDiffSetting() {
		return diffSetting;
	}

	public void setDiffSetting(String diffSetting) {
		this.diffSetting = diffSetting;
	}
	
	
}
