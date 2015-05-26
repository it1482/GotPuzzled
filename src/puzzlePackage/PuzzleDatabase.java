package puzzlePackage;
import java.awt.Image;
import java.util.ArrayList;


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
		
	}

}
