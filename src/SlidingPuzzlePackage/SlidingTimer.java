package SlidingPuzzlePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class SlidingTimer{
	JLabel time_label;
	TimerHandler timerHandler = new TimerHandler();
	Timer timer;
	
	public SlidingTimer(int delay) {
		timer = new Timer(1000,timerHandler);
		time_label = new JLabel("");
		timer.start();
	}

	public void setTimerCounter(int time){
		timerHandler.setTimer_counter(time);
	}
	
	public int getTimerCounter(){
		return timerHandler.getTimer_counter();
	}
	
	public void startTimer(){
		timer.start();
	}
	
	public void stopTimer(){
		timer.stop();
	}
	
	public JLabel getTime_label() {
		return time_label;
	}
	
	class TimerHandler implements ActionListener{
		int timer_counter=0;
		
		public void actionPerformed(ActionEvent e) {
			time_label.setText("Time: "+Integer.toString(timer_counter));
			timer_counter+=1;
		}
		
		public int getTimer_counter() {
			return timer_counter;
		}
		
		public void setTimer_counter(int timer_counter) {
			this.timer_counter = timer_counter;
		}
		
	}

	
}

