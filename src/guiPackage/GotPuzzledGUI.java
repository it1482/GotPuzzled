package guiPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;


public class GotPuzzledGUI {
	
	
	
	
	
	/*
	 * here we post all frames and panels to be visible and enable for whole code
	 */
	// kentriko vasiko frame - panw sto opoio kathontai ola ta panels
	private JFrame frmGotPuzzled;

	
	// kentriko panel
	private JPanel mainMenuPanel;
	
	private JPanel playPanel;
	
	private JPanel ladderPanel;
	
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
		
		// a frame it's been constructed
		frmGotPuzzled = new JFrame();
		frmGotPuzzled.getContentPane().setBackground(new Color(240, 255, 255));
		frmGotPuzzled.setTitle("Got Puzzled 1.0");
		frmGotPuzzled.setBounds(100, 100, 637, 609);
		frmGotPuzzled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmGotPuzzled.getContentPane().setLayout(new BorderLayout());
		frmGotPuzzled.setResizable(false);
		
		
		// a JMenuBar it's been constructed
		JMenuBar menuBar = new JMenuBar();
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
				
			}
		});
		mntmAboutGotPuzzled.setIcon(new ImageIcon(GotPuzzledGUI.class.getResource("/guiPackage/img/info.png")));
		mnAbout.add(mntmAboutGotPuzzled);
		frmGotPuzzled.getContentPane().setLayout(null);
		
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBounds(0, 0, 631, 557);
		mainMenuPanel.setBackground(new Color(245, 245, 220));
		frmGotPuzzled.getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		
		JLabel mainMenuTitleLabel = new JLabel("Got Puzzled!");
		mainMenuTitleLabel.setForeground(new Color(34, 139, 34));
		mainMenuTitleLabel.setBounds(0, 0, 621, 54);
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
		mainEditorButton.setForeground(Color.WHITE);
		mainEditorButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		mainEditorButton.setBackground(new Color(34, 139, 34));
		mainEditorButton.setBounds(181, 189, 243, 72);
		mainMenuPanel.add(mainEditorButton);
		
		JButton mainOptionsButton = new JButton("Options");
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
		
		JButton playLoadGame = new JButton("Load Game");
		playLoadGame.setForeground(Color.WHITE);
		playLoadGame.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		playLoadGame.setBackground(new Color(34, 139, 34));
		playLoadGame.setBounds(181, 297, 243, 72);
		playPanel.add(playLoadGame);
		
		JButton playLeaderboardsButton = new JButton("Leaderboards");
		playLeaderboardsButton.setForeground(Color.WHITE);
		playLeaderboardsButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		playLeaderboardsButton.setBackground(new Color(34, 139, 34));
		playLeaderboardsButton.setBounds(181, 407, 243, 72);
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
		playBackToMainMenuButton.setBounds(20, 426, 100, 50);
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
		
		JList laddersJList = new JList();
		laddersJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		// this adds to the JList the ability to ScrollDown
		laddersListScrollPane.setViewportView(laddersJList);
		
		// thn apo katw entolh thn egrapsa gia na testarw oti to scrollpane douleuei kanonika
		//laddersJList.setListData(new String[1000]);
		
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
		customBackToPlayPanelButton.setBounds(20, 450, 100, 50);
		customGamePanel.add(customBackToPlayPanelButton);
		
		
		// auto to panel vrisketai mesa sto customGamePanel kai tha xrhsimooieithei gia to preview tou puzzle pou thelei na paiksei o paiktis
		JPanel customGamePreviewPanel = new JPanel();
		customGamePreviewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customGamePreviewPanel.setBounds(177, 230, 250, 200);
		customGamePanel.add(customGamePreviewPanel);
		
		JScrollPane customPuzzlesListScrollPane = new JScrollPane();
		customPuzzlesListScrollPane.setBounds(150, 70, 300, 150);
		customGamePanel.add(customPuzzlesListScrollPane);
		
		JList customPuzzlesJList = new JList();
		customPuzzlesJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		customPuzzlesListScrollPane.setViewportView(customPuzzlesJList);
		
		JButton customGameStartGameButton = new JButton("Start Game!");
		customGameStartGameButton.setForeground(Color.WHITE);
		customGameStartGameButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		customGameStartGameButton.setBackground(new Color(34, 139, 34));
		customGameStartGameButton.setBounds(181, 450, 243, 72);
		customGamePanel.add(customGameStartGameButton);
		
		
	}
}
