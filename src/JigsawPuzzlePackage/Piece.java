package JigsawPuzzlePackage;


import java.awt.*;
import java.awt.image.*;
import java.util.*;

// ### Should Pieces be implemented as BufferedImages backed by Rasters,
// rather than images backed by MemoryImageSources?

// ### Puzzle feature: time yourself!

// ### Classic piece edges are problematic.  First of all, the corners are
// weird.  Secondly, the edges are probably too close together.  This is
// causing the bevels to overlap very slightly, so they don't look quite
// right.

/**
 * A single piece of a jigsaw puzzle.  Each piece knows the portion of the
 * image it contains, how its edges are to be drawn, and what its
 * neighboring pieces are.
 *
 * <p> When two or more Pieces are put together, the result is another
 * Piece object, a Unipiece.
 *
 * @see Unipiece
 */
public class Piece
{
  // Class constants ------------------------------------------------------

  /** A Piece must be within this many pixels of "perfect" to be considered
   * close. */
  public static final int posClose = 7;

  /** A Piece must be within this many degrees of rotation from another to
   * be considered aligned to it. */
  public static final int rotClose = 5;

  // Constructor and fields -----------------------------------------------

  
  /** Creates a new Piece.  No initial rotation is done.  (This is needed
   * by Unipiece, which needs to set its subpieces before rotating.)
   * @param data        image data
   * @param imageX      X position of image relative to entire puzzle
   * @param imageY      Y position of image relative to entire puzzle
   * @param imageWidth   width of original image
   * @param imageHeight height of original image
   * @param totalWidth  the width of the entire picture
   * @param totalHeight the height of the entire picture
   */
  protected Piece(
    int[] data,
    int imageX, int imageY,
    int imageWidth, int imageHeight,
    int totalWidth, int totalHeight)
  {
    neighbors = new HashSet<Piece>();
    setImageData (data, imageWidth, imageHeight);
    setImagePosition (imageX, imageY);
    this.totalWidth = totalWidth;
    this.totalHeight = totalHeight;
  }
  
  /** Creates a new Piece.
   * @param data        image data
   * @param imageX      X position of image relative to entire puzzle
   * @param imageY      Y position of image relative to entire puzzle
   * @param imageWidth   width of original image
   * @param imageHeight height of original image
   * @param totalWidth  the width of the entire picture
   * @param totalHeight the height of the entire picture
   * @param rotation    initial rotation
   */
  public Piece(
    int[] data,
    int imageX, int imageY,
    int imageWidth, int imageHeight,
    int totalWidth, int totalHeight,
    int rotation)
  {
    this (data, imageX, imageY, imageWidth, imageHeight,
      totalWidth, totalHeight);
    forceSetRotation (rotation);
  }

  // Location in the image.
  private int imageX, imageY;

  // Size of the entire image.
  private int totalWidth, totalHeight;

  // Location in the image adjusted by current rotation.
  private int rotatedX, rotatedY;

  /** Pieces considered to be neighbors to this one.  They are the only
   * ones that can be fitted to it.
   */
  protected Set<Piece> neighbors;

  /** Original image size and data. */
  protected int origWidth, origHeight;
  private int[] origData;

  /** Current size and data, taking rotation into account. */
  protected int curWidth, curHeight;
  protected int[] curData;

  // Location in the puzzle panel.
  private int puzzleX, puzzleY;

  // Image for this Piece.  We're going to now try making this null for
  // non-atomic pieces.
  private Image image;

  // This is measured in integer degrees, 0-359.  0 is unrotated.  90 is 90
  // degrees clockwise, etc.
  private int rotation;


  // Accessors ------------------------------------------------------------

  // ### This should be done in the constructor...
  /** Sets this Piece's upper-left position relative to the upper-left
   * position of the entire image.  This position can be arbitrary, but for
   * efficiency, it should be the upper-left corner of the smallest
   * rectangle that contains all of this Piece's image data.  This method
   * should typically be called when the Piece is first created, and never
   * again.
   */
  protected void setImagePosition (int x, int y)
  {
    this.imageX = x;
    this.imageY = y;
  }

  /** Returns this Piece's current rotation.  The rotation is given in
   * integer degrees clockwise, and will always be between 0 and 359
   * inclusive.
   */
  public int getRotation() { return rotation; }

  /** Sets this Piece's current rotation.  The rotation is given in integer
   * degrees clockwise, and should always be between 0 and 359 inclusive.
   * If the new rotation is different, this Piece's image data will be
   * recomputed.
   * @throws IllegalArgumentException if rotation is not in [0,359]
   */
  public void setRotation (int rot)
  {
    if (rot == rotation) return;
    forceSetRotation (rot);
  }

  /** Sets this Piece's current rotation.  Unlike setRotation(), this
   * method forces a recompute of the image.
   * @throws IllegalArgumentException if rotation is not in [0,359]
   */
  protected void forceSetRotation (int rot)
  {
    if ((rot < 0) || (rot > 359)) throw new IllegalArgumentException
      ("invalid rotation: "+rot);
    // allow only 0, 90, 180, 270.
    if (rot % 90 != 0) {
      int newRot = rot / 90;
      System.out.println ("unsupported rotation "+rot
        +"; using "+newRot+" instead");
      rot = newRot;
    }
    rotation = rot;
    recomputeImageData();
    if (image != null) image.flush();
    image = Toolkit.getDefaultToolkit().createImage (
      new MemoryImageSource (curWidth,curHeight, curData, 0, curWidth));
  }

  /** Sets this Piece's upper-left position relative to the upper-left
   * position of the JigsawPuzzle.
   */
  public void setPuzzlePosition (int x, int y)
  {
    this.puzzleX = x;
    this.puzzleY = y;
  }

  /** Returns this Piece's current height in pixels.  This is the height of
   * the smallest rectangle containing all of this Piece's image data at
   * its current rotation.
   */
  public int getCurrentHeight() { return curHeight; }

  /** Returns this Piece's current width in pixels.  This is the width of
   * the smallest rectangle containing all of this Piece's image data at
   * its current rotation.
   */
  public int getCurrentWidth() { return curWidth; }

  /** Returns the width of the entire picture. */
  public int getTotalWidth() { return totalWidth; }

  /** Returns the height of the entire picture. */
  public int getTotalHeight() { return totalHeight; }

  /** Returns this Piece's image height in pixels.  This is the height of
   * the smallest rectangle containing all of this Piece's image data in
   * its original orientation.
   */
  public int getImageHeight() { return origHeight; }

  /** Returns this Piece's image width in pixels.  This is the width of the
   * smallest rectangle containing all of this Piece's image data in its
   * original orientation.
   */
  public int getImageWidth() { return origWidth; }

  /** Returns this Piece's X position in the original image. */
  public int getImageX() { return imageX; }

  /** Returns this Piece's Y position in the original image. */
  public int getImageY() { return imageY; }

  /** Returns this Piece's X position in the original image, modified by
   * its current rotation.  The origin is the center of rotation.  */
  public int getRotatedX() { return rotatedX; }

  /** Returns this Piece's Y position in the original image, modified by
   * its current rotation.  The origin is the center of rotation.  */
  public int getRotatedY() { return rotatedY; }

  /** Returns this Piece's X position in the original image, modified by
   * the given rotation.  */
  public int getRotatedX(int rotation) { return rotatedX; }

  /** Returns this Piece's Y position in the original image, modified by
   * the given rotation.  */
  public int getRotatedY(int rotation) { return rotatedY; }

  /** Returns this Piece's X position in the puzzle. */
  public int getPuzzleX() { return puzzleX; }

  /** Returns this Piece's Y position in the puzzle. */
  public int getPuzzleY() { return puzzleY; }

  /** Returns this Piece's current image.  This will be the Piece's portion
   * of the original image, rotated by this Piece's current rotation.
   */
  public Image getImage () { return image; }

  /** Adds a Piece to this Piece's set of neighbors.
   * @throws NullPointerException if neighbor is null
   */
  public void addNeighbor (Piece neighbor)
  {
    if (neighbor == null) throw new NullPointerException ("null neighbor");
    neighbors.add (neighbor);
  }

  /** Removes the given Piece from this Piece's set of neighbors.
   */
  public void removeNeighbor (Piece neighbor)
  { neighbors.remove (neighbor); }

  /** Moves this Piece to the given location, relative to the puzzle
   * panel's upper-left corner.
   */
  public void moveTo (int x, int y)
  { setPuzzlePosition (x, y); }

  public String toString()
  {
    return "Piece[iPos=("+imageX+","+imageY+"),"
      +"iSiz="+origWidth+"x"+origHeight+","
      +"rot="+rotation+","
      +"rPos=("+rotatedX+","+rotatedY+"),"
      +"pPos=("+puzzleX+","+puzzleY+")]";
  }

  /** Draws this Piece in the given Graphics object.  The current image
   * will be drawn, at this Piece's current puzzle position.  */
  protected void draw (Graphics g)
  {
    Image img = getImage();
    if (img != null)
      g.drawImage (img, getPuzzleX(), getPuzzleY(), null);
  }

  /** Returns whether this Piece currently contains the given point,
   * relative to the puzzle panel's upper-left corner.
   */
  public boolean contains (int x, int y)
  {
    int puzX = getPuzzleX();
    int puzY = getPuzzleY();
    int w = getCurrentWidth();
    int h = getCurrentHeight();
    return
      (puzX <= x) && (x <= (puzX+w-1)) &&
      (puzY <= y) && (y <= (puzY+h-1)) &&
      (getAlpha(x-puzX, y-puzY) != 0);
  }

  /** Returns the alpha (transparency) value at the given coordinates in
   * the current image data.
   */
  protected int getAlpha (int x, int y)
  {
    int pixel = curData[y*curWidth + x];
    return (pixel >> 24) & 0xff;
  }

  /** Returns whether this piece is located and oriented close enough to
   * the given Piece to be fitted.
   */
  public boolean isCloseTo (Piece piece)
  {
    // System.out.println ("isCloseTo this:"+this+" piece:"+piece);
    // Don't even bother if they're not aligned.
    int rot = getRotation();
    int rotD = Math.abs (piece.getRotation() - getRotation());
    rotD = Math.min (rotD, 360-rotD);
    if (rotD > rotClose) return false;
    // System.out.println ("aligned...");
    int puzXD = getPuzzleX() - piece.getPuzzleX();
    int puzYD = getPuzzleY() - piece.getPuzzleY();
    int rotXD = getRotatedX() - piece.getRotatedX(rot);
    int rotYD = getRotatedY() - piece.getRotatedY(rot);
    return
      (Math.abs(puzXD-rotXD) <= posClose) &&
      (Math.abs(puzYD-rotYD) <= posClose) ;
  }

  // ### This should be built into the constructor, I think.
  /** Sets the image data and size for this Piece. */
  protected void setImageData (int[] data, int width, int height)
  {
    this.curWidth  = this.origWidth  = width;
    this.curHeight = this.origHeight = height;
    origData = data;
    // forceSetRotation (rotation);
  }


  // Joining pieces -------------------------------------------------------

  /** Checks whether any of this Piece's neighbors are located and oriented
   * close enough to be joined to this one.
   * @return an array of Pieces, or null if no neighbors were close enough;
   *   if the array is non-null, the first Piece will be the new one;
   *   subsequent Pieces will be the ones it was built from
   */
  public Piece[] join ()
  {
    ArrayList<Piece> close = new ArrayList<Piece>();
    Iterator<Piece> iter = neighbors.iterator();
    while (iter.hasNext()) {
      Piece piece = (Piece) iter.next();
      if (piece.isCloseTo (this)) close.add (piece);
    }
    // System.out.println ("close: "+close.size());
    // for (int i = 0; i < close.size(); i++)
      // System.out.println (close.get(i));
    if (close.size() == 0) return null;

    // We can't just return the new Unipiece, because the JigsawPuzzle
    // needs to know what pieces the new one was formed from that are
    // currently in its list.  These might include other Unipieces, which
    // wouldn't be in the new Piece's subpiece list.
    Piece newPiece = Unipiece.join (this, close);
    Piece[] ret = new Piece[close.size()+2];
    ret[0] = newPiece;
    ret[1] = this;
    this.image.flush();
    for (int i = 0; i < close.size(); i++) {
      Piece piece = (Piece) close.get(i);
      ret[i+2] = piece;
      piece.image.flush();
    }
    System.gc();
    return ret;
  }


  // Bevel drawing --------------------------------------------------------

  /** Draws bevels on data.  Check every opaque pixel's NW and SE
   * neighbors.  If NW is transparent and SE is opaque, brighten the
   * central pixel.  If it's the other way around, darken it.  If both or
   * neither are transparent, leave it alone.
   */
  static void bevel (int[] data, int width, int height)
  {
    // Scan diagonal NW-SE lines.  The first and last lines can be skipped.
    for (int i = 0; i < width+height-3; i++) {
      int x = Math.max (0, i-height+2);
      int y = Math.max (0, height-i-2);
      boolean nw, c, se; // true iff that pixel is opaque
      nw = c = se = false;
      c = (((data[y*width+x] >> 24) & 0xff) > 0);
      while ((x < width) && (y < height)) {
        if ((x+1 < width) && (y+1 < height))
          se = (((data[(y+1)*width+(x+1)] >> 24) & 0xff) > 0);
        else se = false;
        if (c) {
          int datum = data[y*width+x];
          if ( nw && !se) data[y*width+x] =   darker (datum);
          if (!nw &&  se) data[y*width+x] = brighter (datum);
        }
        nw = c;
        c = se;
        x++; y++;
      }
    }
  }

  // This mimics Color.brighter() and Color.darker().  They multiply or
  // divide R/G/B by 0.7, and trim them to 0 or 255 if needed.  I'm going
  // to use 7/10 (so it's int arithmetic), and not use Math.  I don't quite
  // trust inlining yet.  And I certainly don't want to make scads of Color
  // objects for each pixel.  It's bad enough these are methods, and not
  // inlined in bevel().
  static final int fn = 10;
  static final int fd =  7;
  static final int maxB =  255 * fd / fn;
  static final int brighter (int val) {
    int r = (val >> 16) & 0xff;
    int g = (val >>  8) & 0xff;
    int b = (val      ) & 0xff;

    // Black goes to #030303 gray
    if ((r==0) && (g==0) && (b==0)) return 0xff030303;
    r = (r < 3) ? 3 : r;
    g = (g < 3) ? 3 : g;
    b = (b < 3) ? 3 : b;

    r = (r >= maxB) ? 255 : (r * fn / fd);
    g = (g >= maxB) ? 255 : (g * fn / fd);
    b = (b >= maxB) ? 255 : (b * fn / fd);
    return ((((0xff00 | r) << 8) | g) << 8) | b;
  }
  static final int darker (int val) {
    int r = (val >> 16) & 0xff;
    int g = (val >>  8) & 0xff;
    int b = (val      ) & 0xff;
    r = r * fd / fn;
    g = g * fd / fn;
    b = b * fd / fn;
    return ((((0xff00 | r) << 8) | g) << 8) | b;
  }

  // 4-way rotation -------------------------------------------------------

  /**
   * Sets this Piece's rotated position and size, based on its current
   * rotation.  The entire puzzle is rotated about the origin, and then
   * translated so that its new upper left corner is at the origin.  Each
   * piece's rotated position is then defined by its new upper left corner
   * in the rotated puzzle.
   */
  protected void setRotatedPosition()
  {
    int rot = getRotation();
    switch (rot) {
      case   0: rotatedX =  imageX; rotatedY =  imageY; 
        curWidth = origWidth; curHeight = origHeight; break;
      case  90:
        rotatedX = totalHeight-imageY-origHeight;
        rotatedY = imageX; 
        curWidth = origHeight; curHeight = origWidth; break;
      case 180:
        rotatedX = totalWidth -imageX-origWidth;
        rotatedY = totalHeight-imageY-origHeight; 
        curWidth = origWidth; curHeight = origHeight; break;
      case 270:
        rotatedX =  imageY;
        rotatedY = totalWidth -imageX-origWidth; 
        curWidth = origHeight; curHeight = origWidth; break;
      default:
        System.out.println ("sRotPos() can't handle rotation: "+rot);
    }
  }

  /** Recomputes this Piece's current image data and size from its original
   * image data and rotation.
   */
  protected void recomputeImageData()
  {
    setRotatedPosition();
    if (rotation == 0) {
      curData = (int[]) origData.clone();
    } else if (rotation == 90) {
      curData = new int[origData.length];
      for (int i = 0; i < curWidth; i++)
        for (int j = 0; j < curHeight; j++)
          try {
          curData[j*curWidth+i] = origData[(origHeight-i-1)*origWidth + j];
          } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println ("ArrayIndexOutOfBoundsException");
            System.out.println (" olen="+origData.length+" clen="+curData.length);
            System.out.println (" i="+i+" j="+j);
            System.out.println (" orgW="+origWidth+" orgH="+origHeight);
            System.out.println (" curW="+ curWidth+" curH="+ curHeight);
            System.out.println (
               " cIdx="+ (j*curWidth+i)
              +" oIdx="+ ((origWidth-j-1)*origHeight + i));
            throw new NullPointerException();
          }
    } else if (rotation == 180) {
      curData = new int[origData.length];
      for (int i = 0; i < curWidth; i++)
        for (int j = 0; j < curHeight; j++)
          curData[j*curWidth+i] =
            origData[(origHeight-j-1)*origWidth + (origWidth-i-1)];
    } else if (rotation == 270) {
      curData = new int[origData.length];
      for (int i = 0; i < curWidth; i++)
        for (int j = 0; j < curHeight; j++)
          curData[j*curWidth+i] = origData[i*origWidth + (origWidth-j-1)];
    }
    bevel(curData, curWidth, curHeight);
    image = Toolkit.getDefaultToolkit().createImage (
      new MemoryImageSource (curWidth,curHeight, curData, 0, curWidth));
  }




}


