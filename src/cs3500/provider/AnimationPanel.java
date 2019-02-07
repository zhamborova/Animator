package cs3500.provider;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;


/**
 * Represents a panel on which to draw an animation.
 */
public class AnimationPanel extends JPanel {
  private List<Transformation> transformations;
  private Map<String, ShapeType> idsToTypes;
  private int tick; // time

  /**
   * Constructs an AnimationPanel with nothing on it.
   */
  public AnimationPanel() {
    this.transformations = Collections.emptyList();
    this.idsToTypes = Collections.emptyMap();
  }

  /**
   * Draws this panel.
   *
   * @param g The paintbrush.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    for (Transformation t : transformations) {
      if (t.startTick() < tick && tick <= t.endTick()) {
        drawShape(g2d, t);
      }
    }
  }

  /**
   * Draws the target shape in the given transformation.
   *
   * @param g2d The paintbrush.
   * @param t   The transformation happening now.
   */
  public void drawShape(Graphics g2d, Transformation t) {
    int startTick = t.startTick();
    ImmutablePoint sLocation = t.getStartLocation();
    ImmutablePoint eLocation = t.getEndLocation();
    Color sColor = t.getStartColor();
    Color eColor = t.getEndColor();
    int length = t.endTick() - startTick;
    int sHeight = t.getStartHeight();
    int eHeight = t.getEndHeight();
    int sWidth = t.getStartWidth();
    int eWidth = t.getEndWidth();
    ShapeType type = this.idsToTypes.get(t.getID());
    int dTime = tick - startTick;

    // calculate the values of the shape's intermediate properties at this panel's current tick
    Color color = new Color(dTime * (eColor.getRed() - sColor.getRed()) / length + sColor.getRed(),
            dTime * (eColor.getGreen() - sColor.getGreen()) / length + sColor.getGreen(),
            dTime * (eColor.getBlue() - sColor.getBlue()) / length + sColor.getBlue());
    int height = dTime * (eHeight - sHeight) / length + sHeight;
    int width = dTime * (eWidth - sWidth) / length + sWidth;
    int x = dTime * (eLocation.x - sLocation.x) / length + sLocation.x;
    int y = dTime * (eLocation.y - sLocation.y) / length + sLocation.y;

    g2d.setColor(color);

    switch (type) {
      case ELLIPSE:
        g2d.fillOval(x, y, width, height);
        break;

      case RECTANGLE:
        g2d.fillRect(x, y, width, height);
        break;

      default:
        throw new IllegalArgumentException("Unsupported shape.");
    }

  }

  /**
   * Takes in the transformations to display.
   *
   * @param transformations The transformations to display.
   */
  public void acceptTransformations(List<Transformation> transformations) {
    this.transformations = transformations;
  }

  /**
   * Takes in a mapping of shape IDs to what type they are.
   *
   * @param idsToTypes The mapping.
   */
  public void acceptIDsToTypes(Map<String, ShapeType> idsToTypes) {
    this.idsToTypes = idsToTypes;
  }

  /**
   * Takes in what the current tick time is.
   *
   * @param tick The current tick time.
   */
  public void acceptTick(int tick) {
    this.tick = tick;
  }
}
