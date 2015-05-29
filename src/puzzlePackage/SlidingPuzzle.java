package puzzlePackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SlidingPuzzle extends Puzzle implements ActionListener{

	
	
	int[] thesis = new int[9];
	JButton help = new JButton("Help");
	Image image;
	ImageIcon icon;
	JButton buttons[],blankButton,newGame;
	//ArrayList<JPanel> rightspots = new ArrayList<JPanel>();
	//ArrayList<JPanel> tempspots = new ArrayList<JPanel>();
	JPanel grid,spots[],tempspots[],rightspots[];
	int currentBlankSpot,movecounter=0;
	JLabel moves_label,time_label;
	
	private Timer timer;
	public int counting = 0;
	int size_pleuras;
	public SlidingPuzzle(String name, Image image, int partsNumber) {
		super(name, image, partsNumber);
		//gia na perastei i photo
		//this.image = image;
		
		//Pleura tou puzzle
		
		size_pleuras=partsNumber;
		setSize(400,300);
		
		buttons = new JButton[size_pleuras*size_pleuras];
		//Gia na mpoun se stoixish ta koumpia
		grid = new JPanel ( new GridLayout(size_pleuras,size_pleuras) );
		
		setupPanels();
		setupImage();
		
		add(grid);
		add(help);
		MouseHandler handler = new MouseHandler();
		help.addMouseListener(handler);
		moves_label = new JLabel("Moves: "+Integer.toString(movecounter));
		add(moves_label,BorderLayout.SOUTH);
		
	}
	
	public void setupPanels(){
		
		spots = new JPanel[size_pleuras*size_pleuras];
		blankButton = new JButton(" ");
		blankButton.setBackground(Color.BLACK);
		for(int i=0;i<spots.length;i++){
			spots[i] = new JPanel(new BorderLayout());
			grid.add(spots[i]);
		}
		
	}
	
	public void setupImage(){
		
		MediaTracker tracker = new MediaTracker(this);
		tracker.addImage(image,1);
		
		/*JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try{
				BufferedImage bi1 = ImageIO.read(file);
				bi1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			} catch (Exception exp){
				exp.printStackTrace();
				
			}
		}
		*/
		try{
			tracker.waitForAll();
			image = ImageIO.read(new File("images/pic.jpg"));
			image=image.getScaledInstance(600,600, Image.SCALE_SMOOTH);
		} catch (Exception e){
			
		
		}
		BufferedImage bimage = new BufferedImage(
				image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics g = bimage.getGraphics();
		g.drawImage(image,0,0,this);
		
		int width=image.getWidth(this);
		int height=image.getHeight(this);
		
		int count = 0;
		for(int j=0;j<size_pleuras;j++)
			for(int i=0;i<size_pleuras;i++){
				BufferedImage window = bimage.getSubimage(i*width/size_pleuras,
															j*height/size_pleuras,
															width/size_pleuras,
															height/size_pleuras);
				setupButton(count,window);
				count++;
			}
		spots[spots.length-1].add(blankButton);
		currentBlankSpot = spots.length-1;
		rightspots[spots.length-1]=spots[spots.length-1];
	}
	
	public void setupButton(int id,Image img){
		buttons[id] = new JButton(Integer.toString(id),new ImageIcon(img));
		buttons[id].addActionListener(this);
		buttons[id].setMargin(new Insets(0,0,0,0));
		buttons[id].setContentAreaFilled(false);
		//thesis[id]=id;
		spots[id].add(buttons[id],BorderLayout.CENTER);
		rightspots[id]=spots[id];
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src == help){
			
			
		}else{
			for(int i=0;i<buttons.length;i++){
				if(buttons[i] == src){
					changeSpots(i);
					return;
				}
			}
		}
	}
	
	public void changeSpots(int i){
		if(currentBlankSpot-1 == i || currentBlankSpot -size_pleuras == i ||
				currentBlankSpot +1 == i || currentBlankSpot + size_pleuras == i){
			movecounter++;
			moves_label.setText("Moves : "+Integer.toString(movecounter));
			
			spots[currentBlankSpot].removeAll();
			spots[i].removeAll();
			
			spots[currentBlankSpot].add(buttons[i]);
			spots[i].add(blankButton);
			
			buttons[currentBlankSpot] = buttons[i];
			buttons[i] = blankButton;
			currentBlankSpot = i;
			
			repaint();
				
			return;
			
		}
		
		
	}
	
	class MouseHandler implements MouseListener{
		
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<spots.length-1;i++){
				tempspots[i]=spots[i];
				spots[i].removeAll();
				spots[i]=tempspots[i];
				
			}			
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
