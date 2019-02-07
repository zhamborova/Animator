import org.junit.Test;

import cs3500.animation.Motion;
import cs3500.animation.State;
import cs3500.shape.Rect;
import cs3500.shape.Shape;

import static org.junit.Assert.assertEquals;

public class MotionTest {

  Shape r = new Rect(new State(100, 300, 2, 3, 255, 0, 0), "R");
  State start = new State(100, 300, 2, 3, 3, 3, 3);
  State end = new State(200, 300, 2, 3, 3, 3, 3);
  Motion e = new Motion(1, 10, start, end);

  // tests get start
  @Test
  public void getStart() {

    State start = new State(100, 300, 2, 3, 3, 3, 3);
    State end = new State(200, 300, 2, 3, 3, 3, 3);
    Motion e = new Motion(1, 10, start, end);

    assertEquals(e.getStart(), 1);
    assertEquals(e.getEnd(), 10);
    assertEquals(e.getStarting(), start);
    assertEquals(e.getEnding(), end);

  }

  // tests store
  @Test
  public void store() {
    State start = new State(100, 300, 2, 3, 3, 3, 3);
    State end = new State(200, 300, 2, 3, 3, 3, 3);
    State start1 = new State(100, 300, 2, 3, 3, 3, 3);
    State end1 = new State(150, 400, 9, 3, 3, 2, 2);
    Motion e = new Motion(1, 10, start, end);
    Motion e1 = new Motion(10, 20, start1, end1);
    e.store(e1.getStarting(), e1.getEnding(), e1.getStart(), e1.getEnd());
    assertEquals(e.getStarting(), e1.getStarting());
    assertEquals(e.getEnding(), e1.getEnding());
    assertEquals(e.getStart(), e1.getStart());
    assertEquals(e.getEnd(), e1.getEnd());

  }

  // tests overlap time
  @Test
  public void overlapTime() {
    State start = new State(100, 300, 2, 3, 3, 3, 3);
    State end = new State(200, 300, 2, 3, 3, 3, 3);
    State start1 = new State(100, 300, 2, 3, 3, 3, 3);
    State end1 = new State(150, 400, 9, 3, 3, 2, 2);
    State start2 = new State(100, 300, 2, 3, 3, 3, 3);
    State end2 = new State(150, 400, 9, 3, 3, 2, 2);

    Motion e = new Motion(1, 10, start, end);
    Motion e1 = new Motion(10, 20, start1, end1);
    Motion e2 = new Motion(11, 19, start1, end1);

    assertEquals(false, e.overlapTime(e1));
    assertEquals(true, e1.overlapTime(e2));

  }

  // tests get motions
  @Test
  public void getEvents() {
    assertEquals(0, r.getMotions().size());
    String s = "";
    try {
      Motion ev =
              new Motion(0, 0, null, new State(3, 3, 3, 3, 3,
              3, 3));
    } catch (IllegalArgumentException e) {
      s = "worked";
    }

    assertEquals("worked", s);


    try {
      Motion ev = new Motion(0, 0, new State(3, 3, 3, 3, 3,
              3, 3), null);
    } catch (IllegalArgumentException e) {
      s = "worked1";
    }

    assertEquals("worked1", s);

    Shape r = new Rect(new State(100, 300, 2, 3, 255, 0, 0), "R");
    r.addEvent(new Motion(3, 3, new State(3, 3, 3, 3, 3,
            3, 3), new State(3, 3, 3, 3, 3,
            3, 3)));
    assertEquals(1, r.getMotions().size());
  }

  //start null
  @Test(expected = IllegalArgumentException.class)
  public void nullStart() {
    new Motion(1, 5, null, end);
  }

  //end null
  @Test(expected = IllegalArgumentException.class)
  public void nullEnd() {
    new Motion(1, 5, start, null);
  }

  //neg Start time
  @Test(expected = IllegalArgumentException.class)
  public void negStart() {
    new Motion(-1, 5, start, end);
  }

  //neg End time
  @Test(expected = IllegalArgumentException.class)
  public void negEnd() {
    new Motion(1, -5, start, end);
  }

  //end before start
  @Test(expected = IllegalArgumentException.class)
  public void endBeforeStart() {
    new Motion(12, 10, start, end);
  }

  //both neg and end before start
  @Test(expected = IllegalArgumentException.class)
  public void negativeBefore() {
    new Motion(-50, -30, start, end);
  }

  //tests tweening
  @Test
  public void tween() {
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    Motion motion = new Motion(1, 20, start, end);

    State tween = motion.current(10);

    assertEquals(10, tween.getX());
    assertEquals(23, tween.getY());
    assertEquals(50, tween.getWidth());
    assertEquals(50, tween.getHeight());
    assertEquals(134, tween.getR());
    assertEquals(120, tween.getG());
    assertEquals(0, tween.getB());
  }

  //tests tweening
  @Test
  public void tweenZero() {
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    Motion motion = new Motion(20, 20, start, end);

    State tween = motion.current(20);

    assertEquals(10, tween.getX());
    assertEquals(0, tween.getY());
    assertEquals(50, tween.getWidth());
    assertEquals(50, tween.getHeight());
    assertEquals(0, tween.getR());
    assertEquals(0, tween.getG());
    assertEquals(0, tween.getB());
  }

  // tests if the state exists in the motion
  @Test
  public void stateExists() {
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    Motion sameTime = new Motion(20, 20, start, end);
    Motion motion = new Motion(10, 20, start, end);

    assertEquals(true, sameTime.stateExists(20));
    assertEquals(false, motion.stateExists(12));
    assertEquals(false, motion.stateExists(1));
    assertEquals(false, motion.stateExists(25));
    assertEquals(true, motion.stateExists(20));
  }

  // tests if the start time is correctly modified
  @Test
  public void modifyStartTime() {
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    Motion motion = new Motion(1, 20, start, end);

    motion.modifyStart(end);

    assertEquals(true, end.equals(motion.getStarting()));
  }

  // tests if the correct state is modified based on tick
  @Test
  public void modifyStates() {
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State newStart = new State(10, 50, 50, 50, 0, 0, 255);
    Motion motion = new Motion(1, 20, start, end);

    assertEquals(false, motion.getStarting().equals(newStart));
    assertEquals(false, motion.getEnding().equals(newStart));
    motion.modifyStates(1, newStart);
    assertEquals(true, motion.getStarting().equals(newStart));
    assertEquals(false, motion.getEnding().equals(newStart));
  }

  // tests if both states are modified when the ticks are the same
  @Test
  public void modifyBothStates() {
    State start = new State(10, 10, 50, 50, 255, 0, 0);
    State end = new State(10, 40, 50, 50, 0, 255, 0);
    State newStart = new State(10, 50, 50, 50, 0, 0, 255);
    Motion motion = new Motion(20, 20, start, end);

    assertEquals(false, motion.getStarting().equals(newStart));
    assertEquals(false, motion.getEnding().equals(newStart));
    motion.modifyStates(20, newStart);
    assertEquals(true, motion.getStarting().equals(newStart));
    assertEquals(true, motion.getEnding().equals(newStart));
  }
}