package puzzlePackage;

import java.awt.Image;

/**This class inherits all the information that puzzleData has plus the information a jigsaw puzzle needs.
 * It contains a new variable called rotation which decides if the jigsawPuzzle will have rotation functionality.
 * @param rotation set the rotation functionality
 */
public class puzzleJigsawData extends PuzzleData {
	private boolean rotation;

	public puzzleJigsawData(String name, Image image, int difficulty,
			boolean rotation) {
		super(name, image, difficulty);
		this.rotation = rotation;
	}

	public boolean getRotation() {
		return rotation;
	}
	
	
}
