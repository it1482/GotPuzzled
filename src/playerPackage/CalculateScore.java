package playerPackage;

public class CalculateScore {
	private int puzzleDifficulty;
	private double timer;
	public CalculateScore(int puzzleDifficulty, double timer) {
		this.puzzleDifficulty = puzzleDifficulty;
		this.timer = timer;
	}
	
	public double returnScore(){
		return puzzleDifficulty/timer;
	}
	
	

}
