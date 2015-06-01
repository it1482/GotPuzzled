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
	private ArrayList<String> puzzlesNames; // This array is needed for the JList in order to show the names!

	
	
	
	public PuzzleDatabase() {
		super();
		this.puzzlesData = new ArrayList<PuzzleData>();
		this.loadsave = new LoadSavePuzzles(puzzlesData);
		this.puzzlesNames = new  ArrayList<String>();
	}
	


	public ArrayList<PuzzleData> getPuzzles() {
		return puzzlesData;
	}





	public  ArrayList<String> getPuzzlesNames() {
		return puzzlesNames;
	}



	public void createPuzzle(String name,Image image, int difficulty){
		for(PuzzleData p : puzzlesData){
			if (p.getName()==name){
				System.out.println("This Puzzle already exists!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		puzzlesData.add(new PuzzleData(name,image,difficulty));

		
	}
	
	
	/**Just for testing creates a database to see if things are working properly.
	 *  
	 */
	public void testDatabase() {
		Image image = null;
		 try {                
			 image = ImageIO.read(new File("images/pic.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
		 

		puzzlesData.add(new PuzzleJigsawData("Puzzle 1",image,1,true));
		puzzlesNames.add("Puzzle 1");


	}

}
