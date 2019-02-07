package cs3500.shape;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * Represents read only shape. Blocks off methods that can mutate a shape instead of the model.
 */
public class RoBaseShape extends BaseShape implements RoShape {

  /**
   * Default constructor for a read only base shape.
   */
  protected RoBaseShape(State currentState, String name, ArrayList<Motion> motions) {
    super(currentState, name, motions);
  }

  @Override
  protected Shape formatShape(State state, String name, ArrayList<Motion> motions) {
    throw new IllegalArgumentException("not supported");
  }

  @Override
  public void setState(State state) {
    throw new UnsupportedOperationException("can't change state");
  }

  @Override
  public void addEvent(Motion m) {
    throw new UnsupportedOperationException("can't add new motion");
  }

  @Override
  public void removeEvent(int tick) {
    throw new UnsupportedOperationException("can't remove motion");
  }

  @Override
  public void updateCurrentState(int tick) {
    throw new UnsupportedOperationException("can't update state");
  }

  @Override
  public void modifyState(int tick, State newState) {
    throw new UnsupportedOperationException("can't modify state");
  }

  @Override
  public RoShape toRO() {
    return this.toRO();
  }

  @Override
  public void addState(int tick, State newState) {
    throw new UnsupportedOperationException("can't add a new state");
  }

  @Override
  public ArrayList<Motion> getMotionsDir() {
    throw new UnsupportedOperationException("block reference");
  }

}
