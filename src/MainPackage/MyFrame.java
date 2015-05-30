package MainPackage;

import javax.swing.JFrame;

import SlidingPuzzlePackage.SlidingPuzzle;

public class MyFrame extends JFrame{
	public MyFrame(){
		System.out.println("ok");
		//size einai i pleura tou puzzle
		int size=3;
		this.setContentPane(new SlidingPuzzle("Test",null,size));
		this.setVisible(true);
		this.setSize(800, 800);
		this.setTitle("Jigsaw");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
}
