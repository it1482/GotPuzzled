package puzzlePackage;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class JigsawPuzzle extends Puzzle {
	public JigsawPuzzle(String name, Image image, int partsNumber) {
		super(name, image, partsNumber);
		// TODO Auto-generated constructor stub
	}
	// fields
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private int pieceNumber;
	private boolean rotation;
	
	public int preferedNumber(int partsNumber){
		return pieceNumber;
	}
	
	public void mouseClicked(MouseEvent e){
		
	}
	
	public void mouseDragged(MouseEvent e){
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	
	
}
