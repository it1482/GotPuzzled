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
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class MainGUI {
	
	/*
	 * here we post all frames and panels to be visible and enable for whole code
	 */
	// kentriko vasiko frame - panw sto opoio kathontai ola ta panels
	private JFrame frmGotPuzzled;

	
	// kentriko panel
	private JPanel mainMenu;
	
	
	
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
		frmGotPuzzled.setBounds(100, 100, 703, 488);
		frmGotPuzzled.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmGotPuzzled.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setIcon(new ImageIcon(MainGUI.class.getResource("/guiPackage/img/exit.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutGotPuzzled = new JMenuItem("About Got Puzzled");
		mntmAboutGotPuzzled.setIcon(new ImageIcon(MainGUI.class.getResource("/guiPackage/img/info.png")));
		mnAbout.add(mntmAboutGotPuzzled);
		frmGotPuzzled.getContentPane().setLayout(null);
		
		mainMenu = new JPanel();
		mainMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenu.setBackground(new Color(224, 255, 255));
		mainMenu.setBounds(160, 12, 357, 402);
		frmGotPuzzled.getContentPane().add(mainMenu);
		mainMenu.setLayout(null);
		
		JLabel title = new JLabel("Got Puzzled!");
		title.setBounds(0, 0, 357, 31);
		title.setFont(new Font("Segoe UI Black", Font.PLAIN, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenu.add(title);
		
		JButton playButton = new JButton("Play!");
		playButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		playButton.setBounds(86, 59, 177, 64);
		mainMenu.add(playButton);
		
		JButton editorButton = new JButton("Editor");
		editorButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		editorButton.setBounds(86, 135, 177, 64);
		mainMenu.add(editorButton);
		
		JButton optionsButton = new JButton("Options");
		optionsButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		optionsButton.setBounds(86, 211, 177, 64);
		mainMenu.add(optionsButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		exitButton.setBounds(86, 287, 177, 64);
		mainMenu.add(exitButton);
		
		
	}
}
