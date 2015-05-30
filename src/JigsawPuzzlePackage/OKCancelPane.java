package JigsawPuzzlePackage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** General purpose OK/Cancel panel, for use at the bottom of a custom dialog
 * or frame.
 *
 * <p> An OKCancelPane contains two buttons.  The "OK" button, on the left,
 * is intended to confirm the action represented in the main dialog or
 * frame; the "Cancel" button, on the right, is intended to abort it.  The
 * buttons are equal in size, and are packed at the right side of the pane.
 *
 * <p> Typical use of an OKCancelPane is to add it to the bottom of a
 * window, add an ActionListener to the OKButton, and pass the window to
 * <code>setWindow()</code>.  The Cancel button has a default
 * ActionListener that calls <code>dispose()</code> on the window, if it is
 * non-null.
 *
 * <p> The text of both buttons can be customized.  If a button's text is
 * changed, its character mnemonic automatically changes to its first
 * letter.  The mnemonic can be customized as well, as long as this is done
 * after the text is changed.
 *
 * <p> The text of all OK and Cancel buttons can be customized by setting
 * the static fields <code>OK_TEXT</code> and <code>CANCEL_TEXT</code>.
 * Only OKCancelPanes constructed after the fields are changed will be
 * affected.
 */
public class OKCancelPane extends JPanel
{
  // Class constants ------------------------------------------------------

  /** Default text on the OK button. */
  public static String OK_TEXT = "OK";

  /** Default text on the Cancel button. */
  public static String CANCEL_TEXT = "Cancel";

  // Constructors ---------------------------------------------------------

  public OKCancelPane()
  {
    super (new BorderLayout());
    okButton     = new JButton ();
    cancelButton = new JButton ();
    setOKText     (OK_TEXT);
    setCancelText (CANCEL_TEXT);

    JPanel buttonPane = new JPanel (new GridLayout(1,2,10,10));
    buttonPane.add (okButton);
    buttonPane.add (cancelButton);

    add (buttonPane, BorderLayout.EAST);

    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent e)
      { if (window != null) window.dispose(); }});
  }

  // Instance variables ---------------------------------------------------

  JButton okButton, cancelButton;
  Window window;

  // Accessors ------------------------------------------------------------

  /** Adds an ActionListener to the OK button. */
  public void addOKActionListener (ActionListener listener)
  { okButton.addActionListener (listener); }
  
  /** Adds an ActionListener to the Cancel button. */
  public void addCancelActionListener (ActionListener listener)
  { cancelButton.addActionListener (listener); }
  
  /** Removes an ActionListener from the OK button. */
  public void removeOKActionListener (ActionListener listener)
  { okButton.removeActionListener (listener); }

  /** Removes an ActionListener from the Cancel button. */
  public void removeCancelActionListener (ActionListener listener)
  { cancelButton.removeActionListener (listener); }

  /** Sets the Window containing this OKCancelPane. */
  public void setWindow (Window window) { this.window = window; }

  /** Sets the text of this OKCancelPane's OK button.  Its character
   * mnemonic will automatically be set to the first character of the new
   * text.
   */
  public void setOKText (String text)
  {
    okButton.setText (text);
    setOKMnemonic (text.charAt(0));
  }

  /** Sets the text of this OKCancelPane's Cancel button.  Its character
   * mnemonic will automatically be set to the first character of the new
   * text.
   */
  public void setCancelText (String text)
  {
    cancelButton.setText (text);
    setCancelMnemonic (text.charAt(0));
  }

  /** Sets the character mnemonic of this pane's OK button. */
  public void setOKMnemonic (char mnem)
  { okButton.setMnemonic (mnem); }

  /** Sets the character mnemonic of this pane's Cancel button. */
  public void setCancelMnemonic (char mnem)
  { cancelButton.setMnemonic (mnem); }

  /** Sets the enable state of this pane's OK button. */
  public void setOKEnabled (boolean state)
  { okButton.setEnabled (state); }

  /** Sets the enable state of this pane's Cancel button. */
  public void setCancelEnabled (boolean state)
  { cancelButton.setEnabled (state); }

}

