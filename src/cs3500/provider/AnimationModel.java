package cs3500.provider;

import java.util.List;
import java.util.Map;


/**
 * Represents shapes moving across a 2D surface. Any implementing class should throw
 * IllegalArgumentException if created with overlapping transformations. Top left of the animator is
 * location (0, 0).
 */
public interface AnimationModel {
  /**
   * Gives a full description of the transformations that occur in this animator.
   *
   * @return Description of the transformations that occur in this animator.
   */
  String description();

  /**
   * Returns a copy of all the Transformations in this Animation.
   *
   * @return a copy of all the Transformations in this Animation.
   */
  List<Transformation> getTransformations();

  /**
   * Returns a copy of a map of shape IDs to what type of shape they are.
   *
   * @return a copy of a map of shape IDs to what type of shape they are.
   */
  Map<String, ShapeType> getIDsToTypes();

  /**
   * Adds the given Transformation to this animation. If it isn't possible to add this
   * Transformation without creating an overlap, gap, or non-fluid transition, then an exception is
   * thrown.
   *
   * @param t The transformation to add.
   * @throws IllegalArgumentException if adding the given Transformation will create an overlap,
   *                                  gap, or non-fluid transition in this animation.
   */
  void addTransformation(Transformation t) throws IllegalArgumentException;

  /**
   * Adds the given shape ID along with its type to this animation only if a shape with the same
   * name does not already exist.
   *
   * @param id   The name of the shape.
   * @param type What kind of shape it is.
   * @throws IllegalArgumentException if a shape with the given id already exists in this
   *                                  animation.
   */
  void addShape(String id, ShapeType type) throws IllegalArgumentException;

  /**
   * Removes the shape with the given id completely from this animation.
   *
   * @param id The name of the shape to remove.
   */
  void removeShape(String id);

  /**
   * Adds the given keyframe of a shape to this animation.
   *
   * @param id The name of the shape.
   * @param tick  The tick to add the keyframe at.
   * @throws IllegalArgumentException if the inputs do not make sense or there is already a
   *                                  keyframe at the given time.
   */
  void addKeyFrame(String id, int tick) throws IllegalArgumentException;

  /**
   * Modifies the keyframe of a shape at the given time.
   *
   * @param id The name of the shape.
   * @param tick  The tick to add the keyframe at.
   * @param x  The x-location of the shape.
   * @param y  The y-location of the shape.
   * @param w  The width of the shape.
   * @param h  The height of the shape.
   * @param r  The red value of the shape's color.
   * @param g  The blue value of the shape's color.
   * @param b  The green value of the shape's color.
   * @throws IllegalArgumentException if the inputs do not make sense or there is no keyframe for
   *                                  the shape at the given time.
   */
  void modifyKeyFrame(String id, int tick, int x, int y, int w, int h, int r, int g, int b)
          throws IllegalArgumentException;

  /**
   * Removes the keyframe of the given shape at the given time.
   *
   * @param id The name of the shape.
   * @param tick  The tick time.
   * @throws IllegalArgumentException if the inputs are invalid or there is no keyframe for the
   *                                  shape at the given time.
   */
  void removeKeyFrame(String id, int tick) throws IllegalArgumentException;

  /**
   * Returns the time at which this animation is complete. Returns 0 if there are no
   * transformations.
   *
   * @return the time at which this animation is complete.
   */
  int getEndTick();
}
