package puzzlePackage;
import java.awt.Image;
import java.util.ArrayList;

import playerPackage.Player;

//NEEDS REWORKING
public class PuzzleDatabase {
	private ArrayList<PuzzleData> puzzlesData;
	private LoadSavePuzzles loadsave;
	
	
	public PuzzleDatabase() {
		super();
		this.puzzlesData = new ArrayList<PuzzleData>();
		this.loadsave = new LoadSavePuzzles(puzzlesData);
	}


	public ArrayList<PuzzleData> getPuzzles() {
		return puzzlesData;
	}



	public void createPuzzle(String name,Image image, int difficulty){
		for(PuzzleData p : puzzlesData){
			if (p.getName()==name){
				System.out.println("This Puzzle already exists!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		puzzlesData.add(new PuzzleData(name,image,difficulty));
		
	}

}
