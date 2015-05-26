package playerPackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSavePlayers implements Serializable{
	private ArrayList<Player> players = new ArrayList<Player>();
	
	
	public ArrayList<Player> load(){
		try{
		FileOutputStream fos = new FileOutputStream("players.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(players);
		oos.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
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
