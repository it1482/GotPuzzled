package playerPackage;

import java.io.Serializable;

public class Player implements Comparable<Player> , Serializable{
	private String name;
	private int score;
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	

	
	public int getScore() {
		return score;
	}




	public String getName(){
		return name;
	}

	public int compareTo(Player arg0) {
		if(this.score > arg0.score)
			return -1;
		else if (this.score < arg0.score)
			return +1;
		return 0;
	}



}
