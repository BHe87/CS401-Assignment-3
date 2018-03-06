import java.io.IOException;
import java.util.Scanner;
public class Assig3 {
	public static int tries = 3, rounds = 0, wins = 0, losses = 0;;
	public static String str;
	
	public static void main (String [] args) throws IOException{
		Scramble2 S = new Scramble2("words.txt");//instantiating an instance of the Scramble2 Class
		PlayerList PL = new PlayerList("players.txt");//instantiating an instance of the PlayerList Class
		Player P = null;//declaring an instance of the Player Class
		int game = 0;
		do{
		Scanner reader = new Scanner(System.in);
		System.out.println("Greetings!, What Is Your Name?");
		String playerName = reader.nextLine();
		if(PL.getPlayer(playerName) != null){//checks if the player is already in the system
			P = PL.getPlayer(playerName);//instantiates the player object
			//System.out.println("Player Got");
		}
		else{
			P = new Player(playerName);//creates a new player object
			//System.out.println("Player Added");
		}
		do{
			String scrambledWord = "", playerGuess = "", correctAnswer = "";
			System.out.println("Welcome " + playerName + " To The Game! You Have 3 Tries to Guess a Word Given a Scrambled Version of It");
			while (tries > 0 ){
				correctAnswer = S.getRealWord();
				scrambledWord = S.getScrambledWord();//Calling method to scramble the word
				System.out.println(scrambledWord);
				System.out.println("What Is This Word? You Have " + tries + " Tries Left");
				playerGuess = reader.nextLine();
					if (playerGuess.equalsIgnoreCase(correctAnswer)){//If the user gets it correct
						System.out.println("Correct!");
						if(P != null){
							P.addWins();//increments wins
							P.addRounds();//increments rounds
						}
						tries = 3;
						if(P != null){
							System.out.println(P.toString());//shows the data
						}
						if(!PlayAgain(reader)){
							System.out.println("Thanks for Playing!");
							tries = 0;
							if(P != null){
								System.out.println(P.toString());
							}
							System.exit(0);
						}
						else{
							System.out.println("Let's Play Again!");
						}
					}
					else if (!playerGuess.equalsIgnoreCase(correctAnswer)){//if the user doesn't get it correct	
						tries--;
						while(!playerGuess.equalsIgnoreCase(correctAnswer) && tries > 0){
							System.out.println("Try Again! You Have " + tries + " Tries Left. Here Are The Letters You Got Correct: ");	
							StringBuilder correctAnswerBuilder = new StringBuilder(correctAnswer);
							StringBuilder playerGuessBuilder = new StringBuilder(playerGuess);
							LetterCheck(correctAnswerBuilder, playerGuessBuilder);
							playerGuess = reader.nextLine();
							if (playerGuess.equalsIgnoreCase(correctAnswer)){
								System.out.println("Correct!");
								if(P != null){
									P.addWins();
									P.addRounds();
								}
								if(P != null){
									System.out.println(P.toString());
								}
								tries = 3;
								PlayAgain(reader);
							}
							else if (!playerGuess.equalsIgnoreCase(correctAnswer)){
								tries--;
								if(tries == 0){
								System.out.println("Out of Tries!");
								if(P != null){
									P.addLosses();
									P.addRounds();
									System.out.println(P.toString());
									}
								}
							}
						}
					}
				}
				System.out.println("The Word Was: " + correctAnswer);
				if(P != null)
					P.toString();
				if(!PlayAgain(reader)){
					System.out.println("Thanks for Playing!");
					System.out.println(P.toString());
					tries = 0;
					if(P != null)
						P.addRounds();
					System.exit(0);
				}
				else{
					System.out.println("Let's Play Again!");
				}
			}
			while(tries != 0);
		}
		while(game == 0);
	}
	public static void LetterCheck(StringBuilder correctAnswerBuilder, StringBuilder playerGuessBuilder){//shows the letters the user got correct in their guess
		while(playerGuessBuilder.length() != correctAnswerBuilder.length()){
			if(playerGuessBuilder.length() < correctAnswerBuilder.length()){
				playerGuessBuilder.append("_");
			}
			else if(playerGuessBuilder.length() > correctAnswerBuilder.length()){
				playerGuessBuilder.delete(correctAnswerBuilder.length(), playerGuessBuilder.length());
			}
		}
		for(int i = 0; i < correctAnswerBuilder.length(); i++){
			if(correctAnswerBuilder.charAt(i) == playerGuessBuilder.charAt(i)){
				System.out.print(correctAnswerBuilder.charAt(i));
			}
			else if (correctAnswerBuilder.charAt(i) != playerGuessBuilder.charAt(i))
				System.out.print("_");
		}
		System.out.println("");
	}
	public static boolean PlayAgain(Scanner reader){//Continues the program if they say Y
		System.out.println("Would You Like To Play Again? (Y/N)");
		String playerAgain = reader.nextLine();
		if(playerAgain.equalsIgnoreCase("Y")){
			tries = 3;
			return true;
		}
		else{
			return false;
		}
	}
}
