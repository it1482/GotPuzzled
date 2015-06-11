package SlidingPuzzlePackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import playerPackage.CalculateScore;
import playerPackage.Player;

public class SlidingPuzzle extends JFrame implements ActionListener{	
	SlidingTimer timer ;		
	
	JFrame frmGotPuzzled,myFrame;
	JPanel myPanel = new JPanel();
	Image image;
	ImageIcon icon;
	JButton buttons[],tempbuttons[],rightbuttons[],blankButton,newGame;
	JButton tipButton = new JButton("Tip"),new_gameButton = new JButton("New Game");
	JPanel grid,spots[];
	int currentBlankSpot,movecounter=0,difficulty;
	JLabel moves_label;
	int endTime,endMoves;
	String playerName;
	
	public int counting = 0;
	int size_pleuras;
	public SlidingPuzzle(String name, Image image, int difficulty,JFrame frmGotPuzzled) {
		this.frmGotPuzzled=frmGotPuzzled;
		this.difficulty=difficulty;
		if(difficulty==1)
			size_pleuras=3;
		else if(difficulty==2)
			size_pleuras=4;
		else if(difficulty==3)
			size_pleuras=5;
		else
			System.out.println("Error in partsNumber variable.");
		this.image=image.getScaledInstance(600,600, Image.SCALE_SMOOTH);			
		
		buttons = new JButton[size_pleuras*size_pleuras];
		grid = new JPanel ( new GridLayout(size_pleuras,size_pleuras) );
		this.setContentPane(myPanel);
		this.setVisible(true);
		this.setSize(650, 800);
		this.setTitle("Jigsaw");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setupPanels();
		setupImage();
		
		myPanel.add(grid);
		myPanel.add(timer.getTime_label());
		new_gameButton.addActionListener(this);
		
		MouseHandler handler = new MouseHandler();
		tipButton.addMouseListener(handler);
		
		moves_label = new JLabel("Moves: "+Integer.toString(movecounter));		
		myPanel.add(new_gameButton,BorderLayout.CENTER);
		myPanel.add(tipButton,BorderLayout.CENTER);			
		myPanel.add(moves_label,BorderLayout.CENTER);
	}
	
	
	/** 
	 * Αρχικοποίηση-Σχεδιασμός του JPanel(spots) των κουμπιών,χρησιμοποιώντας το BorderLayout 
	 * και προσθήκη του JPanel(spots) στο γενικό JPanel(grid) του JFrame.
	 * 
	 **/
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
	
	/***
	 * Μετατρέπει το αντικείμενο Image σε BufferedImage,
	 * Κόβει την εικόνα σε κουτάκια ανάλογα με το πλήθος τους 
	 * και τα συνδεει στο αντιστοιχο κουτι,καλεί την μέθοδο shufflePuzzle() 
	 * και τέλος αρχικοποιεί το αντικείμενο Timer.
	 *  
	 */	 
	public void setupImage(){		
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
		timer = new SlidingTimer(1000);
		
	}
	/*** 
	 * Αρχικοποιεί τα κουμπιά της λίστας buttons,συνδέει το κάθε κουμπί με το ActionListener
	 * και μορφοποιεί τις διαστάσεις του.
	 * @param id
	 * @param img
	 */
	public void setupButton(int id,Image img){
		buttons[id] = new JButton(new ImageIcon(img));
		buttons[id].addActionListener(this);
		buttons[id].setMargin(new Insets(0,0,0,0));
		buttons[id].setContentAreaFilled(false);
		spots[id].add(buttons[id],BorderLayout.CENTER);
		rightbuttons[id]=buttons[id];
	}
	
	@Override
	/***
	 * Εδώ υλοποιούνται οι εναλλαγες των κουμπιών με το κάθε πάτημα που γίνεται απο τον χρήστη,
	 * επίσης υπαρχει και η μέθοδος newGame() που καλείται οταν πατηθεί το κουμπί " New Game "
	 */
	public void actionPerformed(ActionEvent e) {
		if(new_gameButton == e.getSource()){
			newGame();
		}else {
			for(int i=0;i<buttons.length;i++){
					if(buttons[i] == e.getSource()){
						changeSpots(i,false);						
						break; 
					}
			}
			int i=0;
			boolean flag = true;
			while(flag && i<buttons.length-1){
				if(!spots[i].getComponent(0).equals(rightbuttons[i]))
					flag=false;				
				i++;
			}
			if(flag){
				timer.stopTimer();
				endStats();
			}
			return;
		}
		
	}
	
	/**
	 * Γίνεται έλεγχος ορθότητας(καλόντας την μέθοδο validMove),σε περίπτωση που αυτό ισχύει
	 * γίνεται αλλαγή και επανεσχεδιάζεται το JPanel,με τα κουμπιά στις νέες θέσεις τους.
	 * @param i
	 * @param automatic
	 */
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
	/***
	 * Στο ξεκίνημα κάθε παρτίδας,τα κομμάτια πρέπει να ανακατευτούν,χωρίς όμως να παραβιάζουν τους 
	 * κανόνες του παιχνιδιού ώστε να είναι εφικτή η λύση του.
	 * Κρατάμε σε μία μεταβλητή τύπου int τη θέση του κενού κουμπιού,
	 * βρίσκουμε με τη βοήθεια της μεθόδου validMove,τις εφικτές εναλλαγές ο αριθμός των οποίων 
	 * κυμαίνεται από δύο μέχρι τρεις,απορρίπτοντας την εναλλαγή με το κουμπί που εναλλάχθηκε τελευταίο.
	 * Στη συνέχεια έχοντας τις εφικτές εναλλαγές σε μία λίστα,εφαρμόζουμε μία εναλλαγή επιλέγοντας τυχαία 
	 * μία θέση από την προηγούμενη λίστα.
	 * Τέλος,επαναλαμβάνουμε τα βήματα αυτά για έναν τυχαίο αριθμό εύρους 100-2100.
	 * 
	 */
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
				System.out.println("Error in initialize.");
			else{
				last_position=currentBlankSpot;
				changeSpots(possible_moves.get( (int) (Math.random()*possible_moves.size()) ),true);
				
			}
			possible_moves.clear();
		}
		
	}
	/*** 
	 * Ελέγχεται αν τα δύο κουμπιά (κενό κουμπί-το κουμπί που πατήθηκε) είναι γειτονικά,και σε περίπτωση που ισχύει
	 * επιστρέφει την τιμή true, αλλιώς false.
	 * @param i
	 * @param blankpos
	 * @return boolean
	 */
	public boolean validMove(int i,int blankpos){
		if(i/size_pleuras==blankpos/size_pleuras && (blankpos-1==i || blankpos+1==i))
			return true;
		else if(blankpos -size_pleuras == i )
			return true;
		else if(blankpos + size_pleuras == i)
			return true;
		return false;
	}
	
	/** 
	 * Εδώ υλοποιείται η λειτουργία του κουμπιού "Tip" ,
	 * όταν παραμένει το κουμπί πατημένο , εμφανίζει τα κουμπιά στην σωστή τους θέση
	 * και οταν αφήνεται το κουμπί επιστρέφει στην προηγούμενη κατάσταση.
	 * @author Admin
	 *
	 */
	class MouseHandler implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {		}

		@Override
		public void mouseEntered(MouseEvent arg0) {		}

		@Override
		public void mouseExited(MouseEvent arg0) {		}

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
	
	/***
	 * Σε περίπτωση που πατηθεί το κουμπί "New Game" καλείται η μέθοδος αυτή
	 * Η οποία μηδενίζει το χρονόμετρο και τον μετρητή κινήσεων.
	 */
	public void newGame(){
		timer.stopTimer();
		timer.setTimerCounter(0);
		timer.startTimer();		
		movecounter=0;
		moves_label.setText("Moves : "+Integer.toString(movecounter));
		shufflePuzzle();
	}
	
	/***
	 * Σε περίπτωση ολοκλήρωσης του παζλ , αποθηκεύονται τα αποτελέσματα του παιχνιδιού(Χρόνος,Κινήσεις)
	 */
	public void endStats() {
		endTime = timer.getTimerCounter();
		endMoves = movecounter;
		CalculateScore tempscore = new CalculateScore(difficulty,(double)endTime);
		Player newplayer = new Player(super.getName(),(int) tempscore.returnScore());		
		
		JOptionPane.showMessageDialog(null, "You Won", "Puzzle Got Solved!", JOptionPane.PLAIN_MESSAGE);
		//playerName=JOptionPane.showInputDialog(grid,"Time:" + endTime + " ,Moves:" + endMoves + "\nYour Name:","You Won!", JOptionPane.PLAIN_MESSAGE);
		goToMenu();
	}
	
	/** 
	 * Επιστρέφει το κεντρικό μενού
	 */
	public void goToMenu(){
		
		frmGotPuzzled.setVisible(true);
		this.dispose();
	}	
}
