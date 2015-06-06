package guiPackage;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;


import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import puzzlePackage.PuzzleData;
import puzzlePackage.PuzzleJigsawData;
import JigsawPuzzlePackage.JigsawCutter;
import JigsawPuzzlePackage.JigsawFrame;
import MainPackage.Database;
import SlidingPuzzlePackage.SlidingPuzzle;


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
	
	private JPanel editorPanel;
	
	private JPanel editorCreatePanel;
	
	private JPanel createPuzzlePanel; 
	
	
	
	private JPanel optionsPanel;
	
	private JPanel leaderboardsPanel;
	
	


	
	private JTextField createPuzzleNewPuzzleNameTextField;
	
	private JCheckBox createPuzzleRotationCheckBox;
	private JTextField createPuzzlePiecesTextField;
	
	
	private JLabel optionsTitleLabel;
	private JButton optionsBackToMainMenuButton;
	private JLabel leaderboardsTitleLabel;
	
	//fields needed

	Database database = new Database();
	private Image image;
	DefaultListModel<String> model;
	

	
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
		
		// a frame it's been constructed
		frmGotPuzzled = new JFrame();
		frmGotPuzzled.getContentPane().setBackground(new Color(240, 255, 255));
		frmGotPuzzled.setTitle("Got Puzzled 2.0");
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
		
		JButton playLoadGame = new JButton("Load Game");
		playLoadGame.setForeground(Color.WHITE);
		playLoadGame.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		playLoadGame.setBackground(new Color(34, 139, 34));
		playLoadGame.setBounds(181, 297, 243, 72);
		playPanel.add(playLoadGame);
		
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
		
		
		JList<String> laddersJList = new JList();
		laddersJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		// this adds to the JList the ability to ScrollDown
		laddersListScrollPane.setViewportView(laddersJList);
		
		// thn apo katw entolh thn egrapsa gia na testarw oti to scrollpane douleuei kanonika
		//laddersJList.setListData(new String[1000]);
		
		
		// here you can add code to implement the Start Ladder function
		JButton ladderStartLadderButton = new JButton("Start Ladder!");
		ladderStartLadderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
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
		customBackToPlayPanelButton.setBounds(20, 470, 100, 50);
		customGamePanel.add(customBackToPlayPanelButton);
		
		
		// auto to panel vrisketai mesa sto customGamePanel kai tha xrhsimooieithei gia to preview tou puzzle pou thelei na paiksei o paiktis
		JPanel customGamePreviewPanel = new JPanel();
		customGamePreviewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customGamePreviewPanel.setBounds(177, 230, 250, 200);
		customGamePanel.add(customGamePreviewPanel);
		
		JScrollPane customPuzzlesListScrollPane = new JScrollPane( );
		customPuzzlesListScrollPane.setBounds(150, 70, 300, 150);
		customGamePanel.add(customPuzzlesListScrollPane);
		
		final DefaultListModel model = new DefaultListModel();
		//Initiate list with the current puzzles taken from database
		database.getPuzzleDatabase().testDatabase(); 

		final JList customPuzzlesJList = new JList(model);
		UpdateJList(customPuzzlesJList);

		customPuzzlesJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		customPuzzlesListScrollPane.setViewportView(customPuzzlesJList);
		
		// here you can add code to implement the Start Game function
		JButton customGameStartGameButton = new JButton("Start Game!");
		customGameStartGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = database.getPuzzleDatabase().getPuzzlesNames().indexOf(customPuzzlesJList.getSelectedValue());
				System.out.println(index);
				if(database.getPuzzleDatabase().getPuzzles().get(index) instanceof PuzzleJigsawData)
				{
					//Initiates Jigsaw puzzle
					PuzzleJigsawData current = (PuzzleJigsawData) database.getPuzzleDatabase().getPuzzles().get(index);
					JigsawCutter varCutter = new JigsawCutter(current.getRotation());
					JigsawFrame jigframe = new JigsawFrame ((BufferedImage) current.getImage(), varCutter, ((PuzzleJigsawData)current).getRotation());
					jigframe.begin();
					jigframe.setSize (1024, 740);
				    jigframe.setVisible(true);
				    frmGotPuzzled.setVisible(false);

				}
				else
				{
					//Initiates Sliding puzzle
					frmGotPuzzled.setContentPane(new SlidingPuzzle(database.getPuzzleDatabase().getPuzzles().get(index).getName(),database.getPuzzleDatabase().getPuzzles().get(index).getImage(),database.getPuzzleDatabase().getPuzzles().get(index).getDifficulty()));
					frmGotPuzzled.setVisible(true);
					frmGotPuzzled.setResizable(true);
					frmGotPuzzled.setSize(800,800);
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
		editorExportButton.setForeground(Color.WHITE);
		editorExportButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorExportButton.setBackground(new Color(34, 139, 34));
		editorExportButton.setBounds(181, 220, 243, 72);
		editorPanel.add(editorExportButton);
		
		JButton editorImportButton = new JButton("Import");
		editorImportButton.setForeground(Color.WHITE);
		editorImportButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		editorImportButton.setBackground(new Color(34, 139, 34));
		editorImportButton.setBounds(181, 340, 243, 72);
		editorPanel.add(editorImportButton);
		editorBackToMainMenuButton.setBounds(20, 470, 100, 50);
		editorPanel.add(editorBackToMainMenuButton);
		editorBackToMainMenuButton.setForeground(Color.WHITE);
		editorBackToMainMenuButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		editorBackToMainMenuButton.setBackground(new Color(34, 139, 34));
		
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
		createBackToEditorButton.setForeground(Color.WHITE);
		createBackToEditorButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		createBackToEditorButton.setBackground(new Color(34, 139, 34));
		createBackToEditorButton.setBounds(20, 470, 100, 50);
		editorCreatePanel.add(createBackToEditorButton);
		
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
		createPuzzleJigsawRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (createPuzzleJigsawRadioButton.isSelected()){
					createPuzzleRotationCheckBox.setEnabled(true);
					
				}
			}
		});
		createPuzzleJigsawRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleJigsawRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleJigsawRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleJigsawRadioButton.setBounds(50, 220, 121, 24);
		createPuzzlePanel.add(createPuzzleJigsawRadioButton);
		
		final JRadioButton createPuzzleSlidingRadioButton = new JRadioButton("Sliding Puzzle",true);
		createPuzzleSlidingRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (createPuzzleSlidingRadioButton.isSelected()) {
					createPuzzleRotationCheckBox.setSelected(false);
					createPuzzleRotationCheckBox.setEnabled(false);
				}
			}
		});
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
		createPuzzleEasyRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleEasyRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleEasyRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleEasyRadioButton.setBounds(300, 220, 121, 24);
		createPuzzlePanel.add(createPuzzleEasyRadioButton);
		
		final JRadioButton createPuzzleMediumRadioButton = new JRadioButton("Medium");
		createPuzzleMediumRadioButton.setForeground(new Color(34, 139, 34));
		createPuzzleMediumRadioButton.setBackground(new Color(245, 245, 220));
		createPuzzleMediumRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createPuzzleMediumRadioButton.setBounds(300, 250, 121, 24);
		createPuzzlePanel.add(createPuzzleMediumRadioButton);
		
		JRadioButton createPuzzleHardRadioButton = new JRadioButton("Hard");
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
		
		//Gets all the parsed information from the user and creates the puzzle
		JButton createPuzzleButton = new JButton("Create");
		createPuzzleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				int difficulty;
				boolean rotation;
				String name;
				
				
				rotation = createPuzzleRotationCheckBox.isSelected(); 
				if(createPuzzleEasyRadioButton.isSelected()){
					difficulty = 1;

				}
				else if(createPuzzleMediumRadioButton.isSelected())
					difficulty = 2;
				else
					difficulty = 3;
				name = createPuzzleNewPuzzleNameTextField.getText();
				//System.out.println(name);
				//System.out.println(difficulty);
				//System.out.println(rotation);
				//System.out.println(image);
				//System.out.println(createPuzzleJigsawRadioButton.isSelected());
				
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
					UpdateJList(customPuzzlesJList);
					database.getPuzzleDatabase().getLoadsave().save(database.getPuzzleDatabase().getPuzzles());
					System.out.println(name + " puzzle got saved!");
					
						
				}
				else 
					System.out.println("Please check your input");

				
			}
		});
		createPuzzleButton.setForeground(Color.WHITE);
		createPuzzleButton.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		createPuzzleButton.setBackground(new Color(34, 139, 34));
		createPuzzleButton.setBounds(181, 450, 243, 72);
		createPuzzlePanel.add(createPuzzleButton);
		createPuzzleBackToCreateButton.setForeground(Color.WHITE);
		createPuzzleBackToCreateButton.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		createPuzzleBackToCreateButton.setBackground(new Color(34, 139, 34));
		createPuzzleBackToCreateButton.setBounds(20, 470, 100, 50);
		createPuzzlePanel.add(createPuzzleBackToCreateButton);
		
		
		
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
		
		JScrollPane leaderboardsScrollPane = new JScrollPane();
		leaderboardsScrollPane.setBounds(150, 70, 324, 370);
		leaderboardsPanel.add(leaderboardsScrollPane);
		
		JList leaderboardsJList = new JList();
		leaderboardsJList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		leaderboardsScrollPane.setViewportView(leaderboardsJList);
		
		

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
	
	public static Image fileChooser(Component comp){
		Image image=null;
		MediaTracker tracker = new MediaTracker(comp);
		tracker.addImage(image,1);
		
		JFileChooser fc = new JFileChooser(System.getProperty("user.home")+"\\Desktop\\");
		FileFilter filter = new FileNameExtensionFilter("PNG/JPG/BMP","png","jpg","bmp");
		fc.setFileFilter(filter);
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try{
				tracker.waitForAll();
				image = ImageIO.read(file);
			} catch (Exception exp){
				exp.printStackTrace();
				
			}
			
		}else{
			System.out.println("Bye");
		}
		return image;
	}

	private void UpdateJList(JList list){
	    model = new DefaultListModel<String>();
	    for(String p : database.getPuzzleDatabase().getPuzzlesNames()){
	         model.addElement(p);
	    }    
	    list.setModel(model);     
	    list.setSelectedIndex(0);
	}

}
