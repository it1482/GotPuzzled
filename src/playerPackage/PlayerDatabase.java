package playerPackage;

import java.util.ArrayList;

public class PlayerDatabase {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private LoadSavePlayers loadsave;
	
	
	
	public ArrayList<Player> getPlayers() {
		return players;
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
