package com.example.guesstag;

public class HighscoreListItem extends Score {

	private int totalNrOfGuesses;
	private String totalTimeSpent;
	
	public HighscoreListItem(int nrOfPlayers, int points, String gameName,
			String diffSetting, int totalNrOfGuesses, String totalTimeSpent) {
		super(nrOfPlayers, points, gameName, diffSetting);
		
		this.totalNrOfGuesses = totalNrOfGuesses;
		this.totalTimeSpent = totalTimeSpent;
	}

	public int getTotalNrOfGuesses() {
		return totalNrOfGuesses;
	}

	public void setTotalNrOfGuesses(int totalNrOfGuesses) {
		this.totalNrOfGuesses = totalNrOfGuesses;
	}

	public String getTotalTimeSpent() {
		return totalTimeSpent;
	}

	public void setTotalTimeSpent(String totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}
}
