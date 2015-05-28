package playerPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class LoadSavePlayers implements Serializable{
	private ArrayList<Player> players = new ArrayList<Player>();
	
	
	public ArrayList<Player> load(){
		ArrayList<Player> p = null;
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
