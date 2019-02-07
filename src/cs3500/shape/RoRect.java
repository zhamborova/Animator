package cs3500.shape;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * A read only version of rectangle.
 */
public class RoRect extends RoBaseShape {

  /**
   * Default constructor for a read only rectangle.
   *
   * @param currentState
   * @param name
   * @param motions
   */
  protected RoRect(State currentState, String name, ArrayList<Motion> motions) {
    super(currentState, name, motions);
  }
}
