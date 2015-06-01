package puzzlePackage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import playerPackage.Player;

//NEEDS REWORKING
public class PuzzleDatabase {
	private ArrayList<PuzzleData> puzzlesData;
	private LoadSavePuzzles loadsave;
	
	
	
	
	public PuzzleDatabase() {
		super();
		this.puzzlesData = new ArrayList<PuzzleData>();
		this.loadsave = new LoadSavePuzzles(puzzlesData);
	}
	


	public ArrayList<PuzzleData> getPuzzles() {
		return puzzlesData;
	}



	public void createPuzzle(String name,Image image, int difficulty){
		for(PuzzleData p : puzzlesData){
			if (p.getName()==name){
				System.out.println("This Puzzle already exists!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		puzzlesData.add(new PuzzleData(name,image,difficulty));
		
	}
	
	
	//Just for testing
	public void testDatabase() {
		Image image = null;
		 try {                
			 image = ImageIO.read(new File("images/pic.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
		 

		puzzlesData.add(new PuzzleJigsawData("Puzzle 1",image,1,true));
	}

}
