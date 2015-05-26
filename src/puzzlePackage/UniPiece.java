package puzzlePackage;

import java.util.Set;

public class UniPiece extends Piece{

	Set<Piece> subs;
	  public UniPiece (
			    Set<Piece> subpieces,
			    int imageX, int imageY,
			    int imageWidth, int imageHeight,
			    int totalWidth, int totalHeight,
			    int rotation) {
		super(imageX, imageY, totalWidth, totalHeight, rotation, rotation);
		subs = subpieces;
		
		// TODO Auto-generated constructor stub
	}

}
