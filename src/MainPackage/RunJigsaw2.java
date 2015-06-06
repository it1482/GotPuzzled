package MainPackage;

import java.awt.image.BufferedImage;

import JigsawPuzzlePackage.JigsawCutter;
import JigsawPuzzlePackage.JigsawFrame;
import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleDatabase;
import puzzlePackage.PuzzleJigsawData;

public class RunJigsaw2 {
	
	//Gets a puzzle from the database and runs it. Just for testing.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleDatabase database = new PuzzleDatabase() ;
		database.testDatabase();
		JigsawCutter varCutter = null;
		PuzzleData temp_data = database.getPuzzles().get(0);
		if(temp_data instanceof PuzzleJigsawData){
			varCutter = new JigsawCutter(((PuzzleJigsawData)temp_data).getRotation());
			varCutter.setPreferredPieceCount(temp_data.getDifficulty());
		}
		//JigsawFrame frame = new JigsawFrame (((BufferedImage)temp_data.getImage()), varCutter, ((PuzzleJigsawData)temp_data).getRotation());
	    //frame.begin();
	   // frame.setSize (1024, 740);
	    //frame.show();

	}

}
