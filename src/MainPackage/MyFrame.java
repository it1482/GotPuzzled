package MainPackage;

import java.awt.Image;

import javax.swing.JFrame;

import SlidingPuzzlePackage.SlidingCreator;
import SlidingPuzzlePackage.SlidingPuzzle;

public class MyFrame extends JFrame{
	Image image;
	public MyFrame(){
		System.out.println("ok");
		//size einai i pleura tou puzzle
		
		image=SlidingCreator.fileChooser(this);
		int difficulty=2;
		//JFrame slide = new SlidingPuzzle("Test",image,difficulty);
		this.setContentPane(new SlidingPuzzle("Test",image,difficulty));
		this.setVisible(true);
		this.setSize(800, 800);
		this.setTitle("Jigsaw");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
}
