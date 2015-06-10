package MainPackage;

import java.awt.image.BufferedImage;
import java.util.Collections;

import JigsawPuzzlePackage.JigsawCutter;
import JigsawPuzzlePackage.JigsawFrame;
import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleDatabase;
import puzzlePackage.PuzzleJigsawData;

public class RunJigsaw2 {
	
	//Gets a puzzle from the database and runs it. Just for testing.

	public static void main(String[] args) {
		Database database = new Database();
		//database.getPlayersDatabase().test();
		for(int i=0;i<database.getPlayersDatabase().getPlayers().size();i++){
			System.out.println(database.getPlayersDatabase().getPlayers().get(i).getName() + database.getPlayersDatabase().getPlayers().get(i).getScore());
		}
		Collections.sort(database.getPlayersDatabase().getPlayers());
		for(int i=0;i<database.getPlayersDatabase().getPlayers().size();i++){
			System.out.println(database.getPlayersDatabase().getPlayers().get(i).getName() + database.getPlayersDatabase().getPlayers().get(i).getScore());
		}
	}

}
