import org.junit.Test;

import java.util.ArrayList;

import cs3500.animation.Motion;
import cs3500.animation.State;
import cs3500.shape.Ellipse;
import cs3500.shape.Rect;
import cs3500.shape.RoShape;
import cs3500.shape.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BaseShapeTest {


  Shape r = new Rect(new State(100, 300, 2, 3, 255, 0, 0), "R");
  Shape c =
          new Ellipse(new State(100, 100, 2, 3, 0, 255, 0), "C");
  ArrayList<Motion> empty = new ArrayList<>();


  // makes sure getMotions returns an empty list if no motions were added
  @Test
  public void emptyEvents() {
    assertEquals(empty, r.getMotions());
  }

  @Test
  public void getName() {
    assertEquals("R", r.getName());
  }

  // tests that cs3500.Shape constructor throws an exception for a null as a string
  @Test
  public void nullName() {
    try {
      new Rect(new State(1, 1, 1, 1, 1, 1, 1), null);
      fail("No exception found");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // tests that cs3500.Shape constructor throws an exception for an empty name
  @Test
  public void emptyName() {
    try {
      new Rect(new State(1, 1, 1, 1, 1, 1, 1), "");
      fail("No exception found");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // tests that cs3500.Shape constructor throws an exception for a whitespace name
  @Test
  public void whiteSpaceName() {
    try {
      new Rect(new State(1, 1, 1, 1, 1, 1, 1), "         ");
      fail("No exception found");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // tests that cs3500.Shape constructor throws an exception for a new line name
  @Test
  public void newLineName() {
    try {
      new Rect(new State(1, 1, 1, 1, 1, 1, 1), "\n");
      fail("No exception found");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // tests endpoint function through addevent
  @Test
  public void endsDontAgree() {
    State one = new State(1, 1, 1, 1, 1, 1, 1);
    State two = new State(10, 10, 1, 1, 1, 1, 1);
    State wrong = new State(11, 10, 3, 3, 3, 3, 3);
    Motion start = new Motion(1, 10, one, two);
    Motion end = new Motion(10, 15, wrong, two);
    r.addEvent(start);
    try {
      r.addEvent(end);
      fail("exception not found");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // if ends agree the new event should be added
  // confirm this by checking the size of the list of motions
  @Test
  public void EndsAgree() {
    State one = new State(1, 1, 1, 1, 1, 1, 1);
    State two = new State(10, 10, 1, 1, 1, 1, 1);
    State last = new State(15, 10, 1, 1, 1, 1, 1);
    Motion start = new Motion(1, 10, one, two);
    Motion end = new Motion(10, 15, two, last);
    assertEquals(0, r.getMotions().size());
    r.addEvent(start);
    assertEquals(1, r.getMotions().size());
    r.addEvent(end);
    assertEquals(2, r.getMotions().size());
  }

  // different shapes same names should be considered equal
  @Test
  public void diffShapeSameName() {
    Shape r =
            new Rect(new State(100, 300, 2, 3, 255, 0, 0), "R");
    Shape c =
            new Ellipse(new State(100, 100, 2, 3, 0, 255, 0), "R")
            ;
    assertEquals(true, c.equals(r));
  }

  // if the names are the name should be considered equal
  @Test
  public void sameShapeSameName() {
    Shape r = new Rect(new State(100, 300, 2, 3, 255, 0, 0), "R");
    Shape c =
            new Ellipse(new State(100, 100, 2, 3, 0, 255, 0), "R")
            ;
    assertEquals(true, c.equals(r));
  }

  // same cs3500.shape but different names are not equal
  @Test
  public void sameShapeDiffName() {
    Shape e = new Rect(new State(100, 300, 2, 3, 255, 0, 0), "E");
    assertEquals(false, e.equals(r));
  }

  // same cs3500.shape, same fields, but different name should return false
  @Test
  public void sameFieldsDiffName() {
    Shape e = new Rect(new State(100, 300, 2, 3, 255, 0, 0), "E");
    assertEquals(false, e.equals(r));
  }

  // different cs3500.shape, same fields, different name, should return false
  @Test
  public void diffShapeDiffFieldsSameName() {
    Shape ellipse =
            new Ellipse(new State(100, 300, 2, 3, 255, 0, 0), "E")
            ;
    assertEquals(false, ellipse.equals(r));
  }

  // tests if equals returns false for different objects
  @Test
  public void diffObjects() {
    State state = new State(1, 1, 1, 1, 1, 1, 1);
    assertEquals(false, state.equals(r));
  }

  // tests that a read only shape is returned
  @Test
  public void toRO() {
    Shape r = new Rect("R");
    assertEquals(false, r instanceof RoShape);
    assertEquals(true, r.toRO() instanceof RoShape);
  }

  // tests if the shape is in the correct state at the given tick
  @Test
  public void tweenShape() {
    Shape r = new Rect("R");
    State before = ((Rect) r).currentState;
    assertEquals(0, before.getX());
    assertEquals(0, before.getY());
    assertEquals(0, before.getWidth());
    assertEquals(0, before.getHeight());
    assertEquals(0, before.getR());
    assertEquals(0, before.getG());
    assertEquals(0, before.getB());

    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State other = new State(50, 100, 50, 50, 255, 0, 0);
    Motion motion = new Motion(1, 20, start, end);
    Motion motion2 = new Motion(20, 30, end, other);

    r.addEvent(motion);
    r.addEvent(motion2);

    r.updateCurrentState(10);
    State after = ((Rect) r).currentState;

    assertEquals(10, after.getX());
    assertEquals(23, after.getY());
    assertEquals(50, after.getWidth());
    assertEquals(50, after.getHeight());
    assertEquals(134, after.getR());
    assertEquals(120, after.getG());
    assertEquals(0, after.getB());
  }

  // tests if a keyframe at the given tick exists in the shape
  @Test
  public void stateExists() {
    Shape r = new Rect("R");
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State other = new State(50, 100, 50, 50, 255, 0, 0);
    Motion motion = new Motion(1, 20, start, end);
    Motion motion2 = new Motion(20, 30, end, other);

    r.addEvent(motion);
    r.addEvent(motion2);

    assertEquals(false, r.stateExists(3));
    assertEquals(false, r.stateExists(12));
    assertEquals(true, r.stateExists(20));

  }

  // tests if the keyframe at the right tick is modified
  @Test
  public void modifyState() {
    Shape r = new Rect("R");
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State other = new State(50, 100, 50, 50, 255, 0, 0);
    Motion motion = new Motion(1, 20, start, end);
    Motion motion2 = new Motion(20, 30, end, other);

    State mod = new State(10, 10, 10, 10, 10, 10, 10);

    r.addEvent(motion);
    r.addEvent(motion2);

    assertEquals(false, mod.equals(r.getMotions().get(0).getEnding()));
    assertEquals(false, mod.equals(r.getMotions().get(1).getStarting()));

    r.modifyState(20, mod);

    assertEquals(true, mod.equals(r.getMotions().get(0).getEnding()));
    assertEquals(true, mod.equals(r.getMotions().get(1).getStarting()));

  }

  // tests adding a new keyframe
  @Test
  public void newFrameBetween() {
    Shape r = new Rect("R");
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State other = new State(50, 100, 50, 50, 255, 0, 0);
    Motion motion = new Motion(1, 20, start, end);
    Motion motion2 = new Motion(20, 30, end, other);

    State mod = new State(10, 10, 10, 10, 10, 10, 10);

    r.addEvent(motion);
    r.addEvent(motion2);

    assertEquals(2, r.getMotions().size());

    assertEquals(true, 20 == r.getMotions().get(0).getEnd());
    assertEquals(true, end.equals(r.getMotions().get(0).getEnding()));
    assertEquals(true, 20 == r.getMotions().get(1).getStart());
    assertEquals(true, end.equals(r.getMotions().get(1).getStarting()));
    r.addState(15, mod);
    assertEquals(3, r.getMotions().size());
    assertEquals(true, 15 == r.getMotions().get(0).getEnd());
    assertEquals(true, mod.equals(r.getMotions().get(0).getEnding()));
    assertEquals(true, 15 == r.getMotions().get(1).getStart());
    assertEquals(true, mod.equals(r.getMotions().get(1).getStarting()));
  }

  // tests when the frame is added at the very beginning of the motions of a shape
  @Test
  public void newFrameAtBeginning() {
    Shape r = new Rect("R");
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State other = new State(50, 100, 50, 50, 255, 0, 0);
    Motion motion = new Motion(10, 20, start, end);
    Motion motion2 = new Motion(20, 30, end, other);

    State mod = new State(10, 10, 10, 10, 10, 10, 10);

    r.addEvent(motion);
    r.addEvent(motion2);

    assertEquals(2, r.getMotions().size());

    assertEquals(true, 10 == r.getMotions().get(0).getStart());
    assertEquals(true, start.equals(r.getMotions().get(0).getStarting()));
    assertEquals(true, 20 == r.getMotions().get(0).getEnd());
    assertEquals(true, end.equals(r.getMotions().get(0).getEnding()));
    r.addState(5, mod);
    assertEquals(3, r.getMotions().size());
    assertEquals(true, 5 == r.getMotions().get(0).getStart());
    assertEquals(true, mod.equals(r.getMotions().get(0).getStarting()));
    assertEquals(true, 5 == r.getMotions().get(0).getEnd());
    assertEquals(true, mod.equals(r.getMotions().get(0).getEnding()));
  }

  // tests when the frame is added to the very end of the list of motions
  @Test
  public void newFrameAtEnd() {
    Shape r = new Rect("R");
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State other = new State(50, 100, 50, 50, 255, 0, 0);
    Motion motion = new Motion(10, 20, start, end);
    Motion motion2 = new Motion(20, 30, end, other);

    State mod = new State(10, 10, 10, 10, 10, 10, 10);

    r.addEvent(motion);
    r.addEvent(motion2);

    assertEquals(2, r.getMotions().size());

    int i = r.getMotions().size() - 1;

    assertEquals(true, 20 == r.getMotions().get(i).getStart());
    assertEquals(true, end.equals(r.getMotions().get(i).getStarting()));
    assertEquals(true, 30 == r.getMotions().get(i).getEnd());
    assertEquals(true, other.equals(r.getMotions().get(i).getEnding()));
    r.addState(50, mod);

    int i2 = r.getMotions().size() - 1;
    assertEquals(3, r.getMotions().size());
    assertEquals(true, 30 == r.getMotions().get(i2).getStart());
    assertEquals(true, other.equals(r.getMotions().get(i2).getStarting()));
    assertEquals(true, 50 == r.getMotions().get(i2).getEnd());
    assertEquals(true, mod.equals(r.getMotions().get(i2).getEnding()));
  }

}