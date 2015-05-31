package puzzlePackage;

import java.awt.Image;

/** This class contains all the information needed for the sliding puzzle to be created.
 * @param name Contains the name of the puzzle
 * @param image Contains the image of the puzzle
 * @param difficulty contains the difficulty of the puzzle (from 1 to 3)
 * 
 */
public class PuzzleData {
	private String name;
	private Image image;
	private int difficulty;
	
	public PuzzleData(String name, Image image, int difficulty) {
		super();
		this.name = name;
		this.image = image;
		
		if( difficulty >3)
			this.difficulty = 3;
		else if (difficulty < 1)
			this.difficulty = 1;
		else
			this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public Image getImage() {
		return image;
	}

	public int getDifficulty() {
		return difficulty;
	}

	
	
}

