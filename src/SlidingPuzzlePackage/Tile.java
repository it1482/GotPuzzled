package SlidingPuzzlePackage;

public class Tile {
	private int tileX;
	private int tileY;
	private int tileIndex;

	public Tile(int tileX, int tileY, int tileIndex) {
		super();
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileIndex = tileIndex;
	}
	
	public boolean tileCanMove(int freeX, int freeY){
		return true;
	}
	
	public void tileMove(){
		
	}
	

	public int getTileX() {
		return tileX;
	}
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}
	public int getTileY() {
		return tileY;
	}
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
	public int getTileIndex() {
		return tileIndex;
	}
	public void setTileIndex(int tileIndex) {
		this.tileIndex = tileIndex;
	}
	
	
	

}
