package cs3500.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import cs3500.controller.IViewListener;

/**
 * Represents a section of the UI that handles editting or adding a keyframe.
 */
public class EditFramePanel extends JPanel {
  JButton addMod;
  JTextField t;
  JTextField x;
  JTextField y;
  JTextField w;
  JTextField h;
  JTextField r;
  JTextField g;
  JTextField b;

  /**
   * Default constructor for the EditFramePanel.
   */
  public EditFramePanel() {

    this.t = new JTextField(4);
    JLabel t1 = new JLabel("Tick");

    this.add(t1);
    this.add(t);
    this.x = new JTextField(4);
    JLabel x1 = new JLabel("X");

    this.add(x1);
    this.add(x);
    this.y = new JTextField(4);

    JLabel y1 = new JLabel("Y");
    this.add(y1);
    this.add(y);
    this.w = new JTextField(4);
    JLabel w1 = new JLabel("Width");
    this.add(w1);
    this.add(w);
    this.h = new JTextField(4);
    JLabel h1 = new JLabel("Height");
    this.add(h1);
    this.add(h);
    this.r = new JTextField(4);
    JLabel r1 = new JLabel("R");
    this.add(r1);
    this.add(r);
    this.g = new JTextField(4);
    JLabel g1 = new JLabel("G");
    this.add(g1);
    this.add(g);
    this.b = new JTextField(4);
    JLabel b1 = new JLabel("B");
    this.add(b1);
    this.add(b);
    this.addMod = new JButton("Add/Modify Frame");
    this.addMod.setActionCommand("Add Mod Frame");
    this.add(addMod);
  }

  /**
   * Returns the field inputs for the frame. The String will always return in this order: upper
   * level methods will depend on this promise. NEVER USED, but implemented in case.
   */
  public String getInputs() {
    return t.getText() + " " + x.getText() + " " + w.getText() + " " + h.getText() + " " +
            y.getText() + " " + r.getText() + " " + g.getText() + " " +
            b.getText();
  }

  /**
   * Gets the user input for time from the text box.
   */
  public int getT() {
    try {
      return Integer.parseInt(t.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Gets the user input for x from the text box.
   */
  public int getXPosn() {

    try {
      return Integer.parseInt(x.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }

  }

  /**
   * Gets the user input for y from the text box.
   */
  public int getYPosn() {
    try {
      return Integer.parseInt(y.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Gets the user input for width from the text box.
   */
  public int getW() {
    try {
      return Integer.parseInt(w.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Gets the user input for height from the text box.
   */
  public int getH() {
    try {
      return Integer.parseInt(h.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Gets the user input for red from the text box.
   */
  public int getR() {
    try {
      return Integer.parseInt(r.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Gets the user input for green from the text box.
   */
  public int getG() {
    try {
      return Integer.parseInt(g.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Gets the user input for blue from the text box.
   */
  public int getB() {
    try {
      return Integer.parseInt(b.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Input x value");
    }
  }

  /**
   * Sets listeners to the panel. Lets any listeners that care know when the user wants to add or
   * modify a keyframe.
   *
   * @param buttons the listener
   */
  public void setListeners(IViewListener buttons) {
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buttons.action(e.getActionCommand());
      }
    };
    this.addMod.addActionListener(listener);
  }
}
