package MainPackage;

import optionsPackage.OptionDatabase;
import ladderPackage.LadderDatabase;
import playerPackage.PlayerDatabase;
import puzzlePackage.PuzzleDatabase;

public class Database {
	private PuzzleDatabase puzzleDatabase = new PuzzleDatabase();
	private LadderDatabase ladderDatabase = new LadderDatabase();
	private OptionDatabase options = new OptionDatabase();
	private PlayerDatabase players = new PlayerDatabase();

	public PuzzleDatabase getPuzzleDatabase() {
		return puzzleDatabase;
	}
	public LadderDatabase getLadderDatabase() {
		return ladderDatabase;
	}
	public OptionDatabase getOptions() {
		return options;
	}
	public PlayerDatabase getPlayersDatabase() {
		return players;
	}
	
	


	
}
