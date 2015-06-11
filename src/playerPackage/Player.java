package playerPackage;

import java.io.Serializable;
/**
 * Player class was created to keep track of the best players in the game.
 * @param name The name of the player.
 * @param score The score of the player.
 *
 */

public class Player implements Comparable<Player>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;

	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	/**
	 * Compares players by their score.
	 * 	 
	 */
	public int compareTo(Player arg0) {
		if (this.score > arg0.score)
			return -1;
		else if (this.score < arg0.score)
			return +1;
		return 0;
	}

}
