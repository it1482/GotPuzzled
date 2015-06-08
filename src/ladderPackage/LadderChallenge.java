package ladderPackage;
import java.io.Serializable;
import java.util.ArrayList;

import puzzlePackage.Puzzle;
import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleDatabase;

public class LadderChallenge implements Serializable {
	private String Name;
	private int currentLevel = 0;
	private int levelNumber = 0;
	private ArrayList<PuzzleData> puzzles = new ArrayList<PuzzleData>();
	private ArrayList<String> puzzlesnames = new ArrayList<String>();
	
	
	
	public LadderChallenge(String name, int levelNumber,
		ArrayList<PuzzleData> puzzles) {
		super();
		Name = name;
		this.levelNumber = levelNumber;
		this.puzzles = puzzles;
		
		for(int i=0;i<puzzles.size();i++ ){
			puzzlesnames.add(puzzles.get(i).getName());
			
		}
		
	}


	public void addPuzzle(PuzzleData puzzle){
		puzzles.add(puzzle);
		levelNumber++;
	}
	
	
	public ArrayList<PuzzleData> getPuzzles() {
		return puzzles;
	}
	
	public ArrayList<String> getPuzzlesnames() {
		return puzzlesnames;
	}


	public void setPuzzles(ArrayList<PuzzleData> puzzles) {
		this.puzzles = puzzles;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	public int getLevelNumber() {
		return levelNumber;
	}


	public String getName() {
		return Name;
	}
	
	


}
