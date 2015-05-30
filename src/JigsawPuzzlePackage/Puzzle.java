package JigsawPuzzlePackage;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public abstract class Puzzle  extends JPanel{

	
	
	public Puzzle(String name, int difficulty, BufferedImage loadedImage) {
	    super (false);
		this.name = name;
		this.difficulty = difficulty;
		this.loadedImage = loadedImage;
	}

	private String name;
	private int difficulty;
	private int puzzleValue;
	private BufferedImage loadedImage;
	
}

