package guiPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import MainPackage.Database;
import puzzlePackage.PuzzleDatabase;

public class MainGUI {
	
	/*
	 * here we post all frames and panels to be visible and enable for whole code
	 */
	// kentriko vasiko frame - panw sto opoio kathontai ola ta panels
	Database database = new Database();
	
	
	
	private JFrame frmGotPuzzled;

	
	// kentriko panel
	private JPanel mainMenuPanel;
	
	private JPanel playPanel; 
	
	private JPanel ladderChallengePanel;
	
	private JPanel customGamePanel;
	
	
	
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
					MainGUI window = new MainGUI();
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
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGotPuzzled = new JFrame();
		frmGotPuzzled.getContentPane().setBackground(new Color(240, 255, 255));
		frmGotPuzzled.setTitle("Got Puzzled 1.0");
		frmGotPuzzled.setBounds(100, 100, 703, 465);
		frmGotPuzzled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmGotPuzzled.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainGUI.class.getResource("/guiPackage/img/exit.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutGotPuzzled = new JMenuItem("About Got Puzzled");
		mntmAboutGotPuzzled.setIcon(new ImageIcon(MainGUI.class.getResource("/guiPackage/img/info.png")));
		mnAbout.add(mntmAboutGotPuzzled);
		frmGotPuzzled.getContentPane().setLayout(null);
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuPanel.setBackground(new Color(224, 255, 255));
		mainMenuPanel.setBounds(12, 12, 663, 379);
		frmGotPuzzled.getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		
		JLabel mainTitle = new JLabel("Got Puzzled!");
		mainTitle.setBounds(139, 12, 357, 31);
		mainTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 26));
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuPanel.add(mainTitle);
		
		JButton playButton = new JButton("Play!");
		playButton.setBounds(225, 71, 177, 64);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenuPanel.setVisible(false);
				playPanel.setVisible(true);
			}
		});
		playButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		mainMenuPanel.add(playButton);
		
		JButton editorButton = new JButton("Editor");
		editorButton.setBounds(225, 147, 177, 64);
		editorButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		mainMenuPanel.add(editorButton);
		
		JButton optionsButton = new JButton("Options");
		optionsButton.setBounds(225, 223, 177, 64);
		optionsButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		mainMenuPanel.add(optionsButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(225, 299, 177, 64);
		exitButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		mainMenuPanel.add(exitButton);
		
		playPanel = new JPanel();
		playPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		playPanel.setBackground(new Color(224, 255, 255));
		playPanel.setBounds(12, 12, 663, 379);
		frmGotPuzzled.getContentPane().add(playPanel);
		// creates the playPanel and set it non visible
		// playPanel will become visible when the user will press the playButton
		playPanel.setVisible(false);
		
		JButton returnToMainPanelButton = new JButton("back");
		returnToMainPanelButton.setBounds(27, 280, 80, 80);
		returnToMainPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});
		playPanel.setLayout(null);
		
		JLabel playTitle = new JLabel("Play!");
		playTitle.setHorizontalAlignment(SwingConstants.CENTER);
		playTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 26));
		playTitle.setBounds(139, 12, 357, 31);
		playPanel.add(playTitle);
		returnToMainPanelButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		playPanel.add(returnToMainPanelButton);
		
		JButton ladderChallengeButton = new JButton("Ladder Challenge");
		ladderChallengeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ladderChallengePanel.setVisible(true);
				playPanel.setVisible(false);
			}
		});
		ladderChallengeButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		ladderChallengeButton.setBounds(225, 71, 175, 62);
		playPanel.add(ladderChallengeButton);
		
		JButton customGameButton = new JButton("Custom Game");
		customGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGamePanel.setVisible(true);
				playPanel.setVisible(false);
			}
		});
		customGameButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		customGameButton.setBounds(225, 147, 175, 62);
		playPanel.add(customGameButton);
		
		ladderChallengePanel = new JPanel();
		ladderChallengePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ladderChallengePanel.setBackground(new Color(224, 255, 255));
		ladderChallengePanel.setBounds(12, 12, 663, 379);
		frmGotPuzzled.getContentPane().add(ladderChallengePanel);
		ladderChallengePanel.setVisible(false);
		
		
		JButton returnToPlayPanelButton = new JButton("back");
		returnToPlayPanelButton.setBounds(27, 280, 80, 80);
		returnToPlayPanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ladderChallengePanel.setVisible(false);
				playPanel.setVisible(true);
			}
		});
		ladderChallengePanel.setLayout(null);
		returnToPlayPanelButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		ladderChallengePanel.add(returnToPlayPanelButton);
		
		JLabel ladderTitle = new JLabel("Ladder Challenge!");
		ladderTitle.setBounds(139, 12, 357, 31);
		ladderTitle.setHorizontalAlignment(SwingConstants.CENTER);
		ladderTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 26));
		ladderChallengePanel.add(ladderTitle);
		
		JLabel ladderSubTitle = new JLabel("Choose you destiny!");
		ladderSubTitle.setBounds(139, 45, 357, 31);
		ladderSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		ladderSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		ladderChallengePanel.add(ladderSubTitle);
		
		JList laddersJList = new JList();
		laddersJList.setBorder(new LineBorder(new Color(0, 0, 0)));
		laddersJList.setBounds(165, 120, 300, 150);
		ladderChallengePanel.add(laddersJList);
		
		JButton startLadderButton = new JButton("Start Ladder!");
		startLadderButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		startLadderButton.setBounds(240, 300, 190, 50);
		ladderChallengePanel.add(startLadderButton);
		
		customGamePanel = new JPanel();
		customGamePanel.setBounds(12, 12, 663, 379);
		frmGotPuzzled.getContentPane().add(customGamePanel);
		customGamePanel.setBackground(new Color(224, 255, 255));
		customGamePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customGamePanel.setLayout(null);
		
		JButton returnToPlayPanelButton2 = new JButton("back");
		returnToPlayPanelButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGamePanel.setVisible(false);
				playPanel.setVisible(true);
			}
		});

		JLabel customGameTitle = new JLabel("Custom Game!");
		customGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		customGameTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 26));
		customGameTitle.setBounds(139, 12, 357, 31);
		customGamePanel.add(customGameTitle);
		
		database.getPuzzleDatabase().testDatabase(); // Just for Testing the database
		JList customGameJList = new JList(database.getPuzzleDatabase().getPuzzlesNames().toArray());	//List of puzzles.
		customGameJList.setBorder(new LineBorder(new Color(0, 0, 0)));
		customGameJList.setBounds(165, 120, 300, 150);
		customGamePanel.add(customGameJList);
		returnToPlayPanelButton2.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		returnToPlayPanelButton2.setBounds(27, 280, 80, 80);
		customGamePanel.add(returnToPlayPanelButton2);
		
		JButton startCustomGameButton = new JButton("Start Game!");
		startCustomGameButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		startCustomGameButton.setBounds(240, 300, 190, 50);
		customGamePanel.add(startCustomGameButton);
		customGamePanel.setVisible(false);
		
	
		
		
		
	}
}
