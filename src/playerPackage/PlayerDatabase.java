package playerPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PlayerDatabase {
	
	private ArrayList<Player> players;
	private ArrayList<String> names;
	private ArrayList<String> scores;

	private LoadSavePlayers loadsave;
	
	public PlayerDatabase(){
	
		this.loadsave = new LoadSavePlayers();
		this.players = new ArrayList<Player>();
		this.names = new ArrayList<String>();
		this.scores = new  ArrayList<String>();

		
	}
	public void updateStringArray(){
		sortPlayers();
		for(int i=0;i<players.size();i++){
			names.add(players.get(i).getName());
			scores.add( Integer.toString(players.get(i).getScore()));
		}
	}
	
	
	public void test(){
		players.add(new Player("Player1",10));
		players.add(new Player("Player3",50));
		players.add(new Player("Player2",100));
		players.add(new Player("Player4",0));
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
	public void sortPlayers(){
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
