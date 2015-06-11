package ladderPackage;

import java.io.Serializable;
import java.util.ArrayList;

import puzzlePackage.PuzzleData;

/** Ladder Challenges stores all the essential data needed to make Ladder Challenge work properly.
 * @param Name 
 * @param currentLevel keeps track of the player's process.
 * @param levelNumber give the number of levels.
 * @param puzzles ArrayList which contains all the puzzles of the ladder.
 * @param puzzlenames ArrayList that contains only the names of the puzzles for JList use.
 * 
 */

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

		for (int i = 0; i < puzzles.size(); i++) {
			puzzlesnames.add(puzzles.get(i).getName());

		}

	}

	public void addPuzzle(PuzzleData puzzle) {
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
