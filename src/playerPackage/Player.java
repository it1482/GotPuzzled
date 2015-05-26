package playerPackage;

public class Player {
	private String name;
	private int score;
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public void addScore(int scoreToAdd){
		score += scoreToAdd;
	}
	
	public String getName(){
		return name;
	}
	

}
