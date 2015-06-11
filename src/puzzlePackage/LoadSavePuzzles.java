package puzzlePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class LoadSavePuzzles implements Serializable {
	private ArrayList<PuzzleData> puzzles = new ArrayList<PuzzleData>();

	public LoadSavePuzzles(ArrayList<PuzzleData> p) {
		puzzles = p;
	}
	/*Deserialize the list of puzzles*/
	public ArrayList<PuzzleData> load() {
		ArrayList<PuzzleData> p = null;
		File f = new File("puzzlesData.ser");
		if (f.exists() && !f.isDirectory()) {
			try {
				FileInputStream fis = new FileInputStream("puzzlesData.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				p = (ArrayList<PuzzleData>) ois.readObject();
				puzzles = p;
				ois.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			initiate();
		}

		return puzzles;
	}
	
	/*Initializing the puzzles made by the program*/
	public void initiate() {
		ImageIcon image = new ImageIcon("images/1.jpg");
		puzzles.add(new PuzzleData("Puzzle 1", image, 3));
		image = new ImageIcon("images/2.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 2", image, 1, true));
		image = new ImageIcon("images/3.jpg");
		puzzles.add(new PuzzleData("Puzzle 3", image, 1));
		image = new ImageIcon("images/4.jpg");
		puzzles.add(new PuzzleData("Puzzle 4", image, 1));
		image = new ImageIcon("images/5.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 5", image, 2, false));
		image = new ImageIcon("images/6.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 6", image, 2, true));
		image = new ImageIcon("images/7.jpg");
		puzzles.add(new PuzzleData("Puzzle 7", image, 1));
		image = new ImageIcon("images/8.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 8", image, 1, false));
		image = new ImageIcon("images/9.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 9", image, 1, false));
		image = new ImageIcon("images/10.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 10", image, 3, false));
		image = new ImageIcon("images/11.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 11", image, 1, true));
		image = new ImageIcon("images/12.jpg");
		puzzles.add(new PuzzleData("Puzzle 12", image, 2));
	}
	
	/*Serialize the list of puzzles*/
	public void save(ArrayList<PuzzleData> puzzles) {
		try {
			FileOutputStream fos = new FileOutputStream("puzzlesData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(puzzles);
			oos.close();
			System.out.println("puzzles serialised");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
