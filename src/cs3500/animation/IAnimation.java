package cs3500.animation;

import java.util.HashMap;
import java.util.List;

import cs3500.shape.Shape;

/**
 * Represents the Interface of Model for a simple Animator. It provides functionality for
 * manipulating the model by adding and retrieving data. This interface allows mutation of the
 * Model.
 */
public interface IAnimation {

  /**
   * Adds a Shape into Animation.
   *
   * @param name name of the Shape.
   * @param type type of the Shape.
   * @throws IllegalArgumentException the Shape with the same name already exists
   */
  void addShape(String name, String type) throws IllegalArgumentException;

  /**
   * Takes in the data of a motion, creates it then assigns it to the shape with the given name.
   *
   * @param name of the shape
   * @param t1   start time.
   * @param x1   starting x pos.
   * @param y1   starting y pos.
   * @param w1   starting width.
   * @param h1   starting height.
   * @param r1   starting red color.
   * @param g1   starting green color.
   * @param b1   starting blue color.
   * @param t2   ending time.
   * @param x2   ending x pos.
   * @param y2   ending y pos.
   * @param w2   ending width.
   * @param h2   ending height.
   * @param r2   ending red color.
   * @param g2   ending green color.
   * @param b2   ending blue color.
   */
  void addEvent(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
          throws IllegalArgumentException;

  /**
   * Removes the given Motion from the given Shape.
   *
   * @param name name of the Shape
   * @param tick keyframe to remove
   * @throws IllegalArgumentException the given Motion or Shape do not exist or passed Motion or
   *                                  cs3500.Shape are null. We are not sure if this is going to be
   *                                  legal so we just leaving a stub for this homework.
   */
  void removeEvent(String name, int tick) throws IllegalArgumentException;

  /**
   * Removes the shape with the given name.
   *
   * @param name of the shape
   */
  void removeShape(String name);


  /**
   * Gives a String representation of the Animation.
   */
  String printAnimationState();

  /**
   * Returns a deep copy of the hashmap of where keys are names of the shapes and values are
   * shapes.
   *
   * @return the hashmap with the Shapes assign to their String name
   * @throws IllegalArgumentException if the name is not valid or not found
   */
  HashMap<String, Shape> getAnimations() throws IllegalArgumentException;

  /**
   * Returns a direct reference to the animations.
   *
   * @return
   */
  HashMap<String, Shape> getAnimationsDir() throws IllegalArgumentException;

  /**
   * Direct reference to the motions of the given shape.
   *
   * @param shape given
   * @return
   * @throws IllegalArgumentException
   */
  List<Motion> getAllMotionsDir(String shape) throws IllegalArgumentException;

  /**
   * Returns a deep copy of the Shape object with the given name.
   *
   * @param name the name of the shape
   * @throws IllegalArgumentException if the name is not valid or found
   */
  Shape getShape(String name) throws IllegalArgumentException;

  /**
   * Returns a deep copy of the motions a Shape goes through.
   *
   * @param name the name of the Shape
   * @throws IllegalArgumentException if the name is not valid or not found
   */
  List<Motion> getAllMotions(String name) throws IllegalArgumentException;

  /**
   * Gets the dimensions of the canvas for the exportAnimation from the model.
   */
  int[] getCanvas();

  /**
   * Changes an Animation to a read only animation. All it's really doing is just "casting". The new
   * read only animation will have references to the original model, but any methods that can mutate
   * the model will be blocked off.
   */
  RoIAnimation toRO();

  /**
   * Getter for x field.
   *
   * @return this.x.
   */
  int getX();

  /**
   * Getter for y field.
   *
   * @return this.y.
   */
  int getY();

  /**
   * Getter for  width field.
   *
   * @return this.width.
   */
  int getWidth();

  /**
   * Getter for height field.
   *
   * @return this.height.
   */
  int getHeight();


  /**
   * Finds the tick of the last motion animated in the entire animation.
   */
  int lastTick();

  /**
   * If a keyframe exists at the given tick, the frame is modified with the given parameters. If it
   * doesn't exist a new frame with the given parameters is added.
   *
   * @param shapeName name of the given shape
   * @param tick      tick at which shape will be modified
   */
  void addModifyFrame(String shapeName, int tick, int x, int y, int w, int h, int r, int b, int g);

  /**
   * Returns a list of all shapes.
   *
   * @return
   */
  List<Shape> getShapes();
}




















