package cs3500.provider;

import java.awt.Color;



/**
 * Represents an operation that can be performed on a ShapeModel.
 */
public interface Transformation {
  /**
   * Returns the clock time that this transformation beings in an animator.
   *
   * @return the clock time that this transformation beings in an animator.
   */
  int startTick();

  /**
   * Returns the clock time that this transformation ends in an animator.
   *
   * @return the clock time that this transformation ends in an animator.
   */
  int endTick();

  /**
   * Returns information about what position, dimensions, and color a ShapeModel should have by the
   * time this Transformation is completely done.
   *
   * @return the information about the ShapeModel when this Transformation is complete.
   */
  String description();

  /**
   * Returns the starting location of the target shape.
   *
   * @return the starting location of the target shape.
   */
  ImmutablePoint getStartLocation();

  /**
   * Returns the starting width of the target shape.
   *
   * @return the starting width of the target shape.
   */
  int getStartWidth();

  /**
   * Returns the starting height of the target shape.
   *
   * @return the starting height of the target shape.
   */
  int getStartHeight();

  /**
   * Returns the starting color of the target shape.
   *
   * @return the starting color of the target shape.
   */
  Color getStartColor();

  /**
   * Returns the end location of the target shape.
   *
   * @return the end location of the target shape.
   */
  ImmutablePoint getEndLocation();

  /**
   * Returns the end width of the target shape.
   *
   * @return the end width of the target shape.
   */
  int getEndWidth();

  /**
   * Returns the end height of the target shape.
   *
   * @return the end height of the target shape.
   */
  int getEndHeight();

  /**
   * Returns the end color of the target shape.
   *
   * @return the end color of the target shape.
   */
  Color getEndColor();

  /**
   * Returns the ID of the target shape.
   *
   * @return the ID of the target shape.
   */
  String getID();
}
