package puzzlePackage;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SlidingPuzzle extends Puzzle{
	
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	private int freeY,freeX;

	public SlidingPuzzle(String name, Image image, int partsNumber) {
		super(name, image, partsNumber);
		// TODO Auto-generated constructor stub
	}
	
	public int preferedNumber(int number){
		return 0;
	}
	
	public void mouseClicked(MouseEvent e){
		
	}
	
	public void mouseDragged(MouseEvent e){
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}

}
