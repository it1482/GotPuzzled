package puzzlePackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;


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
	


	public void setPuzzlesData(ArrayList<PuzzleData> puzzlesData) {
		this.puzzlesData = puzzlesData;
	}



	public ArrayList<PuzzleData> getPuzzles() {
		return puzzlesData;
	}





	public  ArrayList<String> getPuzzlesNames() {
		return puzzlesNames;
	}



	public void createPuzzle(String name,ImageIcon image, int difficulty){
		for(PuzzleData p : puzzlesData){
			if (p.getName()==name){
				System.out.println("This Puzzle already exists!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		puzzlesData.add(new PuzzleData(name,image,difficulty));

		
	}
	
	
	
	public LoadSavePuzzles getLoadsave() {
		return loadsave;
	}



	public void UpdatePuzzleNamesArrayList() {
		 for(int i=0;i<puzzlesData.size();i++){
			 puzzlesNames.add(puzzlesData.get(i).getName());
		 }

	}
	

	public static PuzzleData loadPuzzle(File file){
		PuzzleData puzzle = null;
		try{
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			puzzle = (PuzzleData) in.readObject();
			in.close();
			fileIn.close();		
		}catch(IOException e){
			System.out.println("");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		System.out.println("path complete - desirialized");
		return puzzle;
			
		}

	
	
	
	
	public void importPuzzle(PuzzleData p,ArrayList<PuzzleData> puzzles,ArrayList<String> puzzleNames){
		boolean flag = true;
		for(PuzzleData puzzle: puzzlesData)
			if(puzzle.getName().equals(p.getName())){
				System.out.println("This Puzzle already exists");
				flag = false;
				break;
			}
		if(flag){
			puzzles.add(p);
			puzzlesNames.add(p.getName());
			loadsave.save(puzzles);
			
		}				
		
	}
	
	public void exportPuzzle(String pname){
		PuzzleData puzzleToExport = null;
		for(PuzzleData p: puzzlesData){
			if(p.getName()==pname){
				puzzleToExport = p;
				String filename = pname + ".puz";
				try{;
					FileOutputStream fos = new FileOutputStream("C:/Users/Ares/Desktop/"+filename);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(puzzleToExport);
					oos.close();
					System.out.println("puzzles serialised");
					}
				catch (Exception e){
						e.printStackTrace();
				}
			break;
			}
		}
	}

}
