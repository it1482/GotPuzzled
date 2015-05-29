package playerPackage;

import java.util.ArrayList;

public class PlayerDatabase {
	
	private ArrayList<Player> players;
	
	private LoadSavePlayers loadsave;
	
	public PlayerDatabase(LoadSavePlayers loadsave){
		
		this.loadsave = loadsave;
		players = loadsave.getPlayers();
		
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void loadPlayers(){
		players = this.loadsave.load();
	}
	
	//(object of PlayerDatabase).getLoaderSaver().load/save(players); to save or load the Player Database
	public LoadSavePlayers getLoaderSaver(){
		return loadsave;
	}

	
	public void createPlayer(String PlayerName){
		for(Player p : players){
			if (p.getName()==PlayerName){
				System.out.println("This Player name is already taken!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
			}
		}
		players.add(new Player(PlayerName,0));
		
	}

}
