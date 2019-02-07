import org.junit.Test;

import cs3500.animation.State;

import static org.junit.Assert.assertEquals;

public class StateTest {

  State state = new State(10, 10, 5, 5, 255, 0, 0);


  // tests print state
  @Test
  public void printState() {
    assertEquals(" 10 10 5 5 255 0 0", state.printState());
  }

  // tests if x changes
  @Test
  public void changeX() {
    State change = new State(12, 10, 5, 5, 255, 0, 0);
    assertEquals(true, change.detectMove(state));
  }

  // tests if y changes
  @Test
  public void changeY() {
    State change = new State(10, 12, 5, 5, 255, 0, 0);
    assertEquals(true, change.detectMove(state));
  }


  // tests if r changes
  @Test
  public void changeR() {
    State change = new State(10, 10, 5, 5, 250, 0, 0);
    assertEquals(true, change.detectColor(state));
  }


  // tests if g changes
  @Test
  public void changeG() {
    State change = new State(10, 10, 5, 5, 255, 10, 0);
    assertEquals(true, change.detectColor(state));
  }

  // tests if b changes
  @Test
  public void changeB() {
    State change = new State(10, 10, 5, 5, 255, 0, 10);
    assertEquals(true, change.detectColor(state));
  }


  // tests if width changes
  @Test
  public void changeWidth() {
    State change = new State(10, 10, 15, 5, 255, 0, 0);
    assertEquals(true, change.detectSize(state));
  }


  // tests if height changes
  @Test
  public void changeHeight() {
    State change = new State(10, 10, 5, 21, 255, 0, 0);
    assertEquals(true, change.detectSize(state));
  }

  // tests for no move
  @Test
  public void noMove() {
    State change = new State(10, 10, 15, 211, 255, 0, 0);
    assertEquals(false, change.detectMove(state));
  }


  // tests for no color change
  @Test
  public void noColorChange() {
    State change = new State(15, 15, 152, 211, 255, 0, 0);
    assertEquals(false, change.detectColor(state));
  }

  // tests for no resize
  @Test
  public void noResize() {
    State change = new State(12, 12, 5, 5, 255, 24, 0);
    assertEquals(false, change.detectSize(state));
  }

  // tests equals with different objects
  @Test
  public void diffObj() {
    assertEquals(false, "".equals(state));
  }

  // test.txt equals return true
  @Test
  public void equalStates() {
    State other = new State(10, 10, 5, 5, 255, 0, 0);
    assertEquals(true, other.equals(state));
  }

  // tests equals with different values
  @Test
  public void diffStates() {
    State x = new State(11, 10, 5, 5, 255, 0, 0);
    State y = new State(10, 12, 5, 5, 255, 0, 0);
    State width = new State(10, 10, 5232, 5, 255, 0, 0);
    State height = new State(10, 10, 5, 115, 255, 0, 0);
    State r = new State(10, 10, 5, 5, 50, 0, 0);
    State g = new State(10, 10, 5, 5, 255, 215, 0);
    State b = new State(10, 10, 5, 5, 255, 0, 76);
    assertEquals(false, x.equals(state));
    assertEquals(false, y.equals(state));
    assertEquals(false, width.equals(state));
    assertEquals(false, height.equals(state));
    assertEquals(false, r.equals(state));
    assertEquals(false, g.equals(state));
    assertEquals(false, b.equals(state));
  }

  // test.txt make sure negative values cant be used in a state constructor

}