package com.example.guesstag;

public class HighscoreListItem extends Score {

	private int totalNrOfGuesses;
	private int totalTimeSpent;
	
	public HighscoreListItem(int nrOfPlayers, int points, String gameName,
			String diffSetting, int totalNrOfGuesses, int totalTimeSpent) {
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

	public int getTotalTimeSpent() {
		return totalTimeSpent;
	}

	public void setTotalTimeSpent(int totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}
}
