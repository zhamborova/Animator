package cs3500.view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animation.RoIAnimation;
import cs3500.animation.State;
import cs3500.shape.Ellipse;
import cs3500.shape.Rect;
import cs3500.shape.Shape;

/**
 * A class representing a panel for Visual View.
 */
public class VisualPanel extends JPanel implements ActionListener {
  private RoIAnimation model;
  private int tick = 0;
  Timer t;


  /**
   * Default constructor for a VisualPanel.
   * Starts the timer, initializes the model, and sets the background to white by default.
   *
   * @param model the model to initialize
   * @param speed the speed of the animation
   */
  public VisualPanel(RoIAnimation model, int speed) {
    Objects.requireNonNull(model);
    this.model = model;
    this.setBackground(Color.WHITE);
    this.setSize(model.getWidth(), model.getHeight());
    t = new Timer(1000 / speed, this);
  }


  /**
   * Draws the shapes.
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Map.Entry<String, Shape> entry : updateShapes().entrySet()) {
      State s = entry.getValue().copyState();
      int r = s.r;
      int gg = s.g;
      int b = s.b;
      int x = s.x - model.getX();
      int y = s.y - model.getY();
      int w = s.width;
      int h = s.height;
      g.setColor(new Color(r, gg, b));

      if (entry.getValue() instanceof Rect) {
        g.fillRect(x, y, w, h);
        g.drawRect(x, y, w, h);
      } else if (entry.getValue() instanceof Ellipse) {
        g.fillOval(x, y, w, h);
        g.drawOval(x, y, w, h);
      }
    }

  }

  /**
   * After each tick redraw the shapes.
   */
  public void actionPerformed(ActionEvent e) {
    tick++;
    repaint();
  }

  /**
   * Updates a copy of the shapes from the model to update the view.
   *
   * @return
   */
  public HashMap<String, Shape> updateShapes() {
    HashMap<String, Shape> animations = model.getAnimations();
    for (Map.Entry<String, Shape> entry : animations.entrySet()) {
      entry.getValue().updateCurrentState(tick);
    }
    return animations;
  }

  /**
   * Allows the frame to start the time in the panel.
   */
  public void start() {
    t.start();
  }
}