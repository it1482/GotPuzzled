package playerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PlayerDatabase {
	
	private ArrayList<Player> players;

	private LoadSavePlayers loadsave;
	
	public PlayerDatabase(){
	
		this.loadsave = new LoadSavePlayers();
		this.players = new ArrayList<Player>();
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		players.add(new Player("",0));
		
	}
	
	public void test(){
		players.add(new Player("a",10));
		players.add(new Player("a",50));
		players.add(new Player("a",100));
		players.add(new Player("a",0));
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void sortPlayer(){
		Collections.sort(players);
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
	
	public void addPlayer(Player player){
		for(Player p : players){
			if (p==player){
				System.out.println("This Player name is already taken!");/*Εμφανιση του μηνυματος με Dialog πλαισιο.*/
				return;
			}
		}
		players.add(player);
	}


}
