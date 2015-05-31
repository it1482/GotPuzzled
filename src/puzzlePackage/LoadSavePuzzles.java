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
	private ArrayList<Puzzle> puzzles = new ArrayList<Puzzle>();
	
	
	public ArrayList<Puzzle> load(){
		ArrayList<Puzzle> p = null;
		try{
			FileInputStream fis = new FileInputStream("puzzles.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         p = (ArrayList<Puzzle>) ois.readObject();
	         puzzles = p;
	         ois.close();
	         fis.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return puzzles;
	}

	public void save (ArrayList<Puzzle> puzzles){
		try{
			FileOutputStream fos = new FileOutputStream("puzzles.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(puzzles);
			oos.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		
	}
	
	public void importPuzzle(){
		Puzzle puzzle=null;
		
		JFileChooser fc = new JFileChooser(System.getProperty("user.home")+"\\Desktop\\");
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try{
				FileInputStream saveFile = new FileInputStream(file.getPath());
				ObjectInputStream save = new ObjectInputStream(saveFile);
				puzzle = (Puzzle) save.readObject();
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
