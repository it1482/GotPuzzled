package guiPackage;


import ladderPackage.LadderDatabase;
import playerPackage.PlayerDatabase;
import puzzlePackage.PuzzleDatabase;

public class Database {
	private PuzzleDatabase puzzleDatabase = new PuzzleDatabase();
	private LadderDatabase ladderDatabase = new LadderDatabase();

	private PlayerDatabase players = new PlayerDatabase();

	public PuzzleDatabase getPuzzleDatabase() {
		return puzzleDatabase;
	}
	public LadderDatabase getLadderDatabase() {
		return ladderDatabase;
	}

	public PlayerDatabase getPlayersDatabase() {
		return players;
	}
	
	


	
}
