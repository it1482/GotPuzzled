package JigsawPuzzlePackage;


import java.awt.image.*;
import java.util.*;

/**
 * A set of joined pieces of a jigsaw puzzle.  It knows the same things a
 * Piece knows, generally by way of keeping a set of the simple Pieces it
 * contains.
 *
 * <p> When two or more Pieces are put together, the result is another
 * Piece object.  Pieces that weren't created this way are referred to here
 * as <i>atomic pieces</i>.
 */
public class Unipiece extends Piece
{

  // Constructor and fields -----------------------------------------------

  /** Creates a new Unipiece.
   * @param subpieces ; this set is used directly by Unipiece,
   *   and should not be modified afterward
   * @param imageX      X position of image relative to entire puzzle
   * @param imageY      Y position of image relative to entire puzzle
   * @param imageWidth   width of original image
   * @param imageHeight height of original image
   * @param totalWidth   width of the entire puzzle in pixels
   * @param totalHeight height of the entire puzzle in pixels
   * @param rotation    initial rotation
   */
  public Unipiece (
    Set<Piece> subpieces,
    int imageX, int imageY,
    int imageWidth, int imageHeight,
    int totalWidth, int totalHeight,
    int rotation)
  {
    // super (totalWidth, totalHeight);
    super (null, imageX, imageY, imageWidth, imageHeight,
      totalWidth, totalHeight);
    // setImagePosition (imageX, imageY);
    // setImageSize (imageWidth, imageHeight);
    subs = subpieces;
    forceSetRotation (rotation);
  }

  // For a Unipiece, original image size and data work differently.  Size
  // is equivalent to the smallest rectangle bounding all subpieces.  Image
  // data is always null; it's always rebuilt from the subpieces.

  // Subpieces.  Records of subs are necessary because of rotation.
  // Without it, subpiece images can be thrown away after they're combined
  // to form the joined piece.  With rotation, however, the bevels will
  // need to be repainted, and so the original image data is still needed.
  // Subs are also necessary if someday I decide to support splitting
  // joined pieces back into their parts.
  private Set<Piece> subs;


  // Accessors ------------------------------------------------------------

  /** Returns an Iterator over this Piece's subpieces, if it is formed from
   * a join.
   * @return an Iterator over the subpieces, or null if this Piece is
   *   atomic
   */
  public Iterator subpieces()
  { return (subs == null) ? null : subs.iterator(); }

  /** Sets the image data for this Piece.  This is disabled for a
   * Unipiece, which cannot be given image data of its own.  Its image
   * data is gathered automatically from its subpieces whenever its
   * rotation is set.
   * @param data the data.  This is expected to be in the format of data
   *   returned by a PixelGrabber.  
   * @throws IllegalArgumentException if data is non-null
   * @see PixelGrabber
   */
  protected void setImageData (int[] data, int width, int height)
  {
    if (data != null) throw new IllegalArgumentException
      ("cannot set image data on a Unipiece");
    setImageSize (width, height);
  }

  // ### This should be built into the constructor, I think.
  protected void setImageSize (int width, int height)
  {
    this.curWidth  = this.origWidth  = width;
    this.curHeight = this.origHeight = height;
  }

  public void setRotation (int rot)
  {
    Iterator iter = subpieces();
    while (iter.hasNext())
      ((Piece)iter.next()).setRotation (rot);
    // Call this last, so it will rebuild this image from the subs
    super.setRotation (rot);
  }

  public String toString()
  {
    return "Multi"+super.toString()+"[pieces="+subs.size()+"]";
  }


  // Joining pieces -------------------------------------------------------

  /** Joins the given main Piece with the list of Pieces, and returns a new
   * Piece.  The new Piece's location and orientation are based on those of
   * the main Piece.
   * @param main main Piece
   * @param others other Pieces
   */
  protected static Unipiece join (Piece main, ArrayList<?> others)
  {
    Set neighbors = new HashSet();
    neighbors.addAll (main.neighbors);
    int mainPX = main.getPuzzleX();
    int mainPY = main.getPuzzleY();
    // Compute a bounding rectangle for all pieces.
    // Build the neighbors set at the same time.
    int minX = main.getImageX();
    int minY = main.getImageY();
    int maxX = minX + main.getImageWidth()  - 1;
    int maxY = minY + main.getImageHeight() - 1;
    for (int i = 0; i < others.size(); i++) {
      Piece piece = (Piece) others.get(i);
      int minXT = piece.getImageX();
      int minYT = piece.getImageY();
      int maxXT = minXT + piece.getImageWidth()  - 1;
      int maxYT = minYT + piece.getImageHeight() - 1;
      minX = Math.min (minX, minXT);
      minY = Math.min (minY, minYT);
      maxX = Math.max (maxX, maxXT);
      maxY = Math.max (maxY, maxYT);
      neighbors.addAll (piece.neighbors);
    }
    int width  = maxX-minX+1;
    int height = maxY-minY+1;

    // new piece neighbors = main/others' neighbors, minus themselves
    neighbors.remove (main);
    for (int i = 0; i < others.size(); i++)
      neighbors.remove (others.get(i));

    // Build the set of subpieces.
    Set<Piece> subs = new HashSet<Piece>();
    addSubs (subs, main);
    for (int i = 0; i < others.size(); i++)
      addSubs (subs, (Piece)others.get(i));

    // Make the new Piece, and set its data, size, and positions.
    Unipiece newPiece = new Unipiece(
      subs,
      minX, minY,    // image position
      width, height, // image size
      main.getTotalWidth(), main.getTotalHeight(),
      main.getRotation());

    // Set the new piece position so that the main piece doesn't appear to
    // move.
    int dx = newPiece.getRotatedX() - main.getRotatedX();
    int dy = newPiece.getRotatedY() - main.getRotatedY();
    // int dx = minX - main.getImageX();
    // int dy = minY - main.getImageY();
    newPiece.setPuzzlePosition ( mainPX+dx, mainPY+dy );

    // Add each piece as a neighbor, and change each piece's neighbors
    //  (remove main and others, and add newPiece)
    Iterator iter = neighbors.iterator();
    while (iter.hasNext()) {
      Piece piece = (Piece)iter.next();
      newPiece.addNeighbor (piece);
      piece.removeNeighbor (main);
      for (int i = 0; i < others.size(); i++)
        piece.removeNeighbor ((Piece)others.get(i));
      piece.addNeighbor (newPiece);
    }

    // System.out.println ("newPiece: "+newPiece);
    // iter = newPiece.subs();
    // while (iter.hasNext())
      // System.out.println ("  "+iter.next());

    return newPiece;
  }

  private static void addSubs (Set<Piece> subset, Piece piece)
  {
    if (piece instanceof Unipiece)
      subset.addAll (((Unipiece)piece).subs);
    else subset.add (piece);
  }

  // This is effectly image blending with support for 0 or max
  // transparency, with int arrays as data.  I couldn't find a tool for
  // doing this in JDK.
  /** Overlays the current image in the given Piece onto the data array.
   * The data array's image location is given by (dataX,dataY).  Its image
   * size is given by (width,height).  The Piece's image is assumed to fit
   * entirely within the data image rectangle.
   */
  protected static void overlay (int[] data, int dataX, int dataY,
    int width, int height, Piece piece)
  {
    int pieceX = piece.getRotatedX();
    int pieceY = piece.getRotatedY();
    int pieceW = piece.getCurrentWidth();
    int pieceH = piece.getCurrentHeight();
    // System.out.println ();
    // System.out.println ("overlay start data:");
    // print (data, width, height);

    int[] newData = piece.curData;
    // System.out.print ("newData: ");
    // print (newData, pieceW, pieceH);

    // Fold it into the data.  ReSPECT mah transparensah!
    int offset = (pieceY-dataY) * width + (pieceX-dataX);
    // System.out.println ("folding "+piece+" to off:"+offset);
    for (int i = 0; i < pieceH; i++) {
      int iNDOffset = i*pieceW;
      int iDOffset  = i*width;
      for (int j = 0; j < pieceW; j++) {
        int ndOff = iNDOffset+j;
        int newDatum = newData[ndOff];
        if (newDatum != 0) // 0==transparent, in this case
          // try {
          data[ offset + iDOffset + j ] = newDatum;
          /*
          } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println ("ArrayIndexOutOfBoundsException");
            System.out.println ("i="+i+" j="+j);
            System.out.println ("offset="+offset+" iDOffset="+iDOffset);
            System.out.println ("datalen="+data.length);
            System.out.println ("dataX="+dataX+" dataY="+dataY);
            System.out.println ("pieceX="+pieceX+" pieceY="+pieceY);
            System.out.println ("pieceW="+pieceW+" pieceH="+pieceH);
            throw new NullPointerException();
          }
          */
      }
    }
    // System.out.print ("after fold: ");
    // print (data, width, height);
  }

  // Debugging method.
  static void print (int[] data, int width, int height)
  {
    System.out.println("data (first 2 bytes): w:"+width+" h:"+height
      +" l:"+data.length);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int datum = (data[i*width+j] >> 16) & 0xffff;
        String hex = Integer.toHexString(datum);
        while (hex.length() < 4) hex = '0'+hex;
        System.out.print (hex+' ');
      }
      System.out.println();
    }
  }


  // 4-way rotation -------------------------------------------------------

  protected void recomputeImageData()
  {
    setRotatedPosition();
    // System.out.println ("recomputing: "+this);
    int[] data = new int[curWidth*curHeight];
    int rotX = getRotatedX();
    int rotY = getRotatedY();
    Iterator<?> iter = subpieces();
    while (iter.hasNext()) {
      Piece sub = (Piece)iter.next();
      overlay (data, rotX, rotY, curWidth, curHeight, sub);
    }
    curData = data;
  }

}

