package ladderPackage;
import java.util.ArrayList;

import puzzlePackage.Puzzle;
import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleDatabase;

public class LadderChallenge {
	private int currentLevel = 0;
	private int levelNumber = 0;
	private ArrayList<PuzzleData> puzzles = new ArrayList<PuzzleData>();
	
	
	public void addPuzzle(PuzzleData puzzle){
		puzzles.add(puzzle);
		levelNumber++;
	}
	public ArrayList<PuzzleData> getPuzzles() {
		return puzzles;
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


}
