import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
public class Player {
	
	private String playerName;//instance variables set to private for encapsulation
	private int playerRounds = 0, playerWins = 0, playerLosses = 0;
	
	public Player(String nm){//constructor with just a name to start 
		playerName = nm;
		playerRounds = 0;
		playerWins = 0;
		playerLosses = 0;
	}
	public Player(String nm, int r, int w, int l){//constructor with all the info to start
		playerName = nm;
		playerRounds = r;
		playerWins = w;
		playerLosses = l;
	}
	public Player(){
		playerName = "";
		playerRounds = 0;
		playerWins = 0;
		playerLosses = 0;
	}
	public void addRounds(){
		playerRounds++;
	}
	public void addWins(){
		playerWins++;
	}
	public void addLosses(){
		playerLosses++;
	}
	public void setName(String nm){
		playerName = nm;
	}
	public void setRounds(int r){
		playerRounds = r;
	}
	public void setWins(int w){
		playerWins = w;
	}
	public void setLosses(int l){
		playerLosses = l;
	}
	public String getName(){
		return playerName;
	}
	public int getRounds(){
		return playerRounds;
	}
	public int getWins(){
		return playerWins;
	}
	public int getLosses(){
		return playerLosses;
	}
	public String toString(){
		String str;
		str =   "\n\tName: " + playerName +
				"\n\tTotal Rounds Played: " + playerRounds + 
				"\n\tTotal Rounds Won: " + playerWins + 
				"\n\tTotal Rounds Lost: " + playerLosses;
		return str;
	}
}
