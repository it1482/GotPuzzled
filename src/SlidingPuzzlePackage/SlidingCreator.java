package SlidingPuzzlePackage;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SlidingCreator extends JFrame {
	
	public SlidingCreator(){
		
		
		
		
		
		
		
	}
	
	public static Image fileChooser(Component comp){
		Image image=null;
		MediaTracker tracker = new MediaTracker(comp);
		tracker.addImage(image,1);
		
		JFileChooser fc = new JFileChooser(System.getProperty("user.home")+"\\Desktop\\");
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try{
				tracker.waitForAll();
				image = ImageIO.read(file);
				image=image.getScaledInstance(600,600, Image.SCALE_SMOOTH);
			} catch (Exception exp){
				exp.printStackTrace();
				
			}
			
		}else{
			System.out.println("Bye");
			System.exit(0);
		}
		return image;
	}
	
}
