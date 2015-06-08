package ladderPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import puzzlePackage.LoadSavePuzzles;
import puzzlePackage.PuzzleData;

public class LadderDatabase {
	private ArrayList<LadderChallenge> ladders;
	private LoadSaveLadders loadsave;
	private ArrayList<String> ladderNames; // This array is needed for the JList in order to show the names!


	
	public LadderDatabase() {
		super();
		this.ladders = new ArrayList<LadderChallenge>();
		this.loadsave =  new LoadSaveLadders(ladders);;
		this.ladderNames = new  ArrayList<String>();
	}



	public void createLadder(String name,ArrayList<PuzzleData> puzzles){
		for(LadderChallenge l : ladders){
			if (l.getName()==name){
				System.out.println("This Ladder already exists!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		ladders.add(new LadderChallenge(name,puzzles.size(),puzzles));

		
	}
	
	public void testDatabase(ArrayList<PuzzleData> puzzles) {
		ladders.add(new LadderChallenge("ladder1",puzzles.size(),puzzles))  ;
		ladderNames.add("ladder1");

		 

		//puzzlesData.add(new PuzzleJigsawData("Puzzle 1",image,1,true));
		//puzzlesNames.add("Puzzle 1");


	}
	
	public LoadSaveLadders getLoadsave() {
		return loadsave;
	}



	public ArrayList<String> getLadderNames() {
		return ladderNames;
	}



	public void setLadders(ArrayList<LadderChallenge> ladders) {
		this.ladders = ladders;
	}



	public ArrayList<LadderChallenge> getLadders() {
		return ladders;
	}
	
	


	
	

}
