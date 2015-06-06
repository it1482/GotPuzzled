package puzzlePackage;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import playerPackage.Player;

//NEED REWORKING
public class LoadSavePuzzles implements Serializable{
	private ArrayList<PuzzleData> puzzles = new ArrayList<PuzzleData>();
	
	public LoadSavePuzzles(ArrayList<PuzzleData> p){
		puzzles = p;
	}
	
	
	public ArrayList<PuzzleData> load(){
		ArrayList<PuzzleData> p = null;
		try{
			FileInputStream fis = new FileInputStream("puzzlesData.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         p = (ArrayList<PuzzleData>) ois.readObject();
	         puzzles = p;
	         ois.close();
	         fis.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return puzzles;
	}

	public void save (ArrayList<PuzzleData> puzzles){
		try{
			FileOutputStream fos = new FileOutputStream("puzzlesData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(puzzles);
			oos.close();
			System.out.println("puzzles serialised");
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		
	}
	
	public void importPuzzle(){
		PuzzleData puzzleData=null;
		
		JFileChooser fc = new JFileChooser(System.getProperty("user.home")+"\\Desktop\\");
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try{
				FileInputStream saveFile = new FileInputStream(file.getPath());
				ObjectInputStream save = new ObjectInputStream(saveFile);
				puzzleData = (PuzzleData) save.readObject();
				save.close();
				
			} catch (Exception exp){
				exp.printStackTrace();
				
			}
			
		}else{
			System.out.println("Bye");
			System.exit(0);
		}
	}

	
	public void exportPuzzle(Puzzle puzzle){
		
	}

}
