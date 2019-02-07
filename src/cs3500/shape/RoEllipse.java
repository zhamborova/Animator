package cs3500.shape;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * A read only version of an ellipse.
 */
public class RoEllipse extends RoBaseShape {

  /**
   * Default constructor for a read only ellipse.
   *
   * @param currentState
   * @param name
   * @param motions
   */
  protected RoEllipse(State currentState, String name, ArrayList<Motion> motions) {
    super(currentState, name, motions);
  }

}
