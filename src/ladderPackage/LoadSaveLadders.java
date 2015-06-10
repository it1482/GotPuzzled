package ladderPackage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleJigsawData;


public class LoadSaveLadders implements Serializable {
	private ArrayList<LadderChallenge> ladders  = new ArrayList<LadderChallenge>();
	
	
	public LoadSaveLadders(ArrayList<LadderChallenge> l){
		ladders = l;
	}
	
	
	public ArrayList<LadderChallenge> load(){
		ArrayList<LadderChallenge> l = null;
		File f = new File("laddersData.ser");
		if(f.exists() && !f.isDirectory()) {
			try{
				FileInputStream fis = new FileInputStream("laddersData.ser");
		         ObjectInputStream ois = new ObjectInputStream(fis);
		         l = (ArrayList<LadderChallenge>) ois.readObject();
		         ladders = l;
		         ois.close();
		         fis.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			initiate();
		}


		return ladders;
	}

	
	public void initiate(){
		ArrayList<PuzzleData> puzzles = new ArrayList<PuzzleData>();

		ImageIcon image =  new ImageIcon("images/8.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 8",image ,1,false));
		image =  new ImageIcon("images/3.jpg");
		puzzles.add(new PuzzleData("Puzzle 3",image ,1));
		image =  new ImageIcon("images/2.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 2",image ,1,true));
		image =  new ImageIcon("images/5.jpg");
		puzzles.add(new PuzzleJigsawData("Puzzle 5",image ,2,false));
		image =  new ImageIcon("images/12.jpg");
		puzzles.add(new PuzzleData("Puzzle 12",image ,2));
		LadderChallenge  ladder1 = new LadderChallenge("Easy Ladder",5,puzzles) ;
		
		ladders.add(ladder1);
	}
	public void save(ArrayList<LadderChallenge> ladders){
		try{
			FileOutputStream fos = new FileOutputStream("laddersData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ladders);
			oos.close();
			System.out.println("puzzles serialised");
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		
		
	}
	

}
