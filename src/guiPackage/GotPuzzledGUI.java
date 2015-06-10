package guiPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ladderPackage.LadderChallenge;
import playerPackage.Player;
import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleJigsawData;
import JigsawPuzzlePackage.JigsawCutter;
import JigsawPuzzlePackage.JigsawFrame;
import SlidingPuzzlePackage.BackgroundSound;
import SlidingPuzzlePackage.SlidingPuzzle;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;



public class GotPuzzledGUI {
		
	/*
	 * here we post all frames and panels to be visible and enable for whole code
	 */
	// kentriko vasiko frame - panw sto opoio kathontai ola ta panels
	private JFrame frmGotPuzzled;

	private BackgroundSound sound = new BackgroundSound();
	
	
	// kentriko panel
	private JPanel mainMenuPanel;
	private JPanel playPanel;
	private JPanel ladderPanel;
	private JPanel playLadderPanel;
	private JPanel customGamePanel;
	private JPanel editorPanel;
	private JPanel editorCreatePanel;
	private JPanel createPuzzlePanel; 
	private JPanel createLadderPanel;
	private JPanel editorExportPanel;
	private JPanel editorExportPuzzlePanel;
	private JPanel editorExportLadderPanel;
	private JPanel editorImportPanel;
	private JPanel optionsPanel;
	private JPanel leaderboardsPanel;

	private JScrollPane customPuzzlesListScrollPane;
	private JMenuBar menuBar;
	private JButton backFromSlidingToMainMenuButton;
	private JTextField createPuzzleNewPuzzleNameTextField;
	private JTextField createLadderNewLadderNameTextField;
	private JCheckBox createPuzzleRotationCheckBox;
	private JTextField createPuzzlePiecesTextField;
	private JButton createPuzzleButton;
	private JButton createLadderButton;
	private JRadioButton optionsMusicOnRadioButton;
	private JRadioButton optionsMusicOffRadioButton;
	
	
	//fields needed
	Database database = new Database();
	private ImageIcon image;
	DefaultListModel<String> model;
	protected int score;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/* apply look and feel
		 * https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GotPuzzledGUI window = new GotPuzzledGUI();
					window.frmGotPuzzled.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public GotPuzzledGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//loading database data
		database.getPuzzleDatabase().setPuzzlesData(database.getPuzzleDatabase().getLoadsave().load());
		database.getLadderDatabase().setLadders(database.getLadderDatabase().getLoadsave().load());
		database.getPlayersDatabase().setPlayers(database.getPlayersDatabase().getLoadSave().load());
		database.getPuzzleDatabase().UpdatePuzzleNamesArrayList(); 
		database.getLadderDatabase().UpdateLadderNamesArrayList();
		database.getPlayersDatabase().UpdateStringArrays();



		
		
		// a frame it's been constructed
		frmGotPuzzled = new JFrame();
		frmGotPuzzled.getContentPane().setBackground(new Color(240, 255, 255));
		frmGotPuzzled.setTitle("Got Puzzled 2.0");
		frmGotPuzzled.setBounds(100, 100, 637, 609);
		frmGotPuzzled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGotPuzzled.setResizable(false);
		
		
		// a JMenuBar it's been constructed
		menuBar = new JMenuBar();
		frmGotPuzzled.setJMenuBar(menuBar);
		
		// a JMenu Option it's been constructed
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		// a JMenu Sub Option it's been constructed: File > Exit
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		
		// ActionListener implementation to close the program with hitting Exit
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(GotPuzzledGUI.class.getResource("/guiPackage/img/exit.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		JMenuItem mntmAboutGotPuzzled = new JMenuItem("About Got Puzzled");
		// ActionListener implementation to show about with hitting About Got Puzzled from the JMenu
		mntmAboutGotPuzzled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog (null, "Got Puzzle is a puzzle game which the player is able to play Sliding and Jigsaw Puzzles.\n"
						+ "One of the core features of Got Puzzled is the freedom which it gives the player to create his own puzzles.\n\n"
						+ "You can play a single puzzle in Custom Game mode\n"
						+ "Got Puzzled also supports Ladder Challenge mode.\nLadder Challenge mode gets the player to solve a series of different puzzles.\n"
						+ "After the end of the Ladder Challenge your score will be saved in the LeaderBoards Section if it's in between the top 10 scores.\n"
						+ "Players can also create their own Ladder Challenges.\n\n"
						+ "With Got Puzzled you can also export your own Puzzles and Ladder Challenges.\n"
						+ "This will allow the other players to import your Puzzles and Ladder Challenges and play them\n\n", "About Got Puzzled", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		mntmAboutGotPuzzled.setIcon(new ImageIcon(GotPuzzledGUI.class.getResource("/guiPackage/img/info.png")));
		mnAbout.add(mntmAboutGotPuzzled);
		
		
		backFromSlidingToMainMenuButton = new JButton("back");
		backFromSlidingToMainMenuButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {				
				frmGotPuzzled.setContentPane(mainMenuPanel);
				frmGotPuzzled.setBounds(100, 100, 637, 609);
				playPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
				frmGotPuzzled.setResizable(false);
				backFromSlidingToMainMenuButton.setVisible(false);				
			}
		});
		
		menuBar.add(backFromSlidingToMainMenuButton);
		backFromSlidingToMainMenuButton.setVisible(false);
		frmGotPuzzled.getContentPane().setLayout(null);
		
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBounds(0, 0, 631, 557);
		mainMenuPanel.setBackground(new Color(245, 245, 220));
		frmGotPuzzled.getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		
		JLabel mainMenuTitleLabel = new JLabel("Got Puzzled!");
		mainMenuTitleLabel.setForeground(new Color(34, 139, 34));
		mainMenuTitleLabel.setBounds(-2, 0, 621, 54);
		mainMenuTitleLabel.setToolTipText("Let the Game begin");
		mainMenuTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		mainMenuTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuPanel.add(mainMenuTitleLabel);
		
		JButton mainPlayButton = new JButton("Play!");
		mainPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				playPanel.setVisible(true);
				
			}
		});
		mainPlayButton.setForeground(new Color(255, 255, 255));
		mainPlayButton.setBackground(new Color(34, 139, 34));
		mainPlayButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		mainPlayButton.setBounds(181, 83, 243, 72);
		mainMenuPanel.add(mainPlayButton);
		
		JButton mainEditorButton = new JButton("Editor");
		mainEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				editorPanel.setVisible(true);
			}
		});
		mainEditorButton.setForeground(Color.WHITE);
		mainEditorButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		mainEditorButton.setBackground(new Color(34, 139, 34));
		mainEditorButton.setBounds(181, 189, 243, 72);
		mainMenuPanel.add(mainEditorButton);
		
		JButton mainOptionsButton = new JButton("Options");
		mainOptionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				optionsPanel.setVisible(true);
			}
		});
		mainOptionsButton.setForeground(Color.WHITE);
		mainOptionsButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		mainOptionsButton.setBackground(new Color(34, 139, 34));
		mainOptionsButton.setBounds(181, 297, 243, 72);
		mainMenuPanel.add(mainOptionsButton);
		
		JButton mainExitButton = new JButton("Exit Game");
		mainExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mainExitButton.setForeground(Color.WHITE);
		mainExitButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		mainExitButton.setBackground(new Color(34, 139, 34));
		mainExitButton.setBounds(181, 407, 243, 72);
		mainMenuPanel.add(mainExitButton);
		
		playPanel = new JPanel();
		playPanel.setBounds(0, 0, 631, 557);
		playPanel.setLayout(null);
		playPanel.setBackground(new Color(245, 245, 220));
		frmGotPuzzled.getContentPane().add(playPanel);
		playPanel.setVisible(false);
		
		
		JLabel playTitleLabel = new JLabel("Play!");
		playTitleLabel.setToolTipText("");
		playTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playTitleLabel.setForeground(new Color(34, 139, 34));
		playTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		playTitleLabel.setBounds(0, 0, 621, 54);
		playPanel.add(playTitleLabel);
		
		JButton playLadderButton = new JButton("Ladder Challenge");
		playLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playPanel.setVisible(false);
				ladderPanel.setVisible(true);
			}
		});
		playLadderButton.setForeground(Color.WHITE);
		playLadderButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		playLadderButton.setBackground(new Color(34, 139, 34));
		playLadderButton.setBounds(181, 83, 243, 72);
		playPanel.add(playLadderButton);
		
		JButton playCustomButton = new JButton("Custom Game");
		playCustomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playPanel.setVisible(false);
				customGamePanel.setVisible(true);
			}
		});
		playCustomButton.setForeground(Color.WHITE);
		playCustomButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		playCustomButton.setBackground(new Color(34, 139, 34));
		playCustomButton.setBounds(181, 189, 243, 72);
		playPanel.add(playCustomButton);
		

		
		JButton playLeaderboardsButton = new JButton("Leaderboards");
		playLeaderboardsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playPanel.setVisible(false);
				leaderboardsPanel.setVisible(true);
			}
		});
		playLeaderboardsButton.setForeground(Color.WHITE);
		playLeaderboardsButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		playLeaderboardsButton.setBackground(new Color(34, 139, 34));
		playLeaderboardsButton.setBounds(181, 340, 243, 72);
		playPanel.add(playLeaderboardsButton);
		
		JButton playBackToMainMenuButton = new JButton("Back");
		playBackToMainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});
		playBackToMainMenuButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		playBackToMainMenuButton.setForeground(new Color(255, 255, 255));
		playBackToMainMenuButton.setBackground(new Color(34, 139, 34));
		playBackToMainMenuButton.setBounds(20, 470, 100, 50);
		playPanel.add(playBackToMainMenuButton);
		
		
		ladderPanel = new JPanel();
		ladderPanel.setLayout(null);
		ladderPanel.setBackground(new Color(245, 245, 220));
		ladderPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(ladderPanel);
		ladderPanel.setVisible(false);
		
		JLabel ladderTitleLabel = new JLabel("Ladder Challenge");
		ladderTitleLabel.setToolTipText("");
		ladderTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ladderTitleLabel.setForeground(new Color(34, 139, 34));
		ladderTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		ladderTitleLabel.setBounds(0, 0, 621, 54);
		ladderPanel.add(ladderTitleLabel);
		
		JLabel ladderSubTitleLabel = new JLabel("Choose your destiny!");
		ladderSubTitleLabel.setToolTipText("");
		ladderSubTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ladderSubTitleLabel.setForeground(new Color(34, 139, 34));
		ladderSubTitleLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
		ladderSubTitleLabel.setBounds(0, 43, 621, 54);
		ladderPanel.add(ladderSubTitleLabel);
		
		JScrollPane laddersListScrollPane = new JScrollPane();
		laddersListScrollPane.setBounds(110, 110, 400, 300);
		ladderPanel.add(laddersListScrollPane);
	
		
		final JList<String> laddersJList = new JList();
		laddersJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		UpdateJList(laddersJList,database.getLadderDatabase().getLadderNames());

		
		// this adds to the JList the ability to ScrollDown
		laddersListScrollPane.setViewportView(laddersJList);
		
		
		// here you can add code to implement the Start Ladder function
		JButton ladderStartLadderButton = new JButton("Start Ladder!");

		ladderStartLadderButton.setForeground(Color.WHITE);
		ladderStartLadderButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		ladderStartLadderButton.setBackground(new Color(34, 139, 34));
		ladderStartLadderButton.setBounds(181, 450, 243, 72);
		ladderPanel.add(ladderStartLadderButton);
		
		JButton ladderBackToPlayPanelButton = new JButton("Back");
		ladderBackToPlayPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ladderPanel.setVisible(false);
				playPanel.setVisible(true);
			}
		});
		ladderBackToPlayPanelButton.setForeground(Color.WHITE);
		ladderBackToPlayPanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		ladderBackToPlayPanelButton.setBackground(new Color(34, 139, 34));
		ladderBackToPlayPanelButton.setBounds(20, 470, 100, 50);
		ladderPanel.add(ladderBackToPlayPanelButton);

		/**
		 * Play Ladder Panel start
		 * 
		 */
		
		playLadderPanel = new JPanel();
		playLadderPanel.setLayout(null);
		playLadderPanel.setBackground(new Color(245, 245, 220));
		playLadderPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(playLadderPanel);
		playLadderPanel.setVisible(false);
		
				
		
		JLabel playLadderTitleLabel = new JLabel("Ladder Challenge Puzzles:");
		playLadderTitleLabel.setToolTipText("");
		playLadderTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playLadderTitleLabel.setForeground(new Color(34, 139, 34));
		playLadderTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		playLadderTitleLabel.setBounds(0, 0, 621, 54);
		playLadderPanel.add(playLadderTitleLabel);
		
		
		JScrollPane ladderChallengePuzzlesListScrollPane = new JScrollPane();
		ladderChallengePuzzlesListScrollPane.setBounds(110, 110, 400, 300);
		playLadderPanel.add(ladderChallengePuzzlesListScrollPane);
		
		

		final JList ladderChallengePuzzlesJList = new JList();
		ladderChallengePuzzlesJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		// this adds to the JList the ability to ScrollDown
		ladderChallengePuzzlesListScrollPane.setViewportView(ladderChallengePuzzlesJList);
		UpdateJList(ladderChallengePuzzlesJList,database.getLadderDatabase().getLadderNames());

		

		
		
		JButton playLadderNextPuzzleButton = new JButton("Next Puzzle!");
		playLadderNextPuzzleButton.setForeground(Color.WHITE);
		playLadderNextPuzzleButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		playLadderNextPuzzleButton.setBackground(new Color(34, 139, 34));
		playLadderNextPuzzleButton.setBounds(181, 450, 243, 72);
		playLadderPanel.add(playLadderNextPuzzleButton);
		
		JButton playLadderBackToLadderPanelButton = new JButton("Back");
		playLadderBackToLadderPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playLadderPanel.setVisible(false);
				ladderPanel.setVisible(true);
			}
		});
		playLadderBackToLadderPanelButton.setForeground(Color.WHITE);
		playLadderBackToLadderPanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		playLadderBackToLadderPanelButton.setBackground(new Color(34, 139, 34));
		playLadderBackToLadderPanelButton.setBounds(20, 470, 100, 50);
		playLadderPanel.add(playLadderBackToLadderPanelButton);
		

		
		/**
		 * Play Ladder Panel end
		 * 
		 */
		

		customGamePanel = new JPanel();
		customGamePanel.setLayout(null);
		customGamePanel.setBackground(new Color(245, 245, 220));
		customGamePanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(customGamePanel);
		customGamePanel.setVisible(false);
		
		JLabel customGameTitleLabel = new JLabel("Custom Game!");
		customGameTitleLabel.setToolTipText("");
		customGameTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		customGameTitleLabel.setForeground(new Color(34, 139, 34));
		customGameTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		customGameTitleLabel.setBounds(0, 0, 621, 54);
		customGamePanel.add(customGameTitleLabel);
		
		JButton customBackToPlayPanelButton = new JButton("Back");
		customBackToPlayPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGamePanel.setVisible(false);
				playPanel.setVisible(true);
			}
		});
		
		customBackToPlayPanelButton.setForeground(Color.WHITE);
		customBackToPlayPanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		customBackToPlayPanelButton.setBackground(new Color(34, 139, 34));
		customBackToPlayPanelButton.setBounds(20, 470, 100, 50);
		customGamePanel.add(customBackToPlayPanelButton);
		
		
		// auto to panel vrisketai mesa sto customGamePanel kai tha xrhsimooieithei gia to preview tou puzzle pou thelei na paiksei o paiktis
		final JLabel customGamePreviewLabel = new JLabel();
		customGamePreviewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customGamePreviewLabel.setBounds(177, 230, 250, 200);
		customGamePanel.add(customGamePreviewLabel);
		
		customPuzzlesListScrollPane = new JScrollPane( );

		customPuzzlesListScrollPane.setBounds(150, 70, 300, 150);
		customGamePanel.add(customPuzzlesListScrollPane);
		
		final DefaultListModel model = new DefaultListModel();
		//Initiate list with the current puzzles taken from database


		final JList customPuzzlesJList = new JList(model);
		customPuzzlesJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		UpdateJList(customPuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
	

		customPuzzlesListScrollPane.setViewportView(customPuzzlesJList);
		
		/**
		 * Handles the preview of the image in Custom Game
		 */
		customPuzzlesJList.addListSelectionListener(new ListSelectionListener(){
			  public void valueChanged(ListSelectionEvent evt) {
				    if (!evt.getValueIsAdjusting()) {
				    	if(customGamePanel.isVisible()){
				    	int index = database.getPuzzleDatabase().getPuzzlesNames().indexOf(customPuzzlesJList.getSelectedValue());
				    	ImageIcon currentIcon = database.getPuzzleDatabase().getPuzzles().get(index).getImage();
				    	Image image = currentIcon.getImage(); // transform it 
				    	Image newimg = image.getScaledInstance(customGamePreviewLabel.getWidth(), customGamePreviewLabel.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				    	currentIcon = new ImageIcon(newimg);  // transform it back
				    	customGamePreviewLabel.setIcon(currentIcon);
				    	}
				    }
				  }
		});
		ladderStartLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ladderPanel.setVisible(false);
				playLadderPanel.setVisible(true);
				database.getLadderDatabase().getLadders().get(0).setCurrentLevel(0);
				int index = database.getLadderDatabase().getLadderNames().indexOf(laddersJList.getSelectedValue());
				UpdateJList(ladderChallengePuzzlesJList,database.getLadderDatabase().getLadders().get(index).getPuzzlesnames());
				score = 0;
				
				
			}
		});

		
		// here you can add code to implement the Start Game function
		JButton customGameStartGameButton = new JButton("Start Game!");
		customGameStartGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = database.getPuzzleDatabase().getPuzzlesNames().indexOf(customPuzzlesJList.getSelectedValue());
				BufferedImage imageInput;
				
				System.out.println(index);
				if(database.getPuzzleDatabase().getPuzzles().get(index) instanceof PuzzleJigsawData)
				{
					//Initiates Jigsaw puzzle
					PuzzleJigsawData current = (PuzzleJigsawData) database.getPuzzleDatabase().getPuzzles().get(index);

					imageInput = ConvertIconToBufferedImage(current.getImage());
					JigsawCutter varCutter = new JigsawCutter(current.getDifficulty(),current.getRotation());
					JigsawFrame jigframe = new JigsawFrame (imageInput, varCutter, ((PuzzleJigsawData)current).getRotation(),frmGotPuzzled);
					jigframe.begin();
					jigframe.setSize (1024, 740);
				    jigframe.setVisible(true);
				    frmGotPuzzled.dispose();

				}
				else
				{
					// sets visible the back to main menu button on the jMenu - it will be set nonvisible when will finish or close the sliding puzzle
					//backFromSlidingToMainMenuButton.setVisible(true);
					
					//Initiates Sliding puzzle
					imageInput = ConvertIconToBufferedImage(database.getPuzzleDatabase().getPuzzles().get(index).getImage());
					SlidingPuzzle slidingpuzzle = new SlidingPuzzle(database.getPuzzleDatabase().getPuzzles().get(index).getName(),
					imageInput,	database.getPuzzleDatabase().getPuzzles().get(index).getDifficulty(),frmGotPuzzled);
					slidingpuzzle.setVisible(true);
				    frmGotPuzzled.setVisible(false);
					/*
					frmGotPuzzled.setVisible(true);
					frmGotPuzzled.setResizable(true);
					frmGotPuzzled.setSize(800,800);*/
					
				}
			}
		});
		customGameStartGameButton.setForeground(Color.WHITE);
		customGameStartGameButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		customGameStartGameButton.setBackground(new Color(34, 139, 34));
		customGameStartGameButton.setBounds(181, 450, 243, 72);
		customGamePanel.add(customGameStartGameButton);
		
		editorPanel = new JPanel();
		editorPanel.setLayout(null);
		editorPanel.setBackground(new Color(245, 245, 220));
		editorPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(editorPanel);
		editorPanel.setVisible(false);
		
		JLabel editorTitleLabel = new JLabel("Editor");
		editorTitleLabel.setToolTipText("");
		editorTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorTitleLabel.setForeground(new Color(34, 139, 34));
		editorTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		editorTitleLabel.setBounds(0, 0, 621, 54);
		editorPanel.add(editorTitleLabel);
		
		JButton editorBackToMainMenuButton = new JButton("Back");
		editorBackToMainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});
		
		JButton editorCreateButton = new JButton("Create");
		editorCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPanel.setVisible(false);
				editorCreatePanel.setVisible(true);
			}
		});
		editorCreateButton.setForeground(Color.WHITE);
		editorCreateButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorCreateButton.setBackground(new Color(34, 139, 34));
		editorCreateButton.setBounds(181, 100, 243, 72);
		editorPanel.add(editorCreateButton);
		
		JButton editorExportButton = new JButton("Export");
		editorExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPanel.setVisible(false);
				editorExportPanel.setVisible(true);
			}
		});
		editorExportButton.setForeground(Color.WHITE);
		editorExportButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorExportButton.setBackground(new Color(34, 139, 34));
		editorExportButton.setBounds(181, 220, 243, 72);
		editorPanel.add(editorExportButton);
		
		JButton editorImportButton = new JButton("Import");

		editorImportButton.setForeground(Color.WHITE);
		editorImportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPanel.setVisible(false);
				editorImportPanel.setVisible(true);
			}
		});
		editorImportButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorImportButton.setBackground(new Color(34, 139, 34));
		editorImportButton.setBounds(181, 340, 243, 72);
		editorPanel.add(editorImportButton);
		editorBackToMainMenuButton.setBounds(20, 470, 100, 50);
		editorPanel.add(editorBackToMainMenuButton);
		editorBackToMainMenuButton.setForeground(Color.WHITE);
		editorBackToMainMenuButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		editorBackToMainMenuButton.setBackground(new Color(34, 139, 34));
			
	
		
		/**
		 * Editor Create Panel Start
		 */
		editorCreatePanel = new JPanel();
		editorCreatePanel.setLayout(null);
		editorCreatePanel.setBackground(new Color(245, 245, 220));
		editorCreatePanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(editorCreatePanel);
		editorCreatePanel.setVisible(false);
		
		JLabel editorCreateTitleLabel = new JLabel("Editor");
		editorCreateTitleLabel.setToolTipText("");
		editorCreateTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorCreateTitleLabel.setForeground(new Color(34, 139, 34));
		editorCreateTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		editorCreateTitleLabel.setBounds(0, 0, 621, 54);
		editorCreatePanel.add(editorCreateTitleLabel);
		
		JButton editorCreatePuzzleButton = new JButton("Create Puzzle");
		editorCreatePuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPuzzlePanel.setVisible(true);
				editorCreatePanel.setVisible(false);
			}
		});
		editorCreatePuzzleButton.setForeground(Color.WHITE);
		editorCreatePuzzleButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorCreatePuzzleButton.setBackground(new Color(34, 139, 34));
		editorCreatePuzzleButton.setBounds(181, 160, 243, 72);
		editorCreatePanel.add(editorCreatePuzzleButton);
		
		JButton createBackToEditorButton = new JButton("Back");
		createBackToEditorButton.setForeground(Color.WHITE);
		createBackToEditorButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		createBackToEditorButton.setBackground(new Color(34, 139, 34));
		createBackToEditorButton.setBounds(20, 470, 100, 50);
		editorCreatePanel.add(createBackToEditorButton);
		createBackToEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorCreatePanel.setVisible(false);
				editorPanel.setVisible(true);				
			}
		});
		
		
		JButton editorCreateLadderButton = new JButton("Create Ladder Challenge");
		editorCreateLadderButton.setForeground(Color.WHITE);
		editorCreateLadderButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		editorCreateLadderButton.setBackground(new Color(34, 139, 34));
		editorCreateLadderButton.setBounds(181, 280, 243, 72);
		editorCreatePanel.add(editorCreateLadderButton);
		editorCreateLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorCreatePanel.setVisible(false);
				createLadderPanel.setVisible(true);
			}
		});
		/**
		 * Editor Create Panel End
		 */
		
		
		
		/**
		 * Create Puzzle Panel Start
		 */
		createPuzzlePanel = new JPanel();
		createPuzzlePanel.setLayout(null);
		createPuzzlePanel.setBackground(new Color(245, 245, 220));
		createPuzzlePanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(createPuzzlePanel);
		createPuzzlePanel.setVisible(false);
		
		JLabel createPuzzleTitleLabel = new JLabel("Create Puzzle");
		createPuzzleTitleLabel.setToolTipText("");
		createPuzzleTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createPuzzleTitleLabel.setForeground(new Color(34, 139, 34));
		createPuzzleTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		createPuzzleTitleLabel.setBounds(0, 0, 621, 54);
		createPuzzlePanel.add(createPuzzleTitleLabel);
		
		JButton createPuzzleBackToCreateButton = new JButton("Back");
		createPuzzleBackToCreateButton.addActionListener(new createPuzzleBackToCreateListener());
		createPuzzleBackToCreateButton.setForeground(Color.WHITE);
		createPuzzleBackToCreateButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		createPuzzleBackToCreateButton.setBackground(new Color(34, 139, 34));
		createPuzzleBackToCreateButton.setBounds(20, 470, 100, 50);
		createPuzzlePanel.add(createPuzzleBackToCreateButton);
		
		final JLabel createPuzzleNewPuzzleNameLabel = new JLabel("Name:");
		createPuzzleNewPuzzleNameLabel.setBackground(new Color(238, 238, 238));
		createPuzzleNewPuzzleNameLabel.setToolTipText("");
		createPuzzleNewPuzzleNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createPuzzleNewPuzzleNameLabel.setForeground(new Color(34, 139, 34));
		createPuzzleNewPuzzleNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 27));
		createPuzzleNewPuzzleNameLabel.setBounds(220, 100, 200, 54);
		createPuzzlePanel.add(createPuzzleNewPuzzleNameLabel);
		
		createPuzzleNewPuzzleNameTextField = new JTextField();
		createPuzzleNewPuzzleNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		createPuzzleNewPuzzleNameTextField.setBackground(new Color(255, 255, 255));
		createPuzzleNewPuzzleNameTextField.setBounds(380, 115, 180, 30);
		createPuzzlePanel.add(createPuzzleNewPuzzleNameTextField);
		createPuzzleNewPuzzleNameTextField.setColumns(10);
		createPuzzleNewPuzzleNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				if (createPuzzleNewPuzzleNameTextField.getText().isEmpty()){
					createPuzzleButton.setEnabled(false);
				}
			}					
			public void insertUpdate(DocumentEvent arg0) {				
				createPuzzleButton.setEnabled(true);
			}		
			public void changedUpdate(DocumentEvent arg0) {	}
		});
		
		
		JButton createPuzzleLoadImageButton = new JButton("Load Image");
		createPuzzleLoadImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				image = fileChooser(frmGotPuzzled);
			}
		});
		createPuzzleLoadImageButton.setForeground(Color.WHITE);
		createPuzzleLoadImageButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		createPuzzleLoadImageButton.setBackground(new Color(34, 139, 34));
		createPuzzleLoadImageButton.setBounds(55, 95, 180, 52);
		createPuzzlePanel.add(createPuzzleLoadImageButton);
		
		final JRadioButton createPuzzleJigsawRadioButton = new JRadioButton("Jigsaw Puzzle");

		createPuzzleJigsawRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleJigsawRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleJigsawRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleJigsawRadioButton.setBounds(50, 220, 121, 24);
		createPuzzlePanel.add(createPuzzleJigsawRadioButton);
		
		final JRadioButton createPuzzleSlidingRadioButton = new JRadioButton("Sliding Puzzle",true);

		createPuzzleSlidingRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleSlidingRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleSlidingRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleSlidingRadioButton.setBounds(50, 250, 121, 24);
		createPuzzlePanel.add(createPuzzleSlidingRadioButton);
		
		ButtonGroup puzzleTypeButtonGroup = new ButtonGroup();
		puzzleTypeButtonGroup.add(createPuzzleJigsawRadioButton);
		puzzleTypeButtonGroup.add(createPuzzleSlidingRadioButton);
		
		createPuzzleRotationCheckBox = new JCheckBox("Rotation");
		createPuzzleRotationCheckBox.setForeground(new Color(34, 139, 34));
		createPuzzleRotationCheckBox.setBackground(new Color(245, 245, 220));
		createPuzzleRotationCheckBox.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleRotationCheckBox.setBounds(50, 280, 112, 24);
		createPuzzlePanel.add(createPuzzleRotationCheckBox);
		createPuzzleRotationCheckBox.setEnabled(false);
		
		JLabel createPuzzleDificultyLabel = new JLabel("Dificulty:");
		createPuzzleDificultyLabel.setToolTipText("");
		createPuzzleDificultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createPuzzleDificultyLabel.setForeground(new Color(34, 139, 34));
		createPuzzleDificultyLabel.setBackground(new Color(245, 245, 220));
		createPuzzleDificultyLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		createPuzzleDificultyLabel.setBounds(300, 180, 160, 30);
		createPuzzlePanel.add(createPuzzleDificultyLabel);
		
		final JRadioButton createPuzzleEasyRadioButton = new JRadioButton("Easy",true);
		createPuzzleEasyRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(createPuzzleJigsawRadioButton.isSelected()){
					createPuzzlePiecesTextField.setText("20");
				}
				if(createPuzzleSlidingRadioButton.isSelected())
					createPuzzlePiecesTextField.setText("3x3");
			}
		});

		createPuzzleEasyRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleEasyRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleEasyRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleEasyRadioButton.setBounds(300, 220, 121, 24);
		createPuzzlePanel.add(createPuzzleEasyRadioButton);
		
		final JRadioButton createPuzzleMediumRadioButton = new JRadioButton("Medium");
		createPuzzleMediumRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(createPuzzleJigsawRadioButton.isSelected()){
					createPuzzlePiecesTextField.setText("50");
				}
				if(createPuzzleSlidingRadioButton.isSelected())
					createPuzzlePiecesTextField.setText("4x4");
			}
			
		});
		createPuzzleMediumRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleMediumRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleMediumRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleMediumRadioButton.setBounds(300, 250, 121, 24);
		createPuzzlePanel.add(createPuzzleMediumRadioButton);
		
		JRadioButton createPuzzleHardRadioButton = new JRadioButton("Hard");
		createPuzzleHardRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(createPuzzleJigsawRadioButton.isSelected()){
					createPuzzlePiecesTextField.setText("100");
				}
				if(createPuzzleSlidingRadioButton.isSelected())
					createPuzzlePiecesTextField.setText("5x5");
			}
		});
		createPuzzleHardRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleHardRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleHardRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleHardRadioButton.setBounds(300, 280, 121, 24);
		createPuzzlePanel.add(createPuzzleHardRadioButton);
		
		ButtonGroup dificultyButtonGroup = new ButtonGroup();
		dificultyButtonGroup.add(createPuzzleEasyRadioButton);
		dificultyButtonGroup.add(createPuzzleMediumRadioButton);
		dificultyButtonGroup.add(createPuzzleHardRadioButton);
		
		
		JLabel createPuzzlePiecesLabel = new JLabel("Pieces:");
		createPuzzlePiecesLabel.setToolTipText("");
		createPuzzlePiecesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		createPuzzlePiecesLabel.setForeground(new Color(34, 139, 34));
		createPuzzlePiecesLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		createPuzzlePiecesLabel.setBackground(UIManager.getColor("Button.background"));
		createPuzzlePiecesLabel.setBounds(340, 310, 71, 30);
		createPuzzlePanel.add(createPuzzlePiecesLabel);
		
		createPuzzlePiecesTextField = new JTextField();
		createPuzzlePiecesTextField.setEditable(false);
		createPuzzlePiecesTextField.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		createPuzzlePiecesTextField.setColumns(10);
		createPuzzlePiecesTextField.setBackground(Color.WHITE);
		createPuzzlePiecesTextField.setBounds(420, 310, 62, 30);
		createPuzzlePanel.add(createPuzzlePiecesTextField);
		
		createPuzzleSlidingRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (createPuzzleSlidingRadioButton.isSelected()) {
					createPuzzleRotationCheckBox.setSelected(false);
					createPuzzleRotationCheckBox.setEnabled(false);
					if(createPuzzleEasyRadioButton.isSelected())
						createPuzzlePiecesTextField.setText("3x3");
					else if(createPuzzleMediumRadioButton.isSelected()){
						createPuzzlePiecesTextField.setText("4x4");
					}
					else{
						createPuzzlePiecesTextField.setText("5x5");
					}
				}
			}
		});
		
		createPuzzleJigsawRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (createPuzzleJigsawRadioButton.isSelected()){
					createPuzzleRotationCheckBox.setEnabled(true);
					if(createPuzzleEasyRadioButton.isSelected())
						createPuzzlePiecesTextField.setText("20");
					else if(createPuzzleMediumRadioButton.isSelected()){
						createPuzzlePiecesTextField.setText("50");
					}
					else{
						createPuzzlePiecesTextField.setText("100");
					}
						
				}
			}
		});
		
		
		//Gets all the parsed information from the user and creates the puzzle
		createPuzzleButton = new JButton("Create Puzzle");
		createPuzzleButton.setEnabled(false);

		createPuzzleButton.setForeground(Color.WHITE);
		createPuzzleButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		createPuzzleButton.setBackground(new Color(34, 139, 34));
		createPuzzleButton.setBounds(181, 450, 243, 72);
		createPuzzlePanel.add(createPuzzleButton);
		/**
		 * Create Puzzle Panel End
		 */

		
		/**
		 * Create Ladder Panel Start
		 */
		createLadderPanel = new JPanel();
		createLadderPanel.setLayout(null);
		createLadderPanel.setBackground(new Color(245, 245, 220));
		createLadderPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(createLadderPanel);
		createLadderPanel.setVisible(false);
		
		JLabel createLadderTitleLabel = new JLabel("Create Ladder Challenge");
		createLadderTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createLadderTitleLabel.setForeground(new Color(34, 139, 34));
		createLadderTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		createLadderTitleLabel.setBounds(0, 0, 621, 54);
		createLadderPanel.add(createLadderTitleLabel);
		
		
		final JLabel createLadderCustomLadderLabel = new JLabel("Ladder:");
		createLadderCustomLadderLabel.setBackground(new Color(238, 238, 238));
		createLadderCustomLadderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createLadderCustomLadderLabel.setForeground(new Color(34, 139, 34));
		createLadderCustomLadderLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		createLadderCustomLadderLabel.setBounds(5, 50, 200, 54);
		createLadderPanel.add(createLadderCustomLadderLabel);
		
		
		JScrollPane createLadderCustomLadderListScrollPane = new JScrollPane( );
		createLadderCustomLadderListScrollPane.setBounds(50, 100, 240, 250);
		createLadderPanel.add(createLadderCustomLadderListScrollPane);
		
		final JList createLadderCustomLadderJList = new JList();
		createLadderCustomLadderJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		createLadderCustomLadderListScrollPane.setViewportView(createLadderCustomLadderJList);

		
		final JLabel createLadderAvailablePuzzlesLabel = new JLabel("Available Puzzles:");
		createLadderAvailablePuzzlesLabel.setBackground(new Color(238, 238, 238));
		createLadderAvailablePuzzlesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createLadderAvailablePuzzlesLabel.setForeground(new Color(34, 139, 34));
		createLadderAvailablePuzzlesLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		createLadderAvailablePuzzlesLabel.setBounds(280, 50, 300, 54);
		createLadderPanel.add(createLadderAvailablePuzzlesLabel);
		
		
		JScrollPane createLadderAvailablePuzzlesListScrollPane = new JScrollPane( );
		
		createLadderAvailablePuzzlesListScrollPane.setBounds(310, 100, 260, 250);
		createLadderPanel.add(createLadderAvailablePuzzlesListScrollPane);
		
		final JList createLadderAvailablePuzzlesJList = new JList();
		createLadderAvailablePuzzlesJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		createLadderAvailablePuzzlesListScrollPane.setViewportView(createLadderAvailablePuzzlesJList);
		UpdateJList(createLadderAvailablePuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
		
		
		JButton createLadderBackToCreateButton = new JButton("Back");
		createLadderBackToCreateButton.addActionListener(new createPuzzleBackToCreateListener());
		createLadderBackToCreateButton.setForeground(Color.WHITE);
		createLadderBackToCreateButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		createLadderBackToCreateButton.setBackground(new Color(34, 139, 34));
		createLadderBackToCreateButton.setBounds(20, 470, 100, 50);
		createLadderBackToCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLadderPanel.setVisible(false);
				editorCreatePanel.setVisible(true);				
			}
		});
		createLadderPanel.add(createLadderBackToCreateButton);
		
		
		final JLabel createLadderNewLadderNameLabel = new JLabel("Name:");
		createLadderNewLadderNameLabel.setBackground(new Color(238, 238, 238));
		createLadderNewLadderNameLabel.setToolTipText("");
		createLadderNewLadderNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createLadderNewLadderNameLabel.setForeground(new Color(34, 139, 34));
		createLadderNewLadderNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 27));
		createLadderNewLadderNameLabel.setBounds(120, 395, 200, 54);
		createLadderPanel.add(createLadderNewLadderNameLabel);
		
		
		createLadderNewLadderNameTextField = new JTextField();
		createLadderNewLadderNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		createLadderNewLadderNameTextField.setBackground(new Color(255, 255, 255));
		createLadderNewLadderNameTextField.setBounds(275, 410, 180, 30);
		createLadderPanel.add(createLadderNewLadderNameTextField);
		createLadderNewLadderNameTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {	
				if(createLadderNewLadderNameTextField.getText().isEmpty()){
					createLadderButton.setEnabled(false);
				}
			}					
			public void insertUpdate(DocumentEvent arg0) {				
				createLadderButton.setEnabled(true);
			}		
			public void changedUpdate(DocumentEvent arg0) {	}
		});		
		
		//createLadderNewLadderNameTextField.setColumns(10);
		
		
		// ADD PUZZLE TO CUSTOM LADDER LIST
		JButton createLadderAddPuzzleToCustomLadderButton = new JButton("Add");
		createLadderAddPuzzleToCustomLadderButton.setForeground(Color.WHITE);
		createLadderAddPuzzleToCustomLadderButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		createLadderAddPuzzleToCustomLadderButton.setBackground(new Color(34, 139, 34));
		createLadderAddPuzzleToCustomLadderButton.setBounds(255, 355, 100, 42);
		createLadderPanel.add(createLadderAddPuzzleToCustomLadderButton);		
		// here we implement the createLadderAddPuzzleToCustomLadderButton button
		final ArrayList<PuzzleData> temp_ladder_puzzles = new ArrayList<PuzzleData>();
		final ArrayList<String> temp_ladder_puzzles_names = new ArrayList<String>();
		createLadderAddPuzzleToCustomLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = database.getPuzzleDatabase().getPuzzlesNames().indexOf(createLadderAvailablePuzzlesJList.getSelectedValue());
				temp_ladder_puzzles.add(database.getPuzzleDatabase().getPuzzles().get(index));
				temp_ladder_puzzles_names.add(database.getPuzzleDatabase().getPuzzlesNames().get(index));
				System.out.println("Puzzle Added to Ladder");
				UpdateJList(createLadderCustomLadderJList,temp_ladder_puzzles_names);
			}
		});		
		

		
		// CLEAR CREATE LADDER INFORMATION
		JButton clearCreateLadderInformationButton = new JButton("Clear");
		clearCreateLadderInformationButton.setForeground(Color.WHITE);
		clearCreateLadderInformationButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		clearCreateLadderInformationButton.setBackground(new Color(34, 139, 34));
		clearCreateLadderInformationButton.setBounds(470, 465, 100, 42);
		createLadderPanel.add(clearCreateLadderInformationButton);		
		// here we implement the clear ladder information button
		clearCreateLadderInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp_ladder_puzzles.clear();
				temp_ladder_puzzles_names.clear();
				UpdateJList(createLadderCustomLadderJList,temp_ladder_puzzles_names);
			}
		});		
		
		
		// CREATE LADDER BUTTON
		//Gets all the information for the puzzles and creates the ladder
		createLadderButton = new JButton("Create Ladder");
		createLadderButton.setForeground(Color.WHITE);
		createLadderButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		createLadderButton.setBackground(new Color(34, 139, 34));
		createLadderButton.setBounds(181, 450, 243, 72);
		createLadderPanel.add(createLadderButton);		
		createLadderButton.setEnabled(false);
	
		/**
		 * Create Ladder Panel End
		 */
		
		
		
		
		
		/**
		 * Editor Export Panel Start
		 */
		editorExportPanel = new JPanel();
		editorExportPanel.setLayout(null);
		editorExportPanel.setBackground(new Color(245, 245, 220));
		editorExportPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(editorExportPanel);
		editorExportPanel.setVisible(false);
		
		JLabel editorExportTitleLabel = new JLabel("Export");
		editorExportTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorExportTitleLabel.setForeground(new Color(34, 139, 34));
		editorExportTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		editorExportTitleLabel.setBounds(0, 0, 621, 54);
		editorExportPanel.add(editorExportTitleLabel);
		
		
		JButton editorExportBackToEditorButton = new JButton("Back");
		editorExportBackToEditorButton.setForeground(Color.WHITE);
		editorExportBackToEditorButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		editorExportBackToEditorButton.setBackground(new Color(34, 139, 34));
		editorExportBackToEditorButton.setBounds(20, 470, 100, 50);
		editorExportBackToEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorExportPanel.setVisible(false);
				editorPanel.setVisible(true);	
			}
		});
		editorExportPanel.add(editorExportBackToEditorButton);

	
		JButton editorExportPuzzlePanelButton = new JButton("Export Puzzle");

		editorExportPuzzlePanelButton.setForeground(Color.WHITE);
		editorExportPuzzlePanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorExportPuzzlePanelButton.setBackground(new Color(34, 139, 34));
		editorExportPuzzlePanelButton.setBounds(181, 140, 243, 72);
		editorExportPanel.add(editorExportPuzzlePanelButton);
		
		
	
		JButton editorExportLadderPanelButton = new JButton("Export Ladder");
		editorExportLadderPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorExportPanel.setVisible(false);
				editorExportLadderPanel.setVisible(true);
			}
		});
		editorExportLadderPanelButton.setForeground(Color.WHITE);
		editorExportLadderPanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorExportLadderPanelButton.setBackground(new Color(34, 139, 34));
		editorExportLadderPanelButton.setBounds(181, 250, 243, 72);
		editorExportPanel.add(editorExportLadderPanelButton);
		/**
		 * Editor Export Panel End
		 */
		
		
		
		/**
		 * Editor Export Puzzle Panel Start
		 */
		editorExportPuzzlePanel = new JPanel();
		editorExportPuzzlePanel.setLayout(null);
		editorExportPuzzlePanel.setBackground(new Color(245, 245, 220));
		editorExportPuzzlePanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(editorExportPuzzlePanel);
		editorExportPuzzlePanel.setVisible(false);
		
		JLabel editorExportPuzzleTitleLabel = new JLabel("Export Puzzle");
		editorExportPuzzleTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorExportPuzzleTitleLabel.setForeground(new Color(34, 139, 34));
		editorExportPuzzleTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		editorExportPuzzleTitleLabel.setBounds(0, 0, 621, 54);
		editorExportPuzzlePanel.add(editorExportPuzzleTitleLabel);
		
		
		JLabel editorExportPuzzleSubTitleLabel = new JLabel("Choose the Puzzle you want to export.");
		editorExportPuzzleSubTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorExportPuzzleSubTitleLabel.setForeground(new Color(34, 139, 34));
		editorExportPuzzleSubTitleLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
		editorExportPuzzleSubTitleLabel.setBounds(0, 50, 621, 54);
		editorExportPuzzlePanel.add(editorExportPuzzleSubTitleLabel);
		

		JScrollPane editorCustomPuzzlesListScrollPane = new JScrollPane( );
		editorCustomPuzzlesListScrollPane.setBounds(155, 120, 300, 270);
		editorExportPuzzlePanel.add(editorCustomPuzzlesListScrollPane);
	
		
		final DefaultListModel exportModel = new DefaultListModel();
		//Initiate list with the current puzzles taken from database
		//database.getPuzzleDatabase().testDatabase(); 

		final JList exportCustomPuzzlesJList = new JList(exportModel);
		exportCustomPuzzlesJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		UpdateJList(exportCustomPuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
		
		
		editorCustomPuzzlesListScrollPane.setViewportView(exportCustomPuzzlesJList);
		
		
		
		JButton editorExportPuzzleBackToExportButton = new JButton("Back");
		editorExportPuzzleBackToExportButton.setForeground(Color.WHITE);
		editorExportPuzzleBackToExportButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		editorExportPuzzleBackToExportButton.setBackground(new Color(34, 139, 34));
		editorExportPuzzleBackToExportButton.setBounds(20, 470, 100, 50);
		editorExportPuzzleBackToExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorExportPuzzlePanel.setVisible(false);	
				editorExportPanel.setVisible(true);				
			}
		});
		editorExportPuzzlePanel.add(editorExportPuzzleBackToExportButton);
		
		// EXPORT PUZZLE BUTTON (JFILE CHOOSER)
		JButton editorExportPuzzleButton = new JButton("Export Puzzle!");
		editorExportPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = database.getPuzzleDatabase().getPuzzlesNames().indexOf(exportCustomPuzzlesJList.getSelectedValue());
				 database.getPuzzleDatabase().exportPuzzle(database.getPuzzleDatabase().getPuzzlesNames().get(index));
			}
		});
		editorExportPuzzleButton.setForeground(Color.WHITE);
		editorExportPuzzleButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorExportPuzzleButton.setBackground(new Color(34, 139, 34));
		editorExportPuzzleButton.setBounds(181, 450, 243, 72);
		editorExportPuzzlePanel.add(editorExportPuzzleButton);
		editorExportPuzzlePanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorExportPanel.setVisible(false);
				editorExportPuzzlePanel.setVisible(true);
				UpdateJList(exportCustomPuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
			}
		});
		/**
		 * Editor Export Puzzle Panel End
		 */
		
		
		
		
		/**
		 * Editor Export Ladder Panel Start
		 */
		editorExportLadderPanel = new JPanel();
		editorExportLadderPanel.setLayout(null);
		editorExportLadderPanel.setBackground(new Color(245, 245, 220));
		editorExportLadderPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(editorExportLadderPanel);
		editorExportLadderPanel.setVisible(false);
		
		JLabel editorExportLadderTitleLabel = new JLabel("Export Ladder");
		editorExportLadderTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorExportLadderTitleLabel.setForeground(new Color(34, 139, 34));
		editorExportLadderTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		editorExportLadderTitleLabel.setBounds(0, 0, 621, 54);
		editorExportLadderPanel.add(editorExportLadderTitleLabel);
		
		
		JLabel editorExportLadderSubTitleLabel = new JLabel("Choose the Ladder you want to export.");
		editorExportLadderSubTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorExportLadderSubTitleLabel.setForeground(new Color(34, 139, 34));
		editorExportLadderSubTitleLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
		editorExportLadderSubTitleLabel.setBounds(0, 50, 621, 54);
		editorExportLadderPanel.add(editorExportLadderSubTitleLabel);
		

		
		JScrollPane editorLaddersListScrollPane = new JScrollPane( );

		editorLaddersListScrollPane.setBounds(155, 120, 300, 270);
		editorExportLadderPanel.add(editorLaddersListScrollPane);
	
		
		final DefaultListModel exportModel2 = new DefaultListModel();
		//Initiate list with the current puzzles taken from database
		//database.getPuzzleDatabase().testDatabase(); 

		final JList exportLaddersJList = new JList(exportModel2);
		exportLaddersJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		
		editorLaddersListScrollPane.setViewportView(exportLaddersJList);
		UpdateJList(exportLaddersJList,database.getLadderDatabase().getLadderNames());
		
		
		
		JButton editorExportLadderBackToExportButton = new JButton("Back");
		editorExportLadderBackToExportButton.setForeground(Color.WHITE);
		editorExportLadderBackToExportButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		editorExportLadderBackToExportButton.setBackground(new Color(34, 139, 34));
		editorExportLadderBackToExportButton.setBounds(20, 470, 100, 50);
		editorExportLadderBackToExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorExportLadderPanel.setVisible(false);	
				editorExportPanel.setVisible(true);				
			}
		});
		editorExportLadderPanel.add(editorExportLadderBackToExportButton);
		
		// EXPORT LADDER BUTTON (JFILE CHOOSER)
		JButton editorExportLadderButton = new JButton("Export Ladder!");
		editorExportLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				 
				 int index = database.getLadderDatabase().getLadderNames().indexOf(exportLaddersJList.getSelectedValue());
				 database.getLadderDatabase().exportLadder(database.getLadderDatabase().getLadderNames().get(index));
				
			}
		});
		editorExportLadderButton.setForeground(Color.WHITE);
		editorExportLadderButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorExportLadderButton.setBackground(new Color(34, 139, 34));
		editorExportLadderButton.setBounds(181, 450, 243, 72);
		editorExportLadderPanel.add(editorExportLadderButton);
		/**
		 * Editor Export Puzzle Panel End
		 */
		
		// here we implement the create ladder button
		createLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("create ladder pressed");
				String name = createLadderNewLadderNameTextField.getText() ;
				database.getLadderDatabase().getLadders().add(new LadderChallenge (name,temp_ladder_puzzles.size(),temp_ladder_puzzles));
				database.getLadderDatabase().getLadderNames().add(name);
				UpdateJList(laddersJList,database.getLadderDatabase().getLadderNames());
				UpdateJList(exportLaddersJList,database.getLadderDatabase().getLadderNames());;
				database.getLadderDatabase().getLoadsave().save(database.getLadderDatabase().getLadders());
				
				
			}
		});	

		/**
		 * Editor Import Panel Start
		 */
		editorImportPanel = new JPanel();
		editorImportPanel.setLayout(null);
		editorImportPanel.setBackground(new Color(245, 245, 220));
		editorImportPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(editorImportPanel);
		editorImportPanel.setVisible(false);
		
		JLabel editorImportTitleLabel = new JLabel("Import");
		editorImportTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editorImportTitleLabel.setForeground(new Color(34, 139, 34));
		editorImportTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		editorImportTitleLabel.setBounds(0, 0, 621, 54);
		editorImportPanel.add(editorImportTitleLabel);
		
		
		JButton editorImportBackToEditorButton = new JButton("Back");
		editorImportBackToEditorButton.setForeground(Color.WHITE);
		editorImportBackToEditorButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		editorImportBackToEditorButton.setBackground(new Color(34, 139, 34));
		editorImportBackToEditorButton.setBounds(20, 470, 100, 50);
		editorImportBackToEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorImportPanel.setVisible(false);
				editorPanel.setVisible(true);	
			}
		});
		editorImportPanel.add(editorImportBackToEditorButton);

	
		JButton editorImportPuzzlePanelButton = new JButton("Import Puzzle");
		editorImportPuzzlePanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				File file = null;
				file = fileChooserUI(frmGotPuzzled);
				PuzzleData importedPuzzle = null;
				importedPuzzle = database.getPuzzleDatabase().loadPuzzle(file);
				if(importedPuzzle != null){
					database.getPuzzleDatabase().importPuzzle(importedPuzzle,database.getPuzzleDatabase().getPuzzles(),database.getPuzzleDatabase().getPuzzlesNames());
					UpdateJList(exportCustomPuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
					UpdateJList(customPuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
					UpdateJList(createLadderAvailablePuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
				}
			}
		});
		editorImportPuzzlePanelButton.setForeground(Color.WHITE);
		editorImportPuzzlePanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorImportPuzzlePanelButton.setBackground(new Color(34, 139, 34));
		editorImportPuzzlePanelButton.setBounds(181, 140, 243, 72);
		editorImportPanel.add(editorImportPuzzlePanelButton);
		
		
	
		JButton editorImportLadderPanelButton = new JButton("Import Ladder");
		editorImportLadderPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File file = null;
				file = fileChooserUI(frmGotPuzzled);
				LadderChallenge importedLadder = null;
				importedLadder = database.getLadderDatabase().loadLadder(file);
				if(importedLadder != null){
					database.getLadderDatabase().importLadder(importedLadder, database.getLadderDatabase().getLadders(), database.getLadderDatabase().getLadderNames());
					database.getLadderDatabase().getLoadsave().save(database.getLadderDatabase().getLadders());
					UpdateJList(ladderChallengePuzzlesJList,database.getLadderDatabase().getLadderNames());
					UpdateJList(exportLaddersJList,database.getLadderDatabase().getLadderNames());
				}

			}
		});
		editorImportLadderPanelButton.setForeground(Color.WHITE);
		editorImportLadderPanelButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorImportLadderPanelButton.setBackground(new Color(34, 139, 34));
		editorImportLadderPanelButton.setBounds(181, 250, 243, 72);
		editorImportPanel.add(editorImportLadderPanelButton);
		/**
		 * Editor Import Panel End
		 */
		createPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				int difficulty;
				boolean rotation;
				String name;
				
				
				rotation = createPuzzleRotationCheckBox.isSelected(); 
				if(createPuzzleEasyRadioButton.isSelected())
				{
					difficulty = 1;
				}
				else if(createPuzzleMediumRadioButton.isSelected())
				{
					difficulty = 2;
				}
				else
				{
					difficulty = 3;
				}
	
				name = createPuzzleNewPuzzleNameTextField.getText();
				//Checking if image has been loaded, name already exists in database or name field is empty
				if(image != null && (name != "" && !database.getPuzzleDatabase().getPuzzlesNames().contains(name)) ){
					if(createPuzzleJigsawRadioButton.isSelected())
					{
						database.getPuzzleDatabase().getPuzzles().add(new PuzzleJigsawData(name,image,difficulty,rotation));
						database.getPuzzleDatabase().getPuzzlesNames().add(name);
					}
					if(createPuzzleSlidingRadioButton.isSelected())
					{
						database.getPuzzleDatabase().getPuzzles().add(new PuzzleData(name,image,difficulty));
						database.getPuzzleDatabase().getPuzzlesNames().add(name);
					}
					UpdateJList(customPuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
					UpdateJList(createLadderAvailablePuzzlesJList,database.getPuzzleDatabase().getPuzzlesNames());
					database.getPuzzleDatabase().getLoadsave().save(database.getPuzzleDatabase().getPuzzles());
					System.out.println(name + " puzzle got saved!");
					
						
				}
				else 
					System.out.println("Please check your input");

				
			}
		});
		
		
		
		
		
		optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBackground(new Color(245, 245, 220));
		optionsPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(optionsPanel);
		optionsPanel.setVisible(false);
		
		JLabel optionsTitleLabel;
		optionsTitleLabel = new JLabel("Options");
		optionsTitleLabel.setToolTipText("");
		optionsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionsTitleLabel.setForeground(new Color(34, 139, 34));
		optionsTitleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 39));
		optionsTitleLabel.setBounds(0, 0, 621, 54);
		optionsPanel.add(optionsTitleLabel);
		
		
		JLabel optionMusicLabel = new JLabel("Music:");
		optionMusicLabel.setToolTipText("");
		optionMusicLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionMusicLabel.setForeground(new Color(34, 139, 34));
		optionMusicLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
		optionMusicLabel.setBackground(new Color(245, 245, 220));
		optionMusicLabel.setBounds(200, 150, 160, 30);
		optionsPanel.add(optionMusicLabel);
		
		
		
		
		
		optionsMusicOnRadioButton = new JRadioButton("On", true);
		optionsMusicOnRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sound.startMusic();
			}
		});
		optionsMusicOnRadioButton.setForeground(Color.BLACK);
		optionsMusicOnRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		optionsMusicOnRadioButton.setBackground(new Color(245, 245, 220));
		optionsMusicOnRadioButton.setBounds(330, 140, 121, 24);
		optionsPanel.add(optionsMusicOnRadioButton);
		
		
		
		optionsMusicOffRadioButton = new JRadioButton("Off", false);
		optionsMusicOffRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.stopMusic();
			}
		});
		optionsMusicOffRadioButton.setForeground(Color.BLACK);
		optionsMusicOffRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		optionsMusicOffRadioButton.setBackground(new Color(245, 245, 220));
		optionsMusicOffRadioButton.setBounds(330, 170, 121, 24);
		optionsPanel.add(optionsMusicOffRadioButton);
		
		
		
				
		JButton optionsBackToMainMenuButton;
		optionsBackToMainMenuButton = new JButton("Back");
		optionsBackToMainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				optionsPanel.setVisible(false);
				mainMenuPanel.setVisible(true);				
			}
		});
		
		optionsBackToMainMenuButton.setForeground(Color.WHITE);
		optionsBackToMainMenuButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		optionsBackToMainMenuButton.setBackground(new Color(34, 139, 34));
		optionsBackToMainMenuButton.setBounds(20, 470, 100, 50);
		optionsPanel.add(optionsBackToMainMenuButton);
		
		ButtonGroup musicRadioButtonGroup = new ButtonGroup();
		musicRadioButtonGroup.add(optionsMusicOnRadioButton);
		musicRadioButtonGroup.add(optionsMusicOffRadioButton);
		
		
		leaderboardsPanel = new JPanel();
		leaderboardsPanel.setLayout(null);
		leaderboardsPanel.setBackground(new Color(245, 245, 220));
		leaderboardsPanel.setBounds(0, 0, 631, 557);
		frmGotPuzzled.getContentPane().add(leaderboardsPanel);
		leaderboardsPanel.setVisible(false);
		
		JLabel leaderboardsTitleLabel;
		leaderboardsTitleLabel = new JLabel("Leaderboards");
		leaderboardsTitleLabel.setToolTipText("");
		leaderboardsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leaderboardsTitleLabel.setForeground(new Color(34, 139, 34));
		leaderboardsTitleLabel.setFont(new Font("Dialog", Font.BOLD, 39));
		leaderboardsTitleLabel.setBounds(0, 0, 621, 54);
		leaderboardsPanel.add(leaderboardsTitleLabel);
		
		JButton leaderboardsBackToPlayButton = new JButton("Back");
		leaderboardsBackToPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leaderboardsPanel.setVisible(false);
				playPanel.setVisible(true);
			}
		});
		leaderboardsBackToPlayButton.setForeground(Color.WHITE);
		leaderboardsBackToPlayButton.setFont(new Font("Dialog", Font.BOLD, 20));
		leaderboardsBackToPlayButton.setBackground(new Color(34, 139, 34));
		leaderboardsBackToPlayButton.setBounds(20, 470, 100, 50);
		leaderboardsPanel.add(leaderboardsBackToPlayButton);
		//
		
		JScrollPane leaderboardsScrollPaneNames = new JScrollPane();
		leaderboardsScrollPaneNames.setBounds(150, 70, 162, 370);
		leaderboardsPanel.add(leaderboardsScrollPaneNames);
		final JList leaderboardsJListNames = new JList();
		leaderboardsJListNames.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		leaderboardsScrollPaneNames.setViewportView(leaderboardsJListNames);
		//
		JScrollPane leaderboardsScrollPaneScore = new JScrollPane();
		leaderboardsScrollPaneScore.setBounds(312, 70, 162, 370);
		leaderboardsPanel.add(leaderboardsScrollPaneScore);
		final JList leaderboardsJListScores = new JList();
		leaderboardsJListScores.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		leaderboardsJListScores.setBounds(0, 0, 321, 367);
		leaderboardsScrollPaneScore.setViewportView(leaderboardsJListScores);
		UpdateJList(leaderboardsJListNames,database.getPlayersDatabase().getNames());
		UpdateJList(leaderboardsJListScores,database.getPlayersDatabase().getScores());
		// here you can add code to implement the NEXT PUZZLE Ladder function

		playLadderNextPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = database.getLadderDatabase().getLadderNames().indexOf(laddersJList.getSelectedValue());

				if(database.getLadderDatabase().getLadders().get(index).getCurrentLevel() < database.getLadderDatabase().getLadders().get(index).getLevelNumber()){
					int indexCurrentLevel = database.getLadderDatabase().getLadders().get(index).getCurrentLevel();
					BufferedImage imageInput;
					
					System.out.println(indexCurrentLevel);
					if(database.getLadderDatabase().getLadders().get(index).getPuzzles().get(indexCurrentLevel) instanceof PuzzleJigsawData)
					{
						//Initiates Jigsaw puzzle
						PuzzleJigsawData current = (PuzzleJigsawData) database.getLadderDatabase().getLadders().get(index).getPuzzles().get(indexCurrentLevel);

						imageInput = ConvertIconToBufferedImage(current.getImage());
						JigsawCutter varCutter = new JigsawCutter(current.getDifficulty(),current.getRotation());
						JigsawFrame jigframe = new JigsawFrame (imageInput, varCutter, ((PuzzleJigsawData)current).getRotation(),frmGotPuzzled);
						jigframe.begin();
						jigframe.setSize (1024, 740);
					    jigframe.setVisible(true);
					    frmGotPuzzled.setVisible(false);
					}
					else
					{
						// sets visible the back to main menu button on the jMenu - it will be set nonvisible when will finish or close the sliding puzzle
						//backFromSlidingToMainMenuButton.setVisible(true);
						
						//Initiates Sliding puzzle
						imageInput = ConvertIconToBufferedImage(database.getPuzzleDatabase().getPuzzles().get(indexCurrentLevel).getImage());
						SlidingPuzzle slidingpuzzle = new SlidingPuzzle(database.getPuzzleDatabase().getPuzzles().get(indexCurrentLevel).getName(),
								imageInput,	database.getPuzzleDatabase().getPuzzles().get(indexCurrentLevel).getDifficulty(),frmGotPuzzled);
						slidingpuzzle.setVisible(true);
					    frmGotPuzzled.setVisible(false);
						
					}

					score  += database.getLadderDatabase().getLadders().get(index).getPuzzles().get(indexCurrentLevel).getDifficulty()*200;
					database.getLadderDatabase().getLadders().get(index).setCurrentLevel(database.getLadderDatabase().getLadders().get(index).getCurrentLevel()+1);

					
					System.out.println(score);

				}
				else{
					JOptionPane.showMessageDialog(null,"Ladder Challenge Finished!" , "You Won", JOptionPane.PLAIN_MESSAGE);
					System.out.println(database.getPlayersDatabase().getPlayers().size());

					for(int i = database.getPlayersDatabase().getPlayers().size()-1;i>=0;i--){
						if(database.getPlayersDatabase().getPlayers().get(i).getScore() < score){
							String name = JOptionPane.showInputDialog(frmGotPuzzled, "What's your name?");
							Player newPlayer = new Player(name,score);
							database.getPlayersDatabase().getPlayers().set(i, newPlayer);
							i = -1;
						}
					}
					database.getPlayersDatabase().UpdateStringArrays();
	
					UpdateJList(leaderboardsJListNames,database.getPlayersDatabase().getNames());
					UpdateJList(leaderboardsJListScores,database.getPlayersDatabase().getScores());
					database.getPlayersDatabase().getLoadSave().save(database.getPlayersDatabase().getPlayers());
					playLadderPanel.setVisible(false);
					playPanel.setVisible(true);
					
				}
			}
		});
		

	}
	
	
	// createPuzzleBackToCreateButton Listener implementation
	// it's been implemented here as a class
	// edw tha prostethei kai h dynatothta me to back to user na ginontai erase ola ta pedia tou createPuzzlePanel
	class createPuzzleBackToCreateListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			createPuzzlePanel.setVisible(false);
			editorCreatePanel.setVisible(true);
			
		}
	}
	

	public static ImageIcon fileChooser(Component comp){
		ImageIcon image=null;
		MediaTracker tracker = new MediaTracker(comp);
 
		
		JFileChooser fc = new JFileChooser(System.getProperty("user.home")+"\\Desktop\\");
		FileFilter filter = new FileNameExtensionFilter("PNG/JPG/BMP","png","jpg","bmp");
		fc.setFileFilter(filter);
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try{
				tracker.waitForAll();
				image =new ImageIcon(file.getAbsolutePath());
			} catch (Exception exp){
				exp.printStackTrace();
				
			}
			
		}else{
			System.out.println("Bye");
		}
		return image;
	}
	
	
	/**Chooses a file from the PC and returns it.
	 * 
	 */

	public static File fileChooserUI(Component comp){
		JFileChooser fc = new JFileChooser(System.getProperty("user.home")+"\\Desktop\\");
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			return file;
		}
		else
			return null;

	}
	
	/** Converts ImageIcon to BufferedImage in order to be used on both puzzles.
	 */
	
	private BufferedImage ConvertIconToBufferedImage(ImageIcon icon){
		BufferedImage bi = new BufferedImage(
			    icon.getIconWidth(),
			    icon.getIconHeight(),
			    BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			// paint the Icon to the BufferedImage.
			icon.paintIcon(null, g, 0,0);
			g.dispose();
			return bi;
	}

	/** Updates the List in order to be visible on JLists
	 * 
	 */
	
	private void UpdateJList(JList<String> list, ArrayList<String> elements){
	    model = new DefaultListModel<String>();
	    for(String p : elements){
	         model.addElement(p);
	    }    
	    list.setModel(model);     
	    list.setSelectedIndex(0);
	}
}
