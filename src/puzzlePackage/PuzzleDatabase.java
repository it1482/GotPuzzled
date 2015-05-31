package puzzlePackage;
import java.awt.Image;
import java.util.ArrayList;

import playerPackage.Player;

//NEEDS REWORKING
public class PuzzleDatabase {
	private ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
	private LoadSavePuzzles loadsave;
	
	
	public PuzzleDatabase() {
		super();
		this.puzzles = puzzles;
		this.loadsave = loadsave;
	}


	public ArrayList<Puzzle> getPuzzles() {
		return puzzles;
	}



	public void createPuzzle(String name, int type, Image image, int size){
		for(Puzzle p : puzzles){
			if (p.getName()==name){
				System.out.println("This Puzzle already exists!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		puzzles.add(new Puzzle(name,type,image,size));
		
	}

}
