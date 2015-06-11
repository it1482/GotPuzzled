package playerPackage;

import java.util.ArrayList;
import java.util.Collections;
/** PlayerDatabase contains all the Player database and important methods.
 * @param players ArrayList that contains all players 
 * @param loadsave save/load class
 * @param names Only for JList-GUI use.
 * @param scores Only for JList-GUI use.
 * 
 */

public class PlayerDatabase {

	private ArrayList<Player> players;
	private ArrayList<String> names;
	private ArrayList<String> scores;
	private LoadSavePlayers loadsave;

	public PlayerDatabase() {
		super();
		this.players = new ArrayList<Player>();
		this.names = new ArrayList<String>();
		this.scores = new ArrayList<String>();
		this.loadsave = new LoadSavePlayers(players);

	}

	/**String updater for the arrays used on Leaderboards
	 * 
	 */
	public void UpdateStringArrays() {
		sortPlayers();
		names.clear();
		scores.clear();
		for (int i = 0; i < players.size(); i++) {
			names.add(players.get(i).getName());
			scores.add(Integer.toString(players.get(i).getScore()));
		}
	}
	
	/**PlayerCreator method used when we want to add a player to the list.
	 * 
	 * @param PlayerName
	 */
	public void createPlayer(String PlayerName) {
		for (Player p : players) {
			if (p.getName() == PlayerName) {
				System.out.println("This Player name is already taken!");
			}
		}
		players.add(new Player(PlayerName, 0));

	}
	
	/**
	 * Sorts the players list.
	 */
	public void sortPlayers() {
		Collections.sort(players);
	}

	public void loadPlayers() {
		players = this.loadsave.load();
	}

	
	//Getters&Setters
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public ArrayList<String> getScores() {
		return scores;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public LoadSavePlayers getLoadSave() {
		return loadsave;
	}



}
