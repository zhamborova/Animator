package cs3500.shape;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * Represents a shape that is a Rectangle.
 */
public class Rect extends BaseShape {

  public Rect(State currentState, String name) {
    super(currentState, name);
  }

  public Rect(String name) {
    super(name);
  }

  // private so its only used by formatShape
  private Rect(State currentState, String name, ArrayList<Motion> motions) {
    super(currentState, name, motions);
  }

  @Override
  public Shape formatShape(State state, String name, ArrayList<Motion> motions) {
    return new Rect(state, name, motions);
  }

  @Override
  public RoShape toRO() {
    return new RoRect(this.currentState, this.name, this.motions);
  }

}
