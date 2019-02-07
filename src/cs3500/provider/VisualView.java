package cs3500.provider;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;


/**
 * Represents an animation that is shown frame-by-frame.
 */
public class VisualView extends JFrame implements cs3500.provider.AnimationView {
  private cs3500.provider.AnimationPanel panel;
  private int speed;
  private int endTick;
  private int currTick;

  /**
   * Constructs a VisualView.
   *
   * @param loc    The window's starting position.
   * @param width  The width of the window.
   * @param height The height of the window.
   * @param speed  The speed of the animation.
   * @throws IllegalArgumentException if any of the width, height, or speed are negative.
   */
  public VisualView(ImmutablePoint loc, int width, int height, int speed)
          throws IllegalArgumentException {
    super();

    if (width < 0) {
      throw new IllegalArgumentException("width is negative.");
    }

    if (height < 0) {
      throw new IllegalArgumentException("height is negative.");
    }

    if (speed < 0) {
      throw new IllegalArgumentException("Negative ticks per second.");
    }

    this.speed = speed;
    this.endTick = 0;
    this.currTick = 0;
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new cs3500.provider.AnimationPanel();
    this.panel.setPreferredSize(new Dimension(width, height));
    this.add(this.panel);
    this.panel.setSize(500, 500);
    this.setVisible(true);
    this.setLocation(loc.x, loc.y);
  }

  /**
   * Renders the animation frame-by-frame. This method completes concurrently with the thread it was
   * called from.
   */
  @Override
  public void draw() {
    // declare final with no action listener in order to use reference to it in the following lambda
    final Timer timer = new Timer(1000 / speed, null);
    timer.addActionListener(e -> {
      // inside if block to ensure that picture doesn't disappear when the animation is over
      if (currTick <= endTick) {
        panel.acceptTick(currTick);
        currTick++;
        this.repaint();
      } else {
        timer.stop();
      }
    });

    // the timer operates concurrently, so the program can continue before timer finishes
    timer.start();
  }

  @Override
  public void acceptTransformations(List<Transformation> transformations) {
    if (!Objects.nonNull(transformations)) {
      throw new IllegalArgumentException("Null transformations.");
    }

    this.panel.acceptTransformations(transformations);
  }

  @Override
  public void acceptIDsToTypes(Map<String, ShapeType> idsToTypes) {
    if (!Objects.nonNull(idsToTypes)) {
      throw new IllegalArgumentException("Null mapping of IDs to their types.");
    }

    this.panel.acceptIDsToTypes(idsToTypes);
  }

  /**
   * Takes in the last tick time of this animation.
   *
   * @param tick The last tick.
   */
  @Override
  public void acceptEndTick(int tick) {
    this.endTick = tick;
  }

  /**
   * This method will do nothing for this class.
   *
   * @param listener The listener to add.
   */
  @Override
  public void addKeyListener(KeyListener listener) {
    // do nothing
  }

  /**
   * This method will do nothing for this class.
   *
   * @param listener The listener to add.
   */
  @Override
  public void addActionListener(ActionListener listener) {
    // do nothing
  }

  /**
   * Plays the animation.
   */
  @Override
  public void play() {
    throw new UnsupportedOperationException();
  }

  /**
   * Pauses the animation.
   */
  @Override
  public void pause() {
    throw new UnsupportedOperationException();
  }

  /**
   * Restarts the animation from the beginning.
   */
  @Override
  public void restart() {
    throw new UnsupportedOperationException();
  }

  /**
   * Turns on the looping if it's off and vice versa.
   */
  @Override
  public void toggleLooping() {
    throw new UnsupportedOperationException();
  }

  /**
   * Increases the speed of the animation by 10 ticks per second.
   */
  @Override
  public void increaseSpeed() {
    throw new UnsupportedOperationException();
  }

  /**
   * Decreases the speed of the animation by 10 ticks per second. If decrementing makes it negative,
   * the speed becomes 1 tick per second.
   */
  @Override
  public void decreaseSpeed() {
    throw new UnsupportedOperationException();
  }

  /**
   * Puts the focus back on the main frame of the view.
   */
  @Override
  public void resetFocus() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the ID text field.
   *
   * @return the String in the ID text field.
   */
  @Override
  public String getIdField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the Shape text field.
   *
   * @return the String in the Shape text field.
   */
  @Override
  public String getShapeOption() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the tick text field.
   *
   * @return the String in the tick text field.
   */
  @Override
  public String getTickField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the x text field.
   *
   * @return the String in the x text field.
   */
  @Override
  public String getXField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the y text field.
   *
   * @return the String in the y text field.
   */
  @Override
  public String getYField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the width text field.
   *
   * @return the String in the width text field.
   */
  @Override
  public String getWidthField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the height text field.
   *
   * @return the String in the height text field.
   */
  @Override
  public String getHeightField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the red text field.
   *
   * @return the String in the red text field.
   */
  @Override
  public String getRedField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the blue text field.
   *
   * @return the String in the blue text field.
   */
  @Override
  public String getBlueField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the String in the green text field.
   *
   * @return the String in the green text field.
   */
  @Override
  public String getGreenField() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns a String describing the animation editing option selected.
   *
   * @return a String describing the animation editing option selected.
   */
  @Override
  public String getEditOption() {
    throw new UnsupportedOperationException();
  }

  /**
   * Displays the given error message.
   *
   * @param msg The message.
   */
  @Override
  public void displayErrorMessage(String msg) {
    throw new UnsupportedOperationException();
  }
}
