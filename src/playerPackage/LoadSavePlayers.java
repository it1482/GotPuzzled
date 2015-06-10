package playerPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSavePlayers implements Serializable{
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public LoadSavePlayers(ArrayList<Player> p){
		players = p;
	}
	
	
	public ArrayList<Player> load(){
		ArrayList<Player> p = null;
		File f = new File("players.ser");
		if(f.exists() && !f.isDirectory()) {
			try{
				FileInputStream fis = new FileInputStream("players.ser");
		         ObjectInputStream ois = new ObjectInputStream(fis);
		         p = (ArrayList<Player>) ois.readObject();
		         players = p;
		         ois.close();
		         fis.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		else
		{
			initiate();
		}
		
		System.out.println(players.get(0).getName());
		return players;
	}
	
	

	
	public void initiate(){
		players.add(new Player("Player1",0));
		players.add(new Player("Player3",0));
		players.add(new Player("Player2",0));
		players.add(new Player("Player4",0));
		players.add(new Player("Player5",0));
		players.add(new Player("Player6",0));
		players.add(new Player("Player7",0));
		players.add(new Player("Player8",0));
		players.add(new Player("Player9",0));
		players.add(new Player("Player10",0));
	}
	

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void save(ArrayList<Player> players) {
		try{
		FileOutputStream fos = new FileOutputStream("players.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(players);
		oos.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}

}
