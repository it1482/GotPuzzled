package puzzlePackage;
import java.awt.Image;

import javax.swing.JPanel;


public class Puzzle {
	//fields
	private String name;
	private Image image;
	private int type;
	private String difficulty;
	private int puzzleValue;;
	public Puzzle(String name,int type, Image image, int partsNumber) {
		this.name = name;
		this.type = type;
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
	
	

}
