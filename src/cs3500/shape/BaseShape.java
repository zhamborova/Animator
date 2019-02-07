package cs3500.shape;

import java.util.ArrayList;
import java.util.Objects;

import cs3500.animation.Motion;
import cs3500.animation.State;

/**
 * Represents a Base class for Shapes. A shape will keep track of its currentState, its name and the
 * motions it will go through.
 */

public abstract class BaseShape implements Shape {
  public State currentState;
  public final String name;
  public final ArrayList<Motion> motions;

  /**
   * Default constructor for creating a BaseShape object.
   *
   * @param startState the state the Shape starts at.
   * @param name       the name of the Shape
   */
  public BaseShape(State startState, String name) {
    if (name == null || name.trim().equals("") || startState == null) {
      throw new IllegalArgumentException();
    }

    this.name = name;
    this.motions = new ArrayList<>();
  }

  /**
   * Allows the construction of a shape with a set of motions.
   *
   * @param startState the state the shape starts out in.
   * @param name       name of the shape.
   * @param motions    the motions the shape will go through.
   */
  protected BaseShape(State startState, String name, ArrayList<Motion> motions) {
    if (name == null || name.trim().equals("") || startState == null) {
      throw new IllegalArgumentException();
    }
    this.currentState = startState;
    this.name = name;
    this.motions = motions;
  }

  protected BaseShape(String name) {
    this.currentState = new State(0, 0, 0, 0, 0, 0, 0);
    this.name = name;
    this.motions = new ArrayList<>();
  }

  @Override
  public ArrayList<Motion> getMotions() {
    return this.copyAllMotions();
  }

  @Override
  public ArrayList<Motion> getMotionsDir() {
    return this.motions;
  }


  public void setState(State state) {
    this.currentState = state;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public State copyState() {
    return this.currentState.copyState();
  }

  @Override
  public void addEvent(Motion m) throws IllegalArgumentException {
    if (this.motions.contains(m)) {
      throw new IllegalArgumentException();
    }
    for (Motion motion : motions) {
      if (motion.overlapMotion(m)) {
        throw new IllegalArgumentException("overlapping motion");
      }
    }

    // if motions isn't empty, we have to check for agreement
    if (!motions.isEmpty()) {
      motions.get(motions.size() - 1).endsAgree(m);
    }
    motions.add(m);
  }

  @Override
  public void removeEvent(int tick) throws IllegalArgumentException {
    // if only one motion, get rid of entire motion
    if (motions.size() == 1) {
      motions.remove(0);
      return;
    }

    Motion first = motions.get(0);
    Motion last = motions.get(motions.size() - 1);


    // edge case removing the first keyframe
    if (first.getStart() == tick) {
      Motion next = motions.get(1);
      next.modifyStartTime(next.getEnd());
      next.modifyStart(next.getEnding());
      motions.remove(0);
      return;
    }


    // edge case removing the last keyframe
    if (last.getEnd() == tick) {
      motions.remove(motions.size() - 1);
      return;
    }

    // removing a keyframe in between first and last
    for (int i = 0; i < motions.size(); i++) {
      Motion cur = motions.get(i);
      if (cur.getStart() == tick) {
        Motion prev = motions.get(i - 1);
        cur.modifyStartTime(prev.getStart());
        cur.modifyStart(prev.getStarting());
        motions.remove(i - 1);
        return;
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Shape)) {
      return false;
    }
    BaseShape shape = (BaseShape) obj;
    return Objects.equals(shape.name, name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  /**
   * Factory method for returning the correct concrete class of BaseShape.
   *
   * @param state   state of the concrete Shape
   * @param name    name of the concrete Shape
   * @param motions the motions of the concrete Shape
   */
  protected abstract Shape formatShape(State state, String name, ArrayList<Motion> motions);

  @Override
  public Shape copyShape() {
    Shape copy = formatShape(this.currentState.copyState(), this.name, this.copyAllMotions());
    return copy;
  }

  @Override
  public ArrayList<Motion> copyAllMotions() {
    ArrayList<Motion> copy = new ArrayList<>();
    for (Motion motion : motions) {
      copy.add(motion.copyMotion());
    }

    return copy;
  }

  @Override
  public String printShape() {
    return "shape " + this.name + " "
            + this.getClass().getSimpleName().toLowerCase();
  }

  @Override
  public String printAllMotions() {
    StringBuilder printMotions = new StringBuilder();
    for (Motion motion : motions) {
      printMotions.append(motion.printMotion(this.name) + "\n");
    }
    return printMotions.toString();
  }

  /**
   * Goes through every motions of the shape and updates the current state appropriately if the
   * given tick is in range of some motion.
   */
  @Override
  public void updateCurrentState(int tick) {
    for (int i = 0; i < motions.size(); i++) {
      if (motions.get(i).getEnd() >= tick && tick >= motions.get(i).getStart()) {
        this.currentState = motions.get(i).current(tick);
      }
    }
  }

  /**
   * Checks if there is a key frame at the given tick.
   */
  @Override
  public boolean stateExists(int tick) {
    for (Motion m : motions) {
      //am i being and idiot here? I feel like im doing if true true else false lol
      if (m.stateExists(tick)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void modifyState(int tick, State newState) {
    System.out.println(this.name);
    System.out.println(newState.getX());
    for (Motion m : motions) {
      m.modifyStates(tick, newState);
    }
  }

  @Override
  abstract public RoShape toRO();

  @Override
  public void addState(int tick, State newState) {
    System.out.println(this.name);
    System.out.println(newState.getX());

    // edge case where the new keyframe is the first keyframe of the shape
    if (motions.size() == 0) {
      this.addEvent(new Motion(tick, tick, newState, newState));
      return;
    }

    Motion first = motions.get(0);
    Motion last = motions.get(motions.size() - 1);
    // using the built in add function handles shifting the motions indices

    // edgecase where the new keyframe is now the at the beginning
    if (tick < first.getStart()) {
      motions.add(0, new Motion(tick, tick, newState, newState));
      first.modifyStartTime(tick);
      first.modifyStart(newState);
      return;
    }

    // edgecase where the new keyframe is now the last keyframe
    if (tick > last.getEnd()) {
      Motion newEnd = new Motion(last.getEnd(), tick, last.getEnding(), newState);
      motions.add(newEnd);
      return;
    }

    // keyframe in between keyframes
    for (int i = 0; i < motions.size(); i++) {
      Motion m = motions.get(i);
      if (tick > m.getStart() && tick < m.getEnd()) {
        State start = m.getStarting();
        Motion newMotion = new Motion(m.getStart(), tick, start, newState);
        m.modifyStartTime(tick);
        m.modifyStart(newState);
        motions.add(i, newMotion);
        return;
      }
    }
  }

}






















