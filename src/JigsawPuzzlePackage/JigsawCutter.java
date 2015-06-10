package JigsawPuzzlePackage;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.PixelGrabber;

/**
 * Cuts pieces into the "classic" look.  Every non-border piece has four
 * sides; two opposite sides have knobs, and the other two have holes.
 * Pieces are all roughly square, not counting the knobs and holes.
 */
public class JigsawCutter
{


  public static final int defPrefPieces = 150;
  public static final double widthToHeightRatio = 1.0;

  /** Creates a JigsawCutter that will try to produce a default number of
   * pieces.
   */
  public JigsawCutter (boolean flag_rotation)
  { this (defPrefPieces, flag_rotation); }

  /** Creates a JigsawCutter.
   * @param prefPieces the preferred number of pieces; the cutter will try
   *   to produce close to this many
   *  @param flag_rotation tell the cutter if rotation will be supported
   * @throws IllegalArgumentException if prefPieces is less than 2
   */
  public JigsawCutter (int prefPieces, boolean flag_rotation)
  {
	this.flag_rotation = flag_rotation;
    setPreferredPieceCount (prefPieces);
  }

  public void setPreferredPieceCount(int difficulty)
  {
    if 		(difficulty == 1) 
    	this.prefPieces = 20;
    else if (difficulty == 2)
    	this.prefPieces = 50;
    else
    	this.prefPieces = 100;
  }

  private int prefPieces;
  private boolean flag_rotation;

  public Piece[] cut (Image image)
  {
    JigUtil.ensureLoaded (image);
    int width = image.getWidth(null);
    int height = image.getHeight(null);

    /*
    First compute the number of rows and columns.  If N = total number
    of pieces, H = height, W = width, and K =
    width/height ratio, then
      rows * columns = N
      (width/columns) / (height/rows) = K
    and therefore
      columns = N/rows
      (height*RR/N*height) = K
      rows = sqrt (NHK/width)
    */
    int rows = (int) Math.round (Math.sqrt (widthToHeightRatio * prefPieces * height / width));
    int columns = (int) Math.round (prefPieces / rows);
    // System.out.println ("rows="+rows+" columns="+columns);

    // Make a matrix of points representing the corners of the pieces.
    // Each point is based on a grid of equal rectangles, and can then
    // drift by up to 1/20th the height or width of an average piece.
    // Points on the north and south edges are fixed vertically, of
    // course, and east/west edge points are fixed horizontally.
    int hVary = height / (rows * 20);
    int wVary = width / (columns * 20);
    // System.out.println ("vary w="+wVary+" h="+hVary);
    Point[][] points = new Point[columns+1][rows+1];
    // i varies horizontally; j varies vertically
    for (int j = 0; j <= rows; j++) {
      int baseY = j*height / rows;
      for (int i = 0; i <= columns; i++) {
        int baseX = i*width / columns;
        int x = baseX;
        int y = baseY;
        if ((i>0) && (i < columns))
          x += Math.random()*(2*wVary+1) - wVary;
        if ((j>0) && (j < rows))
          y += Math.random()*(2*hVary+1) - hVary;
        points[i][j] = new Point (x,y);
        // System.out.print ("("+x+","+y+") ");
      }
      // System.out.println ();
    }

    // Make a knob for each edge.  Two matrices, one for vertical edges,
    // one for horizontal.  Remember to alternate knob directions.
    boolean flip1 = true;
    Knob[][] vKnobs = new Knob[columns-1][rows];
    for (int j = 0; j < rows; j++) {
      boolean flip = flip1;
      for (int i = 0; i < columns-1; i++) {
        Point p1 = points[i+1][j];
        Point p2 = points[i+1][j+1];
        if (flip) { Point temp = p1; p1=p2; p2=temp; }
        vKnobs[i][j] = new Knob (p1.x, p1.y, p2.x, p2.y);
        flip = !flip;
        // System.out.println ("vKnob("+i+","+j+")="+vKnobs[i][j]);
      }
      flip1 = !flip1;
    }

    flip1 = true;
    Knob[][] hKnobs = new Knob[columns][rows-1];
    for (int j = 0; j < rows-1; j++) {
      boolean flip = flip1;
      for (int i = 0; i < columns; i++) {
        Point p1 = points[i][j+1];
        Point p2 = points[i+1][j+1];
        if (flip) { Point temp = p1; p1=p2; p2=temp; }
        hKnobs[i][j] = new Knob (p1.x, p1.y, p2.x, p2.y);
        flip = !flip;
        // System.out.println ("hKnob("+i+","+j+")="+hKnobs[i][j]);
      }
      flip1 = !flip1;
    }

    // Create the pieces.
    Piece[][] pieces = new Piece[columns][rows];
    for (int j = 0; j < rows; j++)
      for (int i = 0; i < columns; i++) {
     // System.out.println ("makePiece i="+i+" j="+j);
        Knob knobN = (j>        0) ? hKnobs[i][j-1] : null;
        Knob knobS = (j<   rows-1) ? hKnobs[i][j]   : null;
        Knob knobW = (i>        0) ? vKnobs[i-1][j] : null;
        Knob knobE = (i<columns-1) ? vKnobs[i][j]   : null;
        pieces[i][j] = makePiece (image,
          points[i][j],
          points[i][j+1],
          points[i+1][j],
          points[i+1][j+1],
          knobN, knobE, knobS, knobW,
          width, height,
          // (i==1)&&(j==1));
          false);
      }

    // Set each piece's neighbors, and build the final array.
    Piece[] ret = new Piece[rows*columns];
    for (int j = 0; j < rows; j++)
      for (int i = 0; i < columns; i++) {
        if (i>0)
          pieces[i][j].addNeighbor (pieces[i-1][j]);
        if (j>0)
          pieces[i][j].addNeighbor (pieces[i][j-1]);
        if (i<columns-1)
          pieces[i][j].addNeighbor (pieces[i+1][j]);
        if (j<rows-1)
          pieces[i][j].addNeighbor (pieces[i][j+1]);
        ret[j*columns+i] = pieces[i][j];
      }

    return ret;
  }

  private Piece makePiece (Image image,
    Point nw, Point sw, Point ne, Point se,
    Knob knobN, Knob knobE, Knob knobS, Knob knobW,
    int tWidth, int tHeight,
    boolean debug)
  {
    // Build a path out of the knobs/puzzle edges.
    GeneralPath path = new GeneralPath();
    path.moveTo (nw.x, nw.y);
    if (knobN != null) path.append (knobN.getCurvePath(nw.x, nw.y), true);
    else path.lineTo (ne.x, ne.y);
    if (knobE != null) path.append (knobE.getCurvePath(ne.x, ne.y), true);
    else path.lineTo (se.x, se.y);
    if (knobS != null) path.append (knobS.getCurvePath(se.x, se.y), true);
    else path.lineTo (sw.x, sw.y);
    if (knobW != null) path.append (knobW.getCurvePath(sw.x, sw.y), true);
    else path.lineTo (nw.x, nw.y);

    /*
    System.out.println ();
    System.out.println ("knobs:");
    System.out.println ("                         N="+knobN);
    System.out.print (" W="+knobW);
    if (knobW==null) System.out.print ("                       ");
    System.out.println (" E="+knobE);
    System.out.println ("                         S="+knobS);
    */

    // Roundoff will sometimes cause the path bounds to be
    // outside of the image bounds, even though that edge is a straight
    // line.  This would cause the edge pieces to appear not to line up
    // while they're being put together.  When the puzzle is finished, the
    // dissolve trick would cause the image to appear blurry due to its
    // finished version being one pixel off from the other.  I'm fixing the
    // roundoff problem for the top and left edge pieces, and hoping the
    // other pieces don't need any help.
    Rectangle box = path.getBounds();
    if (box.x < 0) box.x = 0;
    if (box.y < 0) box.y = 0;
    // if (Math.abs (box.x) < 5 || Math.abs (box.y) < 5)
      // System.out.println ("final bounds:"+box);

    int width  = box.width;
    int height = box.height;

    int[] data = new int[width*height];
    PixelGrabber grabber = 
      new PixelGrabber (image, box.x, box.y,
        width, height, data, 0, width);
    try { grabber.grabPixels(); }
    catch (InterruptedException ex) {
      System.out.println ("interrupted while grabbing");
    }

    int minX = box.x;
    int minY = box.y;
    if (debug) { System.out.println ("data:"); printData (data, width,
      height); }
    mask (data, path, minX, minY, width, height);
    int rotation = 0;
    if(flag_rotation){
       rotation = ((int)(Math.random()*4)) * 90;
    }


    return new Piece (data, minX, minY, width, height,
        tWidth, tHeight, rotation);
  }

  private void mask (int[] data, GeneralPath path,
    int minX, int minY, int width, int height)
  {
    for (int j = 0; j < height; j++) {
      int pathY = minY + j;
      for (int i = 0; i < width; i++) {
        int pathX = minX + i;
        if (!path.contains (pathX,pathY))
          data[j*width+i] = 0;
      }
    }
  }

  private void printData (int[] data, int width, int height)
  {
    System.out.println ("data: "+width+"x"+height);
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        int alpha = (data[j*width+i] >> 24) & 0xff;
        System.out.print (((alpha==0)?'.':'X'));
      }
      System.out.println ();
    }
  }

}

