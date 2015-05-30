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

	
	
	
	Image image;
	ImageIcon icon;
	JButton buttons[],tempbuttons[],rightbuttons[],blankButton,newGame;
	JButton tipButton = new JButton("Tip"),new_gameButton = new JButton("New Game");
	JPanel grid,spots[];
	int currentBlankSpot,movecounter=0;
	JLabel moves_label,time_label;
	
	Timer timer ;
	
	public int counting = 0;
	int size_pleuras;
	public SlidingPuzzle(String name, Image image, int partsNumber) {
		super(name, image, partsNumber);
		//gia na perastei i photo
		//this.image = image;
	
		time_label = new JLabel("");
		ActionListener actListener = new ActionListener(){
			int i=0;
			public void actionPerformed(ActionEvent e){
				time_label.setText("Time: "+Integer.toString(i));
				i+=1;
			}
		};
		timer = new Timer(1000,actListener);
		timer.start();
		
		//Pleura tou puzzle
		
		size_pleuras=partsNumber;
		setSize(400,300);
		
		buttons = new JButton[size_pleuras*size_pleuras];
		//Gia na mpoun se stoixish ta koumpia
		grid = new JPanel ( new GridLayout(size_pleuras,size_pleuras) );
		
		setupPanels();
		setupImage();
		
		add(grid);
		add(time_label);
		new_gameButton.addActionListener(this);
		
		MouseHandler handler = new MouseHandler();
		tipButton.addMouseListener(handler);
		
		moves_label = new JLabel("Moves: "+Integer.toString(movecounter));
	
		
		add(moves_label,BorderLayout.CENTER);
		add(tipButton,BorderLayout.CENTER);
		add(new_gameButton,BorderLayout.CENTER);
		
	}
	
	public void setupPanels(){
		
		spots = new JPanel[size_pleuras*size_pleuras];

		
		rightbuttons = new JButton[size_pleuras*size_pleuras];
		tempbuttons = new JButton[size_pleuras*size_pleuras];
		
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
		rightbuttons[currentBlankSpot]=blankButton;
		shufflePuzzle();
	}
	
	public void setupButton(int id,Image img){
		buttons[id] = new JButton(Integer.toString(id),new ImageIcon(img));
		buttons[id].addActionListener(this);
		buttons[id].setMargin(new Insets(0,0,0,0));
		buttons[id].setContentAreaFilled(false);
		spots[id].add(buttons[id],BorderLayout.CENTER);
		rightbuttons[id]=buttons[id];
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(new_gameButton == e.getSource()){
			movecounter=0;
			moves_label.setText("Moves : "+Integer.toString(movecounter));
			shufflePuzzle();
		}else
			for(int i=0;i<buttons.length;i++){
					if(buttons[i] == e.getSource()){
						changeSpots(i,false);
						return;
					}
			}
		
	}
	
	public void changeSpots(int i,boolean automatic){
		if(validMove(i,currentBlankSpot)){
			if(!automatic){
				movecounter++;
				moves_label.setText("Moves : "+Integer.toString(movecounter));
			}
			
			
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
	
	public void shufflePuzzle(){
		int last_position=-1;
		
		int[] possible_cells_to_move = new int[4];
		
		int random_number = 100 + (int)(Math.random()*2000); 
		for(int i=0;i<random_number;i++){
			possible_cells_to_move[0]=currentBlankSpot-1;
			possible_cells_to_move[1]=currentBlankSpot+1;
			possible_cells_to_move[2]=currentBlankSpot-size_pleuras;
			possible_cells_to_move[3]=currentBlankSpot+size_pleuras;
			
			ArrayList<Integer> possible_moves = new ArrayList<Integer>();
			for(int k=0;k<4;k++){
				if(!validMove(possible_cells_to_move[k],currentBlankSpot) 
						|| last_position==currentBlankSpot 
								|| possible_cells_to_move[k]>=size_pleuras*size_pleuras
									|| possible_cells_to_move[k]<0)
						possible_cells_to_move[k]=-1;
				else
					possible_moves.add(possible_cells_to_move[k]);
			
			}
			if(possible_moves.size()==0)
				System.out.println("Malakia");
			else{
				last_position=currentBlankSpot;
				changeSpots(possible_moves.get( (int) (Math.random()*possible_moves.size()) ),true);
				
			}
			possible_moves.clear();
		}
		
	}
	
	public boolean validMove(int i,int blankpos){
		if(i/size_pleuras==blankpos/size_pleuras && (blankpos-1==i || blankpos+1==i))
			return true;
		else if(blankpos -size_pleuras == i )
			return true;
		else if(blankpos + size_pleuras == i)
			return true;
		return false;
	}
	
	class MouseHandler implements MouseListener{
		
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			for(int i=0;i<spots.length-1;i++){
				tempbuttons[i]=buttons[i];
				spots[i].removeAll();
				spots[i].add(rightbuttons[i]);
			}
			tempbuttons[spots.length-1]=buttons[spots.length-1];
			spots[spots.length-1].removeAll();
			spots[spots.length-1].add(blankButton);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			for(int i=0;i<spots.length;i++){
				buttons[i]=tempbuttons[i];
				spots[i].removeAll();
				spots[i].add(buttons[i]);
				
			}
			repaint();
			
		}
		
	}

	
}
