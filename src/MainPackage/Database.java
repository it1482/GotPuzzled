package MainPackage;

import optionsPackage.OptionDatabase;
import ladderPackage.LadderDatabase;
import playerPackage.PlayerDatabase;
import puzzlePackage.PuzzleDatabase;

public class Database {
	private PuzzleDatabase puzzleDatabase;
	private LadderDatabase ladderDatabase;
	private OptionDatabase options;
	private PlayerDatabase players;

	public PuzzleDatabase getPuzzleDatabase() {
		return puzzleDatabase;
	}
	public LadderDatabase getLadderDatabase() {
		return ladderDatabase;
	}
	public OptionDatabase getOptions() {
		return options;
	}
	public PlayerDatabase getPlayers() {
		return players;
	}
	
	


	
}
