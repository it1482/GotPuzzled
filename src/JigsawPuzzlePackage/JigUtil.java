package JigsawPuzzlePackage;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.File;

/**
 * Common utility methods.
 */
public class JigUtil
{
  private static final Object ESC_CLOSES_FRAME = "ESC_CLOSES_FRAME";

  final static JPanel trackerPanel = new JPanel(false);
  final static MediaTracker tracker = new MediaTracker(trackerPanel);

  /** Ensures that the given Image has been loaded.  The current thread
   * will pause until all of the image data is in memory.
   */
  public static void ensureLoaded (Image image)
  {
    int id = 0;
    tracker.addImage (image, id);
    try { tracker.waitForID(id, 0); }
    catch (InterruptedException e) {
      System.out.println("INTERRUPTED while loading image");
    }
    tracker.removeImage (image, id);
  }

  /**
   * Moves the window to the center of the screen.  If this would place the
   * top left corner off the screen, the window is moved just enough to
   * keep the top left corner on screen.
   */
  public static void center (Window window)
  {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int newX = Math.max (0, (screen.width  - window.getWidth ()) / 2);
    int newY = Math.max (0, (screen.height - window.getHeight()) / 2);
    window.setLocation (newX, newY);
  }


  /**
   * Returns whether the given file is an image file.  For now, all this
   * does is check whether the file extension is JPG, GIF, or PNG.
   */
  public static boolean isImage (File file)
  {
    String name = file.getName();
    if (name.length() <= 4) return false;
    String ext = name.substring (name.length()-4);
    return
      ext.equalsIgnoreCase (".jpg") || 
      ext.equalsIgnoreCase (".gif") ||
      ext.equalsIgnoreCase (".png");
  }


  /** Used by setEscClosesFrame. */
  static class CloseWindowAction extends AbstractAction
  {
    JComponent comp;
    public CloseWindowAction (JComponent c) { this.comp = c; }
    public void actionPerformed(ActionEvent event)
    {
      Window w = SwingUtilities.getWindowAncestor(comp);
      if (w != null) w.dispose();
    }
  }

  // GeneralPath debugging ------------------------------------------------

  /** Prints the GeneralPath to System.out.  Coordinates are displayed to
   * the nearest hundredth. */
  public static void debugPath(GeneralPath path)
  {
    PathIterator iter = path.getPathIterator(null);
    float[] coords = new float[6];
    while (!iter.isDone()) {
      int type = iter.currentSegment(coords);
      String ts = null;
      int pts = 0;
      switch (type) {
        case PathIterator.SEG_MOVETO  : ts = "MOVETO "; pts=1; break;
        case PathIterator.SEG_LINETO  : ts = "LINETO "; pts=1; break;
        case PathIterator.SEG_QUADTO  : ts = "QUADTO "; pts=2; break;
        case PathIterator.SEG_CUBICTO : ts = "CUBICTO"; pts=3; break;
        case PathIterator.SEG_CLOSE   : ts = "CLOSE";   pts=0; break;
        default: ts="UNK:"+type;
      }
      System.out.print ("  "+ts+" ");
      for (int i = 0; i < pts; i++) {
        float x = coords[i*2];
        float y = coords[i*2+1];
        System.out.print ("("+form(x)+","+form(y)+") ");
      }
      System.out.println();
      iter.next();
    }
  }

  private static String form (float f)
  {
    int chaWidth = 4; // characteristic
    int manWidth = 2; // mantissa
    int f100 = Math.round (f * 100.0f);
    String fStr = Float.toString (f100 / 100f);
    if (fStr.indexOf('.') != -1)
      while (fStr.length() - fStr.indexOf('.') < manWidth+1)
        fStr = fStr+'0';
    while (fStr.length() < chaWidth+manWidth+2) fStr = ' '+fStr;
    return fStr;
  }

}

