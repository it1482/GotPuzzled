package puzzlePackage;
import java.awt.Image;


public abstract  class Puzzle {
	//fields
	private String name;
	private Image image;
	private String difficulty;
	private int puzzleValue;;
	public Puzzle(String name, Image image, int partsNumber) {
		super();
		this.name = name;
		this.image = image;
	}
	
	public void reset(){
		
	}
	public void shuffle(){
		
	}
	public boolean checkIfWon(){
		return true;
	}
	
	
	

}
