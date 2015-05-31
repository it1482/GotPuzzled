package MainPackage;


import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

import JigsawPuzzlePackage.JigsawCutter;
import JigsawPuzzlePackage.JigsawFrame;
import JigsawPuzzlePackage.OKCancelPane;

public class RunJigsaw extends JFrame {

	private JFrame frame;
	private JTextField imageField;
	JSpinner pieceSpinner = new JSpinner();
	JLabel       cutterDescLabel;
	OKCancelPane okCancel;
	JCheckBox chckbxRotation = new JCheckBox("Rotation");

	private Image image;
	//static final JigsawCutter cutter =   new JigsawCutter();
	String imagePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunJigsaw window = new RunJigsaw();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RunJigsaw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblImage);
		
		imageField = new JTextField();
		imageField.setBounds(20, 36, 257, 34);
		frame.getContentPane().add(imageField);
		imageField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 fireBrowseAction(); 
			}
		});
		btnNewButton.setBounds(302, 35, 89, 35);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblPieceCount = new JLabel("Piece Count");
		lblPieceCount.setBounds(272, 81, 86, 30);
		frame.getContentPane().add(lblPieceCount);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireOKAction();
			}
		});
		btnOk.setBounds(112, 208, 89, 23);
		frame.getContentPane().add(btnOk);
		pieceSpinner.setModel(new SpinnerNumberModel(new Integer(25), null, null, new Integer(1)));
		
	
		pieceSpinner.setBounds(276, 111, 115, 20);
		frame.getContentPane().add(pieceSpinner);
		


		chckbxRotation.setBounds(272, 154, 119, 30);
		frame.getContentPane().add(chckbxRotation);
	}
	 protected void fireOKAction()
	  {
	    try { setUpPuzzle(); }
	    catch (FrontEndException ex)
	    { JOptionPane.showMessageDialog (this, ex.getMessage()); }
	    catch (Exception ex)
	    { 
	      StackTraceElement[] trace = ex.getStackTrace();
	      StringBuffer buf = new StringBuffer();
	      for (int i = 0; i < trace.length; i++)
	        buf.append (trace[i]).append("\n");
	      JOptionPane.showMessageDialog (this, ex.getMessage()+"\n"+buf); }
	  }
	 
	  private void setUpPuzzle()
			    throws FrontEndException, IOException
			  {
			    image = ImageIO.read(new File(imagePath));
			    // Get the cutter and set its piece count if needed.
			    JigsawCutter varCutter = new JigsawCutter(chckbxRotation.isSelected());
			      int count = ((Number) pieceSpinner.getValue()).intValue();
			      System.out.println ("count = "+count);
			      varCutter.setPreferredPieceCount (count);
			    

			    // Set up and show the frame.
			    JigsawFrame frame = new JigsawFrame ((BufferedImage) image, varCutter, chckbxRotation.isSelected());
			    frame.begin();
			    frame.setSize (1024, 740);
			    frame.show();
			  }
	 
	  protected void fireBrowseAction()
	  {
	    JFileChooser chooser = new JFileChooser(getCurrentFolder());
	    chooser.setFileSelectionMode (JFileChooser.FILES_AND_DIRECTORIES);
	    int choose = chooser.showOpenDialog(this);
	    if (choose == JFileChooser.APPROVE_OPTION)
	      imageField.setText (chooser.getSelectedFile().getAbsolutePath());
	    imagePath = imageField.getText();
	  }

	  private File getCurrentFolder()
	  {
	    String text = imageField.getText().trim();
	    if (text.length() == 0) text = ".";
	    return new File (text);
	  }
	  
	  class FrontEndException extends Exception
	  {
	    public FrontEndException (String text)
	    { super (text); }
	  }
}
