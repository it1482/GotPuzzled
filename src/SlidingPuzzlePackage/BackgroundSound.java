package SlidingPuzzlePackage;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

    public class BackgroundSound {
    	Clip clip;
    	public BackgroundSound(){
    		try {
	    		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sound.wav"));
	    		clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.loop(100);
	    		clip.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	public Clip getMusic(){
    		return clip;
    	}
    	public void startMusic(){
    		clip.start();
    	}
    	
    	public void stopMusic(){
    		clip.stop();
    	}
    }
