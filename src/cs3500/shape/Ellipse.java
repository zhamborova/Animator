package cs3500.shape;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * Represents a shape that is an Ellipse.
 */
public class Ellipse extends BaseShape {

  public Ellipse(State startState, String name) {
    super(startState, name);
  }

  public Ellipse(String name) {
    super(name);
  }

  // private so its only used by formatShape
  private Ellipse(State startState, String name, ArrayList<Motion> motions) {
    super(startState, name, motions);
  }

  @Override
  public Shape formatShape(State state, String name, ArrayList<Motion> motions) {
    return new Ellipse(state, name, motions);
  }

  @Override
  public RoShape toRO() {
    return new RoEllipse(this.currentState, this.name, this.motions);
  }
}
