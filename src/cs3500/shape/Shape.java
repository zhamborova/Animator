package cs3500.shape;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * Represents an interface for cs3500.Shape and all methods they will have.
 */
public interface Shape {

  /**
   * Outputs the motions of a given Shape.
   *
   * @return ArrayList of motions of Shape
   */
  ArrayList<Motion> getMotions();

  /**
   * Outputs direct reference to motions.
   */

  ArrayList<Motion> getMotionsDir();

  /**
   * Adds an event to a Shape.
   *
   * @throws IllegalArgumentException if the Shape already has this event.
   */
  void addEvent(Motion e) throws IllegalArgumentException;

  /**
   * Removes the event from a Shape.
   *
   * @throws IllegalArgumentException if event does not exist. This is a stub.
   */
  void removeEvent(int tick) throws IllegalArgumentException;

  /**
   * Gets the name of a Shape.
   */
  String getName();

  /**
   * Gets the state of a Shape.
   */

  State copyState();

  /**
   * Shapes are equal if their names are the same.
   *
   * @return true if this and given obj are equal, false otherwise.
   */
  boolean equals(Object obj);

  /**
   * Overriding hashCode as we overrode equals above.
   *
   * @return hashcode of a Shape
   */
  int hashCode();

  /**
   * Gives a deep copy of a Shape.
   */
  Shape copyShape();

  /**
   * Gives a deep copy of the Motions a Shape goes through.
   */
  ArrayList<Motion> copyAllMotions();

  /**
   * Gives a String representation of the Shape object.
   */
  String printShape();

  /**
   * Gives a String representation of the Shape's motions.
   */
  String printAllMotions();

  /**
   * Outputs a read only version of the shape.
   */
  RoShape toRO();

  /**
   * Updates the current state of a shape depending on tick.
   */
  void updateCurrentState(int tick);

  /**
   * Determenise if there is a key frame at the given tick for this shape.
   */
  boolean stateExists(int tick);

  /**
   * Modifies the existing state(keyframe) of a shape at the given tick.
   *
   * @param tick     at which shape will be modified.
   * @param newState new state of a shape at that tick.
   */
  void modifyState(int tick, State newState);

  /**
   * Adds a new state for this shape.
   *
   * @param tick where the state will be added.
   */
  void addState(int tick, State newState);


}
