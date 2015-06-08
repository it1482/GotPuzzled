package puzzlePackage;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Puzzle extends JFrame{
	//fields
	private String name;
	private Image image;
	private int difficulty;
	public Puzzle(String name, Image image, int difficulty) {
		this.name = name;
		this.difficulty = difficulty;
		this.image = image;
	}
	
	public void reset(){
		
	}
	public void shuffle(){
		
	}
	public boolean checkIfWon(){
		return true;
	}
	
	public String getName(){
		return this.name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	

}
