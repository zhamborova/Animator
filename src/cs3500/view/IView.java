package cs3500.view;

import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import cs3500.provider.ShapeType;
import cs3500.controller.IViewListener;
import cs3500.provider.Transformation;

/**
 * The Interface for the view of an animation. Provides the methods all basic views of the animation
 * may need.
 */
public interface IView {

  /**
   * Pauses or plays the animation.
   */

  void pauseOrPlay();

  /**
   * plays the animation.
   */

  void play();

  /**
   * Pauses the animation.
   */

  void pause();

  /**
   * Exports the animation to a destination. specified in the View.
   */
  String exportAnimation();

  /**
   * Set the listener for any actions.
   */
  void setListeners(IViewListener buttons);

  /**
   * Enables or disables looping the animation
   */
  void setLoop();

  /**
   * Rewinds or plays forward the animation.
   */
  void toggleTime();

  /**
   * Adds the given amount to the speed of the animation.
   */
  void toggleSpeed(int speed);

  /**
   * Starts the animation.
   */
  void start();

  /**
   * What's the name of the shape the user wants to add?
   *
   * @return
   */
  String getAddShapeName();

  /**
   * What type of shape does the user want to add?
   *
   * @return
   */
  String getAddType();

  /**
   * Gets the name of the user selected shape.
   */
  String getSelectedShape();

  /**
   * Gets the tick for a keyframe set by a user.
   *
   * @return
   */
  int getT();

  /**
   * Gets the x value for a keyframe set by a user.
   *
   * @return
   */
  int getXPosn();

  /**
   * Gets the y value for a keyframe set by a user.
   *
   * @return
   */
  int getYPosn();

  /**
   * Gets the width for a keyframe set by a user.
   *
   * @return
   */
  int getW();

  /**
   * Gets the height for a keyframe set by a user.
   * @return
   */
  int getH();

  /**
   * Gets the red for a keyframe set by a user.
   *
   * @return
   */
  int getR();

  /**
   * Gets the green for a keyframe set by a user.
   *
   * @return
   */
  int getG();

  /**
   * Gets the blue for a keyframe by a user.
   *
   * @return
   */
  int getB();

  /**
   * Gets the tick for the keyframe that the user wants to remove.
   *
   * @return
   */
  int getTickRemove();

  /**
   * Updates the list of available shapes. Not ideal, would like a single point of control where changing the model
   * would automatically update the list. At the least the controller is acting as a single point of control?
   * Not sure if this is the best way to do this.
   *
   * @param action add or remove the shape
   * @param name name of the shape
   */
  void updateAvailableShapes(String action, String name);

  /**
   * Updates the last tick if the user adds a keyframe that extends past the current last tick.
   *
   * @param tick
   */
  void updateLastTick(int tick);

  /**
   * Gets the edit the user does.
   *
   * @return
   */
  String getEdit();

  /**
   * Takes in the transformations to display.
   * It isn't ideal that we had to add these methods to our interface. It doesn't make sense and it
   * tightly couples our interfaces. We tried extending the interface, using a decorator, but it
   * came back to having to promise the functionality here.
   * The main issue was: we passed data to the views through an immutable model. When the controller
   * made changes to the model, although the view can't make any changes, it was able to observe
   * these changes. The provider's view does not contain a ro model, but the values themselves.
   * When we adapted their Transformation with our Motion, the reference was lost.
   * Our interface needed functionality for being able to update the view after every change the
   * controller makes.
   *
   * @param transformations The transformations to display.
   */
  void acceptTransformations(List<Transformation> transformations);

  /**
   * Takes in a mapping of shape IDs to what type they are.
   *
   * It isn't ideal that we had to add these methods to our interface. It doesn't make sense and it
   * tightly couples our interfaces. We tried extending the interface, using a decorator, but it
   * came back to having to promise the functionality here.
   *
   * @param idsToTypes The mapping.
   */
  void acceptIDsToTypes(Map<String, ShapeType> idsToTypes);

  /**
   * Sets a keylistener.
   *
   * @param key
   */
  void setKeyListener(KeyListener key);

  /**
   * Resets the focus.
   */
  void setFocus();
}
