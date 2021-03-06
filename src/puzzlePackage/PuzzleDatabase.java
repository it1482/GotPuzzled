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
	private ArrayList<String> puzzlesNames; // This array is needed for the
	// JList in order to show the names!

	public PuzzleDatabase() {
		super();
		this.puzzlesData = new ArrayList<PuzzleData>();
		this.loadsave = new LoadSavePuzzles(puzzlesData);
		this.puzzlesNames = new ArrayList<String>();
	}

	
	public void createPuzzle(String name, ImageIcon image, int difficulty) {
		for (PuzzleData p : puzzlesData) {
			if (p.getName() == name) {
				System.out.println("This Puzzle already exists!");
			}
		}
		puzzlesData.add(new PuzzleData(name, image, difficulty));

	}
	/**This method appends the difficulty to each puzzle's name
	 * 
	 */
	public void UpdatePuzzleNamesArrayList() {
		for (int i = 0; i < puzzlesData.size(); i++) {
			if (puzzlesData.get(i).getDifficulty() == 1)
				puzzlesNames.add(puzzlesData.get(i).getName() + " (Easy)");
			else if (puzzlesData.get(i).getDifficulty() == 2) {
				puzzlesNames.add(puzzlesData.get(i).getName() + " (Medium)");
			} else {
				puzzlesNames.add(puzzlesData.get(i).getName() + " (Hard)");
			}
		}

	}
	/**Deserializing a puzzle
	 * 
	 * @param file
	 * @return the puzzle to be loaded
	 */
	public static PuzzleData loadPuzzle(File file) {
		PuzzleData puzzle = null;
		try {
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			puzzle = (PuzzleData) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println("");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("path complete - desirialized");
		return puzzle;

	}
	/**Import a puzzle
	 * 
	 * @param p
	 * @param puzzles
	 * @param puzzleNames
	 */
	public void importPuzzle(PuzzleData p, ArrayList<PuzzleData> puzzles,
			ArrayList<String> puzzleNames) {
		boolean flag = true;
		for (PuzzleData puzzle : puzzlesData)
			if (puzzle.getName().equals(p.getName())) {
				System.out.println("This Puzzle already exists");
				flag = false;
				break;
			}
		if (flag) {
			puzzles.add(p);
			puzzlesNames.add(p.getName());
			loadsave.save(puzzles);

		}

	}
	/**Export a puzzle
	 * 
	 * @param pname
	 */
	public void exportPuzzle(String pname) {
		PuzzleData puzzleToExport = null;
		for (PuzzleData p : puzzlesData) {
			String temp_name;
			if (p.getDifficulty() == 1)
				temp_name = p.getName() + " (Easy)";
			else if (p.getDifficulty() == 2)
				temp_name = p.getName() + " (Medium)";
			else
				temp_name = p.getName() + " (Hard)";

			System.out.println(temp_name);
			System.out.println(pname);
			if (temp_name.equals(pname)) {
				puzzleToExport = p;
				String filename = pname + ".puz";
				try {
					String userHomeFolder = System.getProperty("user.home")
							+ "\\Desktop\\";
					FileOutputStream fos = new FileOutputStream(userHomeFolder
							+ filename);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(puzzleToExport);
					oos.close();
					System.out.println("Puzzle " + filename + " serialised");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public LoadSavePuzzles getLoadsave() {
		return loadsave;
	}

	public ArrayList<PuzzleData> getPuzzles() {
		return puzzlesData;
	}

	public ArrayList<String> getPuzzlesNames() {
		return puzzlesNames;
	}
	
	public void setPuzzlesData(ArrayList<PuzzleData> puzzlesData) {
		this.puzzlesData = puzzlesData;
	}


}
