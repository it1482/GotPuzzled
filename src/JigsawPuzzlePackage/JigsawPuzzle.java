package JigsawPuzzlePackage;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;

// ### I think I need a quicker way to detect which piece is clicked on.
//   Mouse-down lags when there are lots of pieces.

/**
 * Jigsaw puzzle.
 */
public class JigsawPuzzle extends Puzzle
{
  // Class constants ------------------------------------------------------

  // ### Allow background to be changed.
  // ### Allow these to be configured.
  // Keyboard commands
  public static final char ROTATE_LEFT  = 'E';
  public static final char ROTATE_RIGHT = 'R';
  public static final char SHUFFLE      = 'S';
  public static final char PREV_BG      = 'V';
  public static final char NEXT_BG      = 'B';
  public static final char CLEAR        = 'C';

  // Available background colors
  public static final Color[] bgColors = {
    Color.BLACK,
    new Color ( 48,  0,  0),
    new Color (  0, 48,  0),
    new Color (  0,  0, 48),
    new Color ( 48, 48, 48),
    new Color ( 96,  0,  0),
    new Color (  0, 96,  0),
    new Color (  0,  0, 96),
    new Color ( 96, 96, 96),
    new Color (144,  0,  0),
    new Color (  0,144,  0),
    new Color (  0,  0,144),
    new Color (144,144,144),
    // Color.LIGHT_GRAY,
    // Color.RED,
    // Color.GREEN,
    // Color.BLUE,
    // Color.WHITE,
  };

  public static final Color CLEAR_COLOR_W = new Color (255,255,255,48);
  public static final Color CLEAR_COLOR_B = new Color (  0,  0,  0,48);

  public static final Cursor
    NORMAL_CURSOR = new Cursor (Cursor.DEFAULT_CURSOR),
    CLEAR_CURSOR  = new Cursor (Cursor.CROSSHAIR_CURSOR);

  // Constructor, fields, and supporting methods --------------------------

  /**
   * Creates a new JigsawPuzzle.
 * @param frmGotPuzzled 
 * @param jigsawFrame 
   * @param image the final picture
   * @param cut the cut to use on the image
   */
  public JigsawPuzzle (String name, BufferedImage loadedImage, JigsawCutter cutter, boolean rotation, JFrame frmGotPuzzled, JigsawFrame jigsawFrame)
  {
	super(name,loadedImage);
	this.frmGotPuzzled = frmGotPuzzled;
	this.jigsawFrame = jigsawFrame;
	this.rotation = rotation;
    this.image = imageScale(loadedImage);
    this.cutter = cutter;
    computePreferredSize();
    setOpaque (true);
    // System.out.println ("isFocusable? "+isFocusable());
    setFocusable(true);
    bgColor = 4;
    setBackground (bgColors[bgColor]);
    setCursor (NORMAL_CURSOR);
    setClearColor();
    addWiring();
  }
  private boolean rotation;
  private BufferedImage loadedImage;
  private Image image, finishedImage;
  private JigsawCutter cutter;
  // Piece[] pieces;
  private Dimension prefSize;
  private boolean mouseDown = false;
  private boolean finished  = false;
  private boolean clearMode = false;
  // Translation from a piece's upper-left corner to the point you clicked
  // on.
  private int transX, transY;
  // Last in list = topmost piece
  private LinkedList<Piece> zOrder;
  private int bgColor;
  private int clearX0, clearY0, clearX1, clearY1;
  private Color clearColor;
  private boolean puzzleFinished = false;
  private JFrame frmGotPuzzled,jigsawFrame;


  // If a keyboard command can affect a piece, it'll be this one.
  // Typically, this piece should be last in zOrder, but you never know.
  private Piece focusPiece;

  private void addWiring()
  {
    addMouseListener (new MouseAdapter() {
      public void mousePressed (MouseEvent e) { mousePressed0 (e); }
      public void mouseReleased(MouseEvent e) { mouseReleased0(e); }
    });
    addMouseMotionListener (new MouseMotionAdapter() {
      public void mouseDragged (MouseEvent e) { mouseDragged0 (e); }
    });
    addKeyListener (new KeyAdapter() {
      public void keyTyped (KeyEvent e) {
        // System.out.println ("type!");
        keyTyped0 (e); }
    });
  }

  // Accessors ------------------------------------------------------------

  /**
   * Tells this puzzle to cut the image into pieces and randomize their
   * positions.  This is a potentially time-consuming operation, and should
   * not be done in the AWT thread.
   */
  public void reset()
  {
    zOrder = null;
    Piece[] pieces = cutter.cut (image);
    shuffle (pieces);
  }

  /** Move current pieces around randomly, randomize z-order, but don't
   * randomize rotation.
   */
  public void shuffle()
  {
    Piece[] pieces = new Piece[zOrder.size()];
    zOrder.toArray (pieces);
    shuffle (pieces);
    repaint();
  }

  // Component methods ----------------------------------------------------

  public Dimension getMaximumSize() { return getPreferredSize(); }
  public Dimension getMinimumSize() { return getPreferredSize(); }
  public Dimension getPreferredSize() { return prefSize; }

  /** Returns whether the pieces have been prepared for this puzzle. */
  public boolean isCut()
  {
    return zOrder != null;
  }

  protected void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    int width = prefSize.width;
    int height = prefSize.height;

    if (zOrder == null) return;

    int x = 0, y = 0;
    Iterator iter = zOrder.iterator();
    while (iter.hasNext()) {
      Piece piece = (Piece) iter.next();
      piece.draw (g);
    }

    if (clearMode && mouseDown) {
      int cx = Math.min (clearX0, clearX1);
      int cy = Math.min (clearY0, clearY1);
      int cw = Math.abs (clearX0-clearX1);
      int ch = Math.abs (clearY0-clearY1);
      g.setColor (clearColor);
      g.fillRect (cx, cy, cw, ch);
    }

    if (finished && (finishedImage != null)) {
      Piece lastPiece = (Piece) zOrder.get(0);
      x = lastPiece.getPuzzleX();
      y = lastPiece.getPuzzleY();
      g.drawImage (finishedImage, x, y, null);
    }
  }

  public void setClearMode (boolean flag)
  {
    clearMode = flag;
    setCursor (clearMode ? CLEAR_CURSOR : NORMAL_CURSOR);
    // Just in case this is done from outside
    if (!clearMode && mouseDown) clear();
  }

  // Private methods ------------------------------------------------------

/**
 * If the image is too big. It scales it down. 
 * All images should be at 1200x1200 pixels max
 */
  
	private  Image imageScale(BufferedImage loadedImage){
		int preferredMaxHeigth = 768;
		int preferredMaxWidth = 1024;
		int currentHeigth = loadedImage.getHeight();
		int currentWidth =  loadedImage.getWidth();
		double scale_reduction = 1;
		
		//Scaling down until we find the best suited size
		while(currentHeigth > preferredMaxHeigth || currentWidth > preferredMaxWidth){
			scale_reduction -= 0.01;
			currentHeigth = (int) (scale_reduction *loadedImage.getHeight(null)) ;
			currentWidth = (int) (scale_reduction* loadedImage.getWidth(null));
			System.out.println(currentWidth + "x" +currentHeigth + " scale " + scale_reduction);
		}
		

	    BufferedImage newImage = null; //scaled image
	    if(loadedImage != null) {
	    	newImage = new BufferedImage(currentWidth, currentHeigth, BufferedImage.TYPE_3BYTE_BGR);
	        Graphics2D g = newImage.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(scale_reduction, scale_reduction);
	        g.drawRenderedImage(loadedImage, at);
	    }
	    return newImage;
	    
	}
	  /**
	   * Ideally, the preferred area is roughly 1.5 times the area of the
	   * image, and the preferred width is 5/3 of the preferred height.
	   * However, if the result would be smaller than the image in either
	   * dimension, it is enlarged to allow the image to fit.
	   */
  private void computePreferredSize()
  {
    JigUtil.ensureLoaded (image);
    int iWidth  = image.getWidth(null);
    int iHeight = image.getHeight(null);
    int area = iWidth * iHeight * 3 / 2;
    int width = (int) Math.sqrt (area * 5 / 3);
    int height = width * 3 / 5;
    width  = Math.max (width,  iWidth);
    height = Math.max (height, iHeight);
    prefSize = new Dimension (width, height);
  }

  // ### Should this be public?
  private void finish()
  {
    if (zOrder.size() != 1) return;
    finished = true;
    Piece lastPiece = (Piece) zOrder.get(0);

    // Auto-rotate the puzzle to its correct position.
    lastPiece.setRotation (0);

    // Center the last piece in the middle of the panel.
    int prevX = lastPiece.getPuzzleX();
    int prevY = lastPiece.getPuzzleY();
    final int width  = lastPiece.getImageWidth();
    final int height = lastPiece.getImageHeight();
    int curW = getWidth();
    int curH = getHeight();
    final int centerX = (curW - width ) / 2;
    final int centerY = (curH - height) / 2;
    lastPiece.moveTo (centerX, centerY);
    repaint(0,   prevX,   prevY, width, height);
    repaint(0, centerX, centerY, width, height);

    // Draw the original image on top of the last piece in increasing
    // opaqueness.  This should make the pieces appear to fade into the
    // original image.
    final int[] data = new int [width*height];
    try {
      new PixelGrabber (image, 0,0, width, height, data, 0, width)
        .grabPixels();
    } catch (InterruptedException ex)
    { System.out.println ("interrupted on finish grab"); }
    for (int i = 0; i < data.length; i++)
      data[i] = data[i] & 0x00ffffff;

    ActionListener fader = new ActionListener() {
      int trans = 0x00;

      public void actionPerformed (ActionEvent evt) {
        for (int i = 0; i < data.length; i++)
          data[i] = (data[i] & 0x00ffffff) | (trans << 24);
        if (finishedImage != null) finishedImage.flush();
        finishedImage = Toolkit.getDefaultToolkit().createImage (
          new MemoryImageSource (width, height, data, 0, width));
        repaint(0, centerX, centerY, width, height);
        if (trans < 0xff) {
          trans += 0x11; if (trans >= 0xff) trans = 0xff;
          // System.out.println ("fader at "+trans);
          Timer timer = new Timer (200, this);
          timer.setRepeats (false);
          timer.start();
        }
        else
        {
        	JOptionPane.showMessageDialog(null, "You Won", "Puzzle Got Solved!", JOptionPane.PLAIN_MESSAGE);
        	puzzleFinished = true;
        	frmGotPuzzled.setVisible(true);
        	jigsawFrame.dispose();
        	
      	
        }

      }
    };

    Timer timer = new Timer (200, fader);
    timer.setRepeats (false);
    timer.start();
  }


  // Mouse event handling -------------------------------------------------

  private void mousePressed0 (MouseEvent e)
  {
    if (finished) return;
    mouseDown = true;
    if (clearMode) startClearRect(e);
    else grabPiece(e);
  }

  private void mouseDragged0 (MouseEvent e)
  {
    if (finished) return;
    if (clearMode) dragClearRect(e);
    else dragPiece(e);
  }

  private void mouseReleased0 (MouseEvent e)
  {
    if (finished) return;
    mouseDown = false;
    if (clearMode) finishClearRect(e);
    else releasePiece (e);
  }

  private void grabPiece (MouseEvent e)
  {
    int x = e.getX();
    int y = e.getY();
    // System.out.println ("mouseDown at "+x+","+y);
    focusPiece = null;
    ListIterator iter = zOrder.listIterator(zOrder.size());
    while ((focusPiece == null) && (iter.hasPrevious())) {
      Piece piece = (Piece) iter.previous();
      if (piece.contains (x, y)) {
        focusPiece = piece;
        // System.out.println ("focus on "+focusPiece);
        iter.remove();
      }
    }
    if (focusPiece != null) {
      zOrder.add (focusPiece);
      transX = x - focusPiece.getPuzzleX();
      transY = y - focusPiece.getPuzzleY();
      // The focusPiece might have moved up in Z-order.  At worst, we have
      // to repaint its bounding rectangle.
      repaint(0, focusPiece.getPuzzleX(), focusPiece.getPuzzleY(),
        focusPiece.getCurrentWidth(), focusPiece.getCurrentHeight());
    }
    // System.out.println ("focusPiece now "+focusPiece);
  }

  private void dragPiece (MouseEvent e)
  {
    if (focusPiece == null) return;
    int prevX = focusPiece.getPuzzleX();
    int prevY = focusPiece.getPuzzleY();
    int prevW = focusPiece.getCurrentWidth();
    int prevH = focusPiece.getCurrentHeight();
    focusPiece.moveTo (e.getX()-transX, e.getY()-transY);
    // Repaint the focusPiece' previous and current bounding rects.
    repaint(0, prevX, prevY, prevW, prevH);
    repaint(0, focusPiece.getPuzzleX(), focusPiece.getPuzzleY(),
      focusPiece.getCurrentWidth(), focusPiece.getCurrentHeight());
  }

  private void releasePiece (MouseEvent e)
  {
    if (focusPiece == null) return;
    Piece[] result = focusPiece.join();
    if (result != null) {
      Piece newPiece = result[0];
      for (int i = 1; i < result.length; i++)
        zOrder.remove (result[i]);
      zOrder.add (newPiece);
      focusPiece = newPiece;
      // Joined pieces may be of any size and number.  Mouse release isn't
      // a terribly frequent event, so just repaint the whole thing.  If
      // it's really necessary later, the thing to do would be to repaint
      // the bounding rect for every piece in the result array above.
      repaint();
      if (zOrder.size() == 1) finish();
    }
  }

  private void startClearRect (MouseEvent e)
  {
    clearX0 = e.getX();
    clearY0 = e.getY();
  }

  private void dragClearRect (MouseEvent e)
  {
    int prevX1 = clearX1;
    int prevY1 = clearY1;
    clearX1 = e.getX();
    clearY1 = e.getY();
    int x = Math.min (clearX0, prevX1);
    int y = Math.min (clearY0, prevY1);
    int w = Math.abs (clearX0-prevX1);
    int h = Math.abs (clearY0-prevY1);
    repaint (0, x,y,w,h);
    x = Math.min (clearX0, clearX1);
    y = Math.min (clearY0, clearY1);
    w = Math.abs (clearX0-clearX1);
    h = Math.abs (clearY0-clearY1);
    repaint (0, x,y,w,h);
  }

  private void finishClearRect (MouseEvent e)
  {
    clearX1 = e.getX();
    clearY1 = e.getY();
    int cx0 = Math.max (0, Math.min (clearX0, clearX1));
    int cy0 = Math.max (0, Math.min (clearY0, clearY1));
    int cx1 = Math.min (getWidth(), Math.max (clearX0, clearX1));
    int cy1 = Math.min (getHeight(),Math.max (clearY0, clearY1));
    Iterator iter = zOrder.iterator();
    while (iter.hasNext()) {
      Piece piece = (Piece) iter.next();
      if (intersects (piece, cx0,cy0,cx1,cy1))
        shuffle (piece, cx0,cy0,cx1,cy1);
    }
    repaint();
  }

  // (x1,y1) guaranteed to be SE of (x0,y0)
  /** Return whether the piece intersects with the rectangle defined by the
   * given points.  ### Not perfect; returns true for some pieces that it
   * shouldn't.  Ideally, it should grab the part of the Piece in the
   * rectangle, and search it for non-transparent pixels.  Costly, so be
   * careful.
   */
  private boolean intersects (Piece piece, int x0, int y0, int x1, int y1)
  {
    int px = piece.getPuzzleX();
    int py = piece.getPuzzleY();
    int pw = piece.getCurrentWidth();
    int ph = piece.getCurrentHeight();
    int w = x1 - x0;
    int h = y1 - y0;
    Rectangle r  = new Rectangle (x0,y0,w,h);
    Rectangle rp = new Rectangle (px,py,pw,ph);
    // System.out.print ("int? "+r+" "+rp);
    // System.out.println ("   "+r.intersects(rp));
    return r.intersects(rp);
  }

  static final Rectangle emptyRect = new Rectangle (0,0,0,0);

  // (x1,y1) guaranteed to be SE of (x0,y0)
  /** Shuffle piece randomly, but keeping it out of the rectangle defined
   * by the given points. */
  private void shuffle (Piece piece, int x0, int y0, int x1, int y1)
  {
    // Make the rectangle denoting where the Piece could be placed in the
    // whole panel.  Top point will be (0,0).
    int w = getWidth()  - piece.getCurrentWidth();
    int h = getHeight() - piece.getCurrentHeight();
    // If w or h is negative, the piece is too big to be shuffled, so quit.
    if (w<0 || h<0) return;

    // Define the endpoints of the rectangle the Piece must avoid.
    int ax = Math.max (0, x0 - piece.getCurrentWidth());
    int ay = Math.max (0, y0 - piece.getCurrentHeight());
    int aw = x1 - ax;
    int ah = y1 - ay;

    // Now define four rectangles forming the shape where the NW piece
    // corner could go.  I'll use BorderLayout rectangles as a guide.
    Rectangle north = (ay==0) ? emptyRect :
      new Rectangle (0,0, w, ay);
    Rectangle south = (y1>=h) ? emptyRect :
      new Rectangle (0,y1+1, w, h-y1);
    Rectangle west  = (ax==0 || ah==0) ? emptyRect :
      new Rectangle (0,ay, ax,ah);
    Rectangle east  = (x1>=w || ah==0) ? emptyRect :
      new Rectangle (x1,ay, w-x1,ah);

    // System.out.println ("w="+getWidth()+" h="+getHeight());
    // int pw = piece.getCurrentWidth();
    // int ph = piece.getCurrentHeight();
    // System.out.println ("shuff p:("+pw+"x"+ph+")"+
      // "r=("+x0+","+y0+")-("+x1+","+y1+")");
    // System.out.println ("  n="+north);
    // System.out.println ("  s="+south);
    // System.out.println ("  w="+ west);
    // System.out.println ("  e="+ east);
    int
      nArea = north.width * north.height,
      sArea = south.width * south.height,
      wArea = west .width * west .height,
      eArea = east .width * east .height,
      totalArea = nArea + sArea + wArea + eArea;

    int rand = (int) (Math.random() * totalArea);
    // System.out.println ("total="+totalArea+" rand="+rand);
    rand -= nArea; if (rand<0) { shuffle (piece,north); return; }
    rand -= sArea; if (rand<0) { shuffle (piece,south); return; }
    rand -= wArea; if (rand<0) { shuffle (piece,west ); return; }
    shuffle (piece,east );
  }

  private void shuffle (Piece piece, Rectangle rect)
  {
    int dx = (int) (Math.random() * rect.width);
    int dy = (int) (Math.random() * rect.height);
    // System.out.println ("shuf in "+rect+" dx="+dx+" dy="+dy);
    piece.moveTo (rect.x+dx, rect.y+dy);
  }

  // Keyboard event handling ----------------------------------------------

  private void keyTyped0 (KeyEvent e)
  {
    if (finished) return;
    char ch = Character.toUpperCase (e.getKeyChar());
    
    if (ch==ROTATE_LEFT)  		{if(rotation) rotatePiece (270);}
    else if (ch==ROTATE_RIGHT)  {if (rotation) rotatePiece (90);}
    else if (ch==SHUFFLE)      shuffle();
    else if (ch==PREV_BG)      prevBackground();
    else if (ch==NEXT_BG)      nextBackground();
    else if (ch==CLEAR)        toggleClearMode();
    else return;
  }

  private void rotatePiece (int amount)
  {
    if (focusPiece == null) return;
    int newRotation = focusPiece.getRotation()+amount;
    newRotation %= 360;
    int prevW = focusPiece.getCurrentWidth();
    int prevH = focusPiece.getCurrentHeight();
    int prevX = focusPiece.getPuzzleX();
    int prevY = focusPiece.getPuzzleY();
    focusPiece.setRotation (newRotation);
    // Make the piece appear to rotate about its center.
    // ### Feature: When the mouse is down, rotate about the cursor instead
    //   of the center.
    int centerX = prevX + prevW/2;
    int centerY = prevY + prevH/2;
    int currW = focusPiece.getCurrentWidth();
    int currH = focusPiece.getCurrentHeight();
    int currX = centerX - currW/2;
    int currY = centerY - currH/2;
    focusPiece.moveTo (currX, currY);
    repaint(0, prevX, prevY, prevW, prevH);
    repaint(0, currX, currY, currW, currH);
  }

  private void shuffle(Piece[] pieces)
  {
    zOrder = null;
    int height = getHeight();
    int width  = getWidth();

    // Copy pieces in random order into zOrder, and randomize their
    // positions.
    LinkedList<Piece> zOrder0 = new LinkedList<Piece>();
    int lastIdx = pieces.length-1;
    while (lastIdx > 0) {
      int pIdx = (int) (Math.random() * (lastIdx+1));
      Piece piece = pieces[pIdx];
      piece.setPuzzlePosition (
        (int) (Math.random() * (width  - piece.getCurrentWidth ())) ,
        (int) (Math.random() * (height - piece.getCurrentHeight())) );
      zOrder0.add (piece);
      if (pIdx != lastIdx) {
        Piece temp = pieces[lastIdx];
        pieces[lastIdx] = pieces[pIdx];
        pieces[pIdx] = temp;
      }
      lastIdx--;
    }
    zOrder0.add (pieces[0]);

    finished = false;
    if (finishedImage != null) {
      finishedImage.flush();
      finishedImage = null;
    }

    zOrder = zOrder0;
  }

  private void prevBackground()
  {
    bgColor--;
    if (bgColor < 0) bgColor = bgColors.length-1;
    setBackground (bgColors[bgColor]);
    setClearColor();
    repaint();
  }

  private void nextBackground()
  {
    bgColor++;
    if (bgColor >= bgColors.length) bgColor = 0;
    setBackground (bgColors[bgColor]);
    setClearColor();
    repaint();
  }

  private void toggleClearMode()
  {
    if (mouseDown) return; // can't toggle clear mode while dragging
    setClearMode (!clearMode);
  }

  private void clear()
  {
    System.out.println ("clear()");
    // ### stub
  }

  private void setClearColor()
  { clearColor = getClearColor(); }

  private Color getClearColor()
  { return isBright (bgColors[bgColor]) ? CLEAR_COLOR_B : CLEAR_COLOR_W; }

  private boolean isBright (Color c)
  {
    float[] hsb = new float[3];
    Color.RGBtoHSB (c.getRed(),c.getGreen(),c.getBlue(), hsb);
    return hsb[2] > 0.5;
  }



public boolean isFinished() {
	return finished;
}

public boolean isRotation() {
	// TODO Auto-generated method stub
	return rotation;
}

  
}

