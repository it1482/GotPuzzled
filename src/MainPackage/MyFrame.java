package MainPackage;

import javax.swing.JFrame;

import puzzlePackage.SlidingPuzzle;

public class MyFrame extends JFrame{
	public MyFrame(){
		System.out.println("ok");
	
	
		this.setContentPane(new SlidingPuzzle());
		this.setVisible(true);
		this.setSize(800, 800);
		this.setTitle("ChessBoard");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
}
