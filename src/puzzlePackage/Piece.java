package puzzlePackage;


public class Piece {

	  // Location in the image.
	  private int imageX, imageY;

	  // Size of the entire image.
	  private int totalWidth, totalHeight;

	  // Location in the image adjusted by current rotation.
	  private int rotatedX, rotatedY;
	  
	

	  public Piece(int imageX, int imageY, int totalWidth, int totalHeight,
			int rotatedX, int rotatedY) {
		super();
		this.imageX = imageX;
		this.imageY = imageY;
		this.totalWidth = totalWidth;
		this.totalHeight = totalHeight;
		this.rotatedX = rotatedX;
		this.rotatedY = rotatedY;
	}

	

	public boolean canFit (Piece piece){
		  return true;
	  }
	  
	  
	public int getImageX() {
		return imageX;
	}



	public int getImageY() {
		return imageY;
	}



	public int getTotalWidth() {
		return totalWidth;
	}



	public int getTotalHeight() {
		return totalHeight;
	}



	public int getRotatedX() {
		return rotatedX;
	}



	public int getRotatedY() {
		return rotatedY;
	}



	public void setRotation (int rotation){
		  
	  }

	
}
