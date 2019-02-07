package cs3500.animation;

import java.util.Objects;

/**
 * Represents a keyframe of a cs3500.shape at the start of the event and its end.
 */
public class State {
  public final int x;
  public final int y;
  public final int width;
  public final int height;
  public final int r;
  public final int g;
  public final int b;

  /**
   * Default constructor for State.
   *
   * @param x      x coordinate
   * @param y      y coordinate
   * @param width  width of Shape
   * @param height height of Shape
   * @param r      color red
   * @param g      color green
   * @param b      color blue
   */
  public State(int x, int y, int width, int height, int r, int g, int b) {

    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Getter for the x field.
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for the y field.
   */
  public int getY() {
    return y;
  }

  /**
   * Getter for the width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Getter for the height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Getter for red.
   */
  public int getR() {
    return r;
  }

  /**
   * Getter for green.
   */
  public int getG() {
    return g;
  }

  /**
   * Getter for blue.
   */
  public int getB() {
    return b;
  }

  /**
   * Prints the information of the cs3500.shape.
   *
   * @return String containing the information of the cs3500.shape.
   */
  public String printState() {
    StringBuilder print = new StringBuilder();
    print.append(" " + this.x + " " + this.y + " " + this.width + " " +
            this.height + " " + this.r + " " + this.g + " " + this.b);

    return print.toString();
  }

  /**
   * Detects if there was a move motion.
   *
   * @return true if there is movement, false otherwise.
   */
  public boolean detectMove(State other) {
    return this.x != other.x || this.y != other.y;
  }

  /**
   * Detects if there was change of color.
   *
   * @return true if there is a change in color, false otherwise
   */
  public boolean detectColor(State other) {
    return this.r != other.r || this.b != other.b || this.g != other.g;
  }

  /**
   * Detect if there was change in size.
   *
   * @return true if there is a change in size, false otherwise.
   */
  public boolean detectSize(State other) {
    return this.width != other.width || this.height != other.height;
  }

  /**
   * Two states are equal when the fields are the same.
   *
   * @param obj compared with.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof State)) {
      return false;
    }
    State state = (State) obj;
    return state.x == this.x
            &&
            state.y == this.y
            &&
            state.height == this.height
            &&
            state.width == this.width
            &&
            state.r == this.r
            &&
            state.g == this.g
            &&
            state.b == this.b;

  }

  /**
   * Hashcode overriden with the fields of the State.
   */
  @Override
  public int hashCode() {
    return Objects.hash(x, y, height, width, r, g, b);
  }

  /**
   * Returns a deep copy of the state.
   */
  public State copyState() {
    // primitives and Strings are immutable
    return new State(this.x, this.y, this.width, this.height, this.r, this.g, this.b);
  }

}
