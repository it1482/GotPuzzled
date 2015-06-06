package puzzlePackage;

import java.awt.Image;

import javax.swing.ImageIcon;

/**This class inherits all the information that puzzleData has plus the information a jigsaw puzzle needs.
 * It contains a new variable called rotation which decides if the jigsawPuzzle will have rotation functionality.
 * @param rotation set the rotation functionality
 */
public class PuzzleJigsawData extends PuzzleData {
	private boolean rotation;

	public PuzzleJigsawData(String name, ImageIcon image, int difficulty,
			boolean rotation) {
		super(name, image, difficulty);
		this.rotation = rotation;
	}

	public boolean getRotation() {
		return rotation;
	}
	
	
}
