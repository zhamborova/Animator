package cs3500.provider;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

/**
 * Represents a way to display an animator.
 */
public interface AnimationView {
  /**
   * Renders this view in some way.
   */
  void draw();

  /**
   * Takes in the transformations to display.
   *
   * @param transformations The transformations to display.
   */
  void acceptTransformations(List<Transformation> transformations);

  /**
   * Takes in a mapping of shape IDs to what type they are.
   *
   * @param idsToTypes The mapping.
   */
  void acceptIDsToTypes(Map<String, ShapeType> idsToTypes);

  /**
   * Takes in the last tick time of this animation.
   *
   * @param tick The last tick.
   */
  void acceptEndTick(int tick);

  /**
   * Adds a listener for key events that happen in this view. Since implementing classes will
   * probably extend JFrames, there will already be an implementation for this method.
   *
   * @param listener The listener to add.
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds a listener for action events that happen in this view.
   *
   * @param listener The listener to add.
   */
  void addActionListener(ActionListener listener);

  /**
   * Plays the animation.
   */
  void play();

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Restarts the animation from the beginning.
   */
  void restart();

  /**
   * Turns on the looping if it's off and vice versa.
   */
  void toggleLooping();

  /**
   * Increases the speed of the animation by 10 ticks per second.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of the animation by 10 ticks per second. If decrementing makes it negative,
   * the speed becomes 1 tick per second.
   */
  void decreaseSpeed();

  /**
   * Puts the focus back on the main frame of the view.
   */
  void resetFocus();

  /**
   * Returns the String in the ID text field.
   *
   * @return the String in the ID text field.
   */
  String getIdField();

  /**
   * Returns the String in the Shape text field.
   *
   * @return the String in the Shape text field.
   */
  String getShapeOption();

  /**
   * Returns the String in the tick text field.
   *
   * @return the String in the tick text field.
   */
  String getTickField();

  /**
   * Returns the String in the x text field.
   *
   * @return the String in the x text field.
   */
  String getXField();

  /**
   * Returns the String in the y text field.
   *
   * @return the String in the y text field.
   */
  String getYField();

  /**
   * Returns the String in the width text field.
   *
   * @return the String in the width text field.
   */
  String getWidthField();

  /**
   * Returns the String in the height text field.
   *
   * @return the String in the height text field.
   */
  String getHeightField();

  /**
   * Returns the String in the red text field.
   *
   * @return the String in the red text field.
   */
  String getRedField();

  /**
   * Returns the String in the blue text field.
   *
   * @return the String in the blue text field.
   */
  String getBlueField();

  /**
   * Returns the String in the green text field.
   *
   * @return the String in the green text field.
   */
  String getGreenField();

  /**
   * Returns a String describing the animation editing option selected.
   *
   * @return a String describing the animation editing option selected.
   */
  String getEditOption();

  /**
   * Displays the given error message.
   *
   * @param msg The message.
   */
  void displayErrorMessage(String msg);
}
