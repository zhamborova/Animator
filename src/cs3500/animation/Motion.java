package cs3500.animation;

/**
 * Represents a class of changes that could be applied to shapes. Holds the duration of the change
 * and the changes made.
 */
public class Motion {
  private int start;
  private int end;
  private State starting;
  private State ending;
  private String name;

  /**
   * Default constructor for an Motion.
   *
   * @param start    start tick
   * @param end      end tick
   * @param starting state at start
   * @param ending   state at end
   */
  public Motion(int start, int end, State starting, State ending) {
    if (starting == null || ending == null || end < start || start < 0 || end < 0) {
      throw new IllegalArgumentException();
    }
    this.start = start;
    this.end = end;
    this.starting = starting;
    this.ending = ending;

  }

  /**
   * Gives a deep copy of the motion.
   */
  public Motion copyMotion() {
    Motion copy = new Motion(this.start, this.end, starting.copyState(), ending.copyState());
    return copy;
  }

  /**
   * Gets the start field of the Motion.
   *
   * @return this.start
   */
  public int getStart() {
    return this.start;
  }

  /**
   * Gets the end field of the Motion.
   *
   * @return this.end
   */
  public int getEnd() {

    return this.end;
  }

  /**
   * Gets the starting field of the Motion.
   *
   * @return this.starting
   */
  public State getStarting() {

    return this.starting;
  }

  /**
   * Gets the ending field of the Motion.
   *
   * @return this.ending
   */
  public State getEnding() {

    return this.ending;
  }

  /**
   * Stores the information of a Shape for the text output.
   *
   * @param one   is the starting state.
   * @param two   is the ending state.
   * @param start is the starting tick.
   * @param end   is the ending tick.
   */
  public void store(State one, State two, int start, int end) {
    starting = one;
    ending = two;
    this.start = start;
    this.end = end;
  }

  /**
   * Checks if two motions overlap in time.
   *
   * @param other is given event
   * @return True if they overlap, false otherwise.
   */
  public boolean overlapTime(Motion other) {
    return (this.start < other.start && other.start < this.end) || (this.start < other.end &&
            other.end < this.end);
  }


  /**
   * Checks if the is an overlapping motion between two motions.
   *
   * @return true if there is an overlapping motion during overlapping time, false otherwise.
   */
  public boolean overlapMotion(Motion other) {
    if (this.overlapTime(other)) {
      return ((this.detectMove() && other.detectMove()) ||
              (this.detectColor() && other.detectColor()) ||
              (this.detectSize() && other.detectSize()));
    }
    return false;
  }

  /**
   * Detects if there was a move motion.
   *
   * @return true if there is movement, false otherwise.
   */
  public boolean detectMove() {
    return starting.detectMove(ending);
  }

  /**
   * Detects if there was change of color.
   *
   * @return true if there is a change in color, false otherwise
   */
  public boolean detectColor() {
    return starting.detectColor(ending);
  }

  /**
   * Detectc if there was change in size.
   *
   * @return true if there is a change in size, false otherwise.
   */
  public boolean detectSize() {
    return starting.detectSize(ending);
  }

  /**
   * Provides a text description of a Shape's motion.
   *
   * @param name the name of the shape with the motion
   */
  public String printMotion(String name) {
    StringBuilder printMotion = new StringBuilder();
    printMotion.append("motion " + name + " " + Integer.toString(start) + " " +
            starting.printState() + "   " + Integer.toString(end) + ending.printState());
    return printMotion.toString();
  }

  /**
   * Checks if the endpoints of motions agree.
   */
  public void endsAgree(Motion other) throws IllegalArgumentException {
    if (this.end != other.start) {
      throw new IllegalArgumentException("Gap in time");
    }
    if (this.ending.detectSize(other.starting) || this.ending.detectMove(other.starting)
            || this.ending.detectSize(other.starting)) {
      throw new IllegalArgumentException("ends do not agree");
    }
  }


  /**
   * Computes the next value of any field of a state with given tweening formula.
   *
   * @return next value of some of State's fields.
   */
  private int nextValue(int tick, int start, int end) {

    int t1 = this.start;
    int t2 = this.end;
    int d = t2 - t1;
    // can't divide by zero
    if (d == 0) {
      d = 1;
    }
    if (start != end) {
      int result = start * (t2 - tick) / d + end * (tick - t1) / d;
      return result;
    } else {
      return end;
    }
  }

  /**
   * Computes the state of a shape in the given tick.
   *
   * @param tick current tick
   * @return next State.
   */
  public State current(int tick) {
    int x = nextValue(tick, starting.x, ending.x);
    int y = nextValue(tick, starting.y, ending.y);
    int w = nextValue(tick, starting.width, ending.width);
    int h = nextValue(tick, starting.height, ending.height);
    int r = nextValue(tick, starting.r, ending.r);
    int g = nextValue(tick, starting.g, ending.g);
    int b = nextValue(tick, starting.b, ending.b);

    return new State(x, y, w, h, r, g, b);
  }

  /**
   * Checks if a keyframe at the given tick exists.
   */
  public boolean stateExists(int tick) {
    return (this.getEnd() == tick || this.getStart() == tick);
  }

  /**
   * Modifies the start state of the motion to the given state.
   *
   * @param state new state
   */
  public void modifyStart(State state) {
    this.starting = state;
  }

  /**
   * Modifies the start time of the motion to the given tick.
   *
   * @param tick new start time
   */
  public void modifyStartTime(int tick) {
    this.start = tick;
  }

  /**
   * Modifies the states with the same time as the given tick.
   *
   * @param tick     given tick
   * @param newState new state if ticks are the same
   */
  public void modifyStates(int tick, State newState) {
    if (start == tick) {
      starting = newState;
    }
    if (end == tick) {
      ending = newState;
    }
  }
}











