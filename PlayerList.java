import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class PlayerList {
	
	private int totalWins = 0, totalLosses = 0, totalRounds = 0;//instance variables set to private for encapsulation
	private double percentWon;
	File file, tempFile;
	Scanner fileReader;
	BufferedWriter BW;
	FileWriter FW;
	BufferedReader BR;
	FileReader FR;
	PrintWriter PW;
	Player player;
	Player newPlayer;
	ArrayList<Player> PlayerDatabase;
	
	public PlayerList(String s)throws IOException{//PlayerList Constructor
		player = new Player();
		file = new File(s);
		fileReader = new Scanner(file);
		tempFile = new File(file.getAbsolutePath() + ".tmp");
		BR = new BufferedReader(new FileReader(file));
		FW = new FileWriter(file, true);
		PlayerDatabase = new ArrayList<Player>();
		String name;
		int rounds, wins, losses;
		while(fileReader.hasNext()){
			name = fileReader.next();
			rounds = fileReader.nextInt();
			totalRounds += rounds;
			wins = fileReader.nextInt();
			totalWins += wins;
			losses = (fileReader.nextInt());
			totalLosses += losses;
			player = new Player(name, rounds, wins, losses);
			PlayerDatabase.add(player);
		}
	}
	public Player getPlayer(String name){
		for(int i = 0; i < PlayerDatabase.size(); i++){//getting the name of the player object in the arraylist
			if(PlayerDatabase.get(i).getName().equals(name)){
				return PlayerDatabase.get(i);
			}
		}
		return null;
	}
	public void addPlayer(Player x)throws IOException{//adds the player by writing him/her into the textfile
		FW.write(x.toString());
	}
	public String toStringAdmin(){
		String str = "";
		percentWon = (double)totalWins/(double)totalRounds;
		String x = new DecimalFormat("#.##").format(percentWon);
		str = "Total Players: " + PlayerDatabase.size() + 
				"\n\t Total Rounds Played: " + totalRounds + 
				"\n\t Total Rounds Won: " + totalWins + 
				"\n\t Total Rounds Lost: " + totalLosses + 
				"\n\t Pct of Rounds Won: " + x + "%" +
				PlayerDatabase;
		return str;
	}
	public Player removePlayer(String rName) throws IOException{
		for(int i = 0; i < PlayerDatabase.size(); i++){//getting the name
			Player tempPlayer = getPlayer(rName);
			if(PlayerDatabase.get(i).getName().equals(rName)){//if the name passed in is the same as a name in the textfile
				PlayerDatabase.remove(tempPlayer);
				return tempPlayer;
			}
		}
		return null;
	}
	public void saveList(){//saves the data by storing it into the textfile
		try{
			FW = new FileWriter("players.txt", true);
			BW = new BufferedWriter(FW);
			for(int i = 0; i < PlayerDatabase.size(); i++){
				String name = PlayerDatabase.get(i).getName();
				int rounds = PlayerDatabase.get(i).getRounds();
				int wins = PlayerDatabase.get(i).getWins();
				int losses = PlayerDatabase.get(i).getLosses();
				BW.write(name + " " + rounds + " " + wins + " " + losses);
			}
		}
		catch(IOException e){
			System.out.println("Error");
		}
	}
	public String toString(){
		String str = "";
		percentWon = (double)totalWins/(double)totalRounds;
		String x = new DecimalFormat("#.##").format(percentWon);
		str = "Total Players: " + PlayerDatabase.size() + 
				"\n\t Total Rounds Played: " + totalRounds + 
				"\n\t Total Rounds Won: " + totalWins + 
				"\n\t Total Rounds Lost: " + totalLosses + 
				"\n\t Pct of Rounds Won: " + x + "%";
		return str;
	}
}
