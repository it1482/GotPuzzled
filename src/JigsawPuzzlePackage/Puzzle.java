package JigsawPuzzlePackage;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public abstract class Puzzle  extends JPanel{

	
	
	public Puzzle(String name, BufferedImage loadedImage) {
	    super (false);
		this.name = name;

		this.loadedImage = loadedImage;
	}

	private String name;
	private int puzzleValue;
	private BufferedImage loadedImage;
	
}

