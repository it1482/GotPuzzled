package ladderPackage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class LoadSaveLadders implements Serializable {
	private ArrayList<LadderChallenge> ladders  = new ArrayList<LadderChallenge>();
	
	
	public LoadSaveLadders(ArrayList<LadderChallenge> l){
		ladders = l;
	}
	
	
	public ArrayList<LadderChallenge> load(){
		ArrayList<LadderChallenge> l = null;
		try{
			FileInputStream fis = new FileInputStream("laddersData.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         l = (ArrayList<LadderChallenge>) ois.readObject();
	         ladders = l;
	         ois.close();
	         fis.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}



		return ladders;
	}

	public void save(ArrayList<LadderChallenge> ladders){
		try{
			FileOutputStream fos = new FileOutputStream("laddersData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ladders);
			oos.close();
			System.out.println("puzzles serialised");
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		
		
	}
	

}
