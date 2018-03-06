import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.*;
public class Scramble2 {

	File file;
	Scanner fileReader;
	Scramble2 S;
	String realWord, randomWord = "";
	boolean firstRead = true, getRealWord = true, getScrambledWord;
	int i = 1;
	String lastWord = null;
	ArrayList<String> words;
	
	public Scramble2(String s)throws IOException{
		file = new File(s);
		fileReader = new Scanner(file);
		words = new ArrayList<String>();//creates an arraylist to hold the words from the textfile
		while(fileReader.hasNext()){//fills the arraylist with the words from the testfile iteratively
			String a = fileReader.next();
			words.add(a);
		}
	}
	public String getRealWord(){
		if(getRealWord){//gate to open this method
			Random r = new Random();
			int x = r.nextInt(words.size()+1);
			if(x == 13){
				randomWord = words.get(x-1);
				i++;
			}
			else{
				randomWord = words.get(x);
				i++;
			}
			realWord = randomWord;
			lastWord = realWord;
			getScrambledWord = true;
			getRealWord = false;
			firstRead = false;
		}
		if(i > words.size()){
			i = 1;
			firstRead = true;
			return null;
		}
		return realWord;
	}
	public String getScrambledWord(){
		if(firstRead)
			return null;
		if(getScrambledWord || getRealWord){//2 stringbuilders to make a random word out of a normal word
			StringBuilder scrambledWord = new StringBuilder();
			StringBuilder original = new StringBuilder(getRealWord());
			Random rand = new Random(5);	
			while (original.length() != 0){
				int r = rand.nextInt(original.length());//scrambles the word
				scrambledWord.append(original.charAt(r)); 
				original = original.deleteCharAt(r);
		        }
			getRealWord = true;
			getScrambledWord = false;
			return scrambledWord.toString();
		}
		return null;
	}
	public void reset(){//opens the gates to the methods and resets the words
		getRealWord = true; 
		getScrambledWord = false;
		i = 1;
	}
}
