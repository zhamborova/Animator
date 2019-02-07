import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import cs3500.animation.Animation;
import cs3500.animation.IAnimation;
import cs3500.animation.Motion;
import cs3500.animation.RoIAnimation;
import cs3500.animation.State;
import cs3500.animator.util.AnimationBuilder;
import cs3500.shape.Ellipse;
import cs3500.shape.Rect;
import cs3500.shape.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AnimationTest {

  Animation anim = new Animation(0, 0, 500, 500);
  Shape rect = new Rect(
          new State(10, 20, 2, 20, 30, 30, 30), "R");
  Shape elipse = new Ellipse(
          new State(30, 50, 4, 4, 50, 60, 70), "E");

  State start2 = new State(150, 300, 7, 3, 3, 3, 3);
  State end2 = new State(250, 300, 6, 3, 3, 3, 3);
  State start3 = new State(300, 300, 4, 3, 3, 3, 3);
  State end3 = new State(350, 400, 9, 3, 3, 2, 2);

  Motion e2 = new Motion(20, 100, start2, end2);
  Motion e3 = new Motion(100, 101, end2, end3);
  State start4 = new State(100, 300, 2, 3, 3, 3, 3);
  State end4 = new State(200, 300, 2, 3, 3, 3, 3);
  State start5 = new State(100, 300, 2, 3, 3, 3, 3);
  State end5 = new State(150, 400, 9, 3, 3, 2, 2);
  State start6 = new State(150, 300, 7, 3, 3, 3, 3);
  State end6 = new State(250, 300, 6, 3, 3, 3, 3);

  Motion e4 = new Motion(1, 10, start4, end4);

  String catchFail = "fail to catch exception";

  // test.txt printshape
  @Test
  public void printShape() {
    Shape rect = new Rect(
            new State(10, 20, 2, 20, 30, 30, 30), "R");
    Shape elipse = new Ellipse(
            new State(30, 50, 4, 4, 50, 60, 70), "E");

    State start = new State(100, 300, 2, 3, 3, 3, 3);
    State end = new State(200, 300, 2, 3, 3, 3, 3);
    State start1 = new State(100, 300, 2, 3, 3, 3, 3);
    State end1 = new State(150, 400, 9, 3, 3, 2, 2);
    State start2 = new State(150, 300, 7, 3, 3, 3, 3);
    State end2 = new State(250, 300, 6, 3, 3, 3, 3);
    State start3 = new State(300, 300, 4, 3, 3, 3, 3);
    State end3 = new State(350, 400, 9, 3, 3, 2, 2);

    Motion e = new Motion(1, 10, start, end);
    Motion e1 = new Motion(10, 20, end, end1);
    Motion e2 = new Motion(20, 100, end1, end2);
    Motion e3 = new Motion(100, 101, end2, end3);

    rect.addEvent(e);
    rect.addEvent(e1);
    rect.addEvent(e2);
    rect.addEvent(e3);


    State start4 = new State(100, 300, 2, 3, 3, 3, 3);
    State end4 = new State(200, 300, 2, 3, 3, 3, 3);
    State start5 = new State(100, 300, 2, 3, 3, 3, 3);
    State end5 = new State(150, 400, 9, 3, 3, 2, 2);
    State start6 = new State(150, 300, 7, 3, 3, 3, 3);
    State end6 = new State(250, 300, 6, 3, 3, 3, 3);

    Motion e4 = new Motion(1, 10, start4, end4);
    Motion e5 = new Motion(10, 20, end4, end5);
    Motion e6 = new Motion(20, 100, end5, end6);


    elipse.addEvent(e4);
    elipse.addEvent(e5);
    elipse.addEvent(e6);

    Animation anim = new Animation(0, 0, 500, 500);
    anim.addShape("R", "rectangle");
    anim.addShape("E", "ellipse");

    anim.addEvent("R", 1, 100, 300, 2, 3, 3, 3, 3, 10,
            200, 300, 2, 3, 3, 3, 3);
    anim.addEvent("R", 10, 200, 300, 2, 3, 3, 3, 3, 20,
            150, 400, 9, 3, 3, 2, 2);

    anim.addEvent("E", 1, 100, 300, 2, 3, 3, 3, 3, 10,
            200, 300, 2, 3, 3, 3, 3);
    anim.addEvent("E", 10, 200, 300, 2, 3, 3, 3, 3, 20,
            150, 400, 9, 3, 3, 2, 2);

    assertEquals("shape R rect\n" +
                    "motion R 1  100 300 2 3 3 3 3   10 200 300 2 3 3 3 3\n" +
                    "motion R 10  200 300 2 3 3 3 3   20 150 400 9 3 3 2 2\n\n"
                    +
                    "shape E ellipse\n" +
                    "motion E 1  100 300 2 3 3 3 3   10 200 300 2 3 3 3 3\n" +
                    "motion E 10  200 300 2 3 3 3 3   20 150 400 9 3 3 2 2\n\n",
            anim.printAnimationState());
  }


  // tests add cs3500.shape
  @Test
  public void addShape() {
    anim.addShape("R", "rectangle");
    anim.addShape("E", "ellipse");

    assertEquals(anim.getAnimations().size(), 2);
    assertEquals(anim.getAnimations().get("R"), rect);
    assertEquals(anim.getAnimations().get("E"), elipse);
  }

  // tests add shape when the shape is null
  @Test
  public void nullShape() {
    String s = "";
    try {
      anim.addShape(null, null);
      fail("fail to catch exception");
    } catch (IllegalArgumentException e) {
      s = "working";
    }
    assertEquals("working", s);
  }

  // tests add shape when the shape already
  @Test
  public void existingShape() {
    anim.addShape("R", "rectangle");
    assertEquals(anim.getShape("R"), rect);
    try {
      anim.addShape("R", "rectangle");
      fail("fail to catch exception");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // tests add shape when a different shape, but same name already exists
  @Test
  public void existingName() {
    anim.addShape("R", "rectangle");
    assertEquals(anim.getShape("R"), rect);
    try {
      Shape exists = new Ellipse("R");
      anim.addShape("R", "ellipse");
      fail("fail to catch exception");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }


  // tests add event
  @Test
  public void addEvent() {
    //if the exact same event already exists or if the cs3500.shape where
    // event is added does not exist or passed Motion or cs3500.Shape are null.

    Animation anim = new Animation(0, 0, 500, 500);
    Shape rect = new Rect(
            new State(10, 20, 2, 20, 30, 30, 30), "R");
    Shape ellipse = new Ellipse(
            new State(30, 50, 4, 4, 50, 60, 70), "E");

    rect.addEvent(e2);
    assertEquals(1, rect.getMotions().size());
    rect.addEvent(e3);
    assertEquals(2, rect.getMotions().size());

    String s = "";
    try {
      anim.addEvent("Rect", 20, 150, 300, 7, 3, 3, 3, 3,
              100, 250, 300, 6, 3, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      s = "worked";
    }

    assertEquals("worked", s);

    try {
      anim.addEvent(null, 20, 150, 300, 7, 3, 3, 3, 3, 100,
              250, 300, 6, 3, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      s = "worked still";
    }
    assertEquals("worked still", s);
  }

  // adds an event with a null string
  @Test
  public void nullShapeMotion() {
    assertEquals(anim.getAnimations().size(), 0);
    try {
      anim.addEvent(null, 20, 150, 300, 7, 3, 3, 3, 3, 100,
              250, 300, 6, 3, 3, 3, 3);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
      assertEquals(anim.getAnimations().size(), 0);
    }
  }

  // adds an event with an empty string
  @Test
  public void emptyStringShape() {
    assertEquals(anim.getAnimations().size(), 0);
    try {
      anim.addEvent("", 20, 150, 300, 7, 3, 3, 3, 3, 100,
              250, 300, 6, 3, 3, 3, 3);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
      assertEquals(anim.getAnimations().size(), 0);
    }
  }

  // adds an event whitespace string
  @Test
  public void whiteSpaceShape() {
    assertEquals(anim.getAnimations().size(), 0);
    try {
      anim.addEvent("        ", 20, 150, 300, 7, 3, 3, 3, 3,
              100, 250, 300, 6, 3, 3, 3, 3);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
      assertEquals(anim.getAnimations().size(), 0);
    }
  }

  // shape not found
  @Test
  public void notFound() {
    anim.addShape("R", "rectangle");
    anim.addShape("E", "ellipse");
    assertEquals(anim.getAnimations().size(), 2);

    try {
      anim.addEvent("A", 20, 150, 300, 7, 3, 3, 3, 3, 100,
              250, 300, 6, 3, 3, 3, 3);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
      assertEquals(anim.getAnimations().size(), 2);
    }
  }

  // invalid motion
  @Test
  public void sameMotion() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    try {
      anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
              250, 300, 6, 3, 3, 3, 3);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // invalid motion: end points do not agree
  @Test
  public void overlapMotion() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    Motion e4 = new Motion(100, 101, start3, end3);
    try {
      anim.addEvent("R", 1, 100, 300, 2, 3, 3, 3, 3, 10,
              200, 300, 2, 3, 3, 3, 3);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // invalid motion: gap in time
  @Test
  public void gapInTime() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    Motion t = new Motion(105, 110, end2, end3);
    try {
      anim.addEvent("R", 105, 250, 300, 6, 3, 3, 3, 3, 110,
              350, 400, 9, 3, 3, 2, 2);
      fail(catchFail);
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  // tests if get copied correctly deep copied the Shape
  @Test
  public void getShape() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    rect.addEvent(e2);
    //fields are the same
    assertEquals("R", rect.getName());
    assertEquals(1, rect.getMotions().size());
    Motion m = rect.getMotions().get(0);
    assertEquals(true, m.getStarting().equals(e2.getStarting()));
    assertEquals(true, m.getEnding().equals(e2.getEnding()));
    assertEquals(true, m.getStart() == e2.getStart());
    assertEquals(true, m.getEnd() == e2.getEnd());
  }

  // tests if copy does not reference the original shape
  @Test
  public void noReferenceShape() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    Shape copy = anim.getShape("R");

    copy.addEvent(e3);
    assertEquals(0, rect.getMotions().size());
    assertEquals(2, copy.getMotions().size());
  }

  // tests if the copy is copied correctly
  @Test
  public void correctCopy() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    anim.addEvent("R", 100, 250, 300, 6, 3, 3, 3, 3, 101,
            350, 400, 9, 3, 3, 2, 2);

    List<Motion> copy = anim.getAllMotions("R");

    assertEquals(anim.getShape("R").getMotions().size(), copy.size());
    for (int i = 0; i < copy.size(); i++) {
      Motion copyMotion = copy.get(i);
      Motion og = anim.getShape("R").getMotions().get(i);

      assertEquals(true, og.getStart() == copyMotion.getStart());
      assertEquals(true, og.getEnd() == copyMotion.getEnd());
      assertEquals(true, og.getStarting().equals(copyMotion.getStarting()));
      assertEquals(true, og.getEnding().equals(copyMotion.getEnding()));
    }
  }

  // tests if the copy is not a reference to the original
  @Test
  public void noReferenceAllMotions() {
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    anim.addEvent("R", 100, 250, 300, 6, 3, 3, 3, 3, 101,
            350, 400, 9, 3, 3, 2, 2);
    assertEquals(2, anim.getShape("R").getMotions().size());
    List<Motion> copy = anim.getAllMotions("R");
    copy.add(e4);
    assertEquals(2, anim.getShape("R").getMotions().size());
  }

  // tests if the copy is not a reference to the original
  @Test
  public void noReferenceAnimation() {
    anim.addShape("R", "rectangle");
    anim.addShape("E", "ellipse");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    anim.addEvent("E", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    anim.addEvent("R", 100, 250, 300, 6, 3, 3, 3, 3, 101,
            350, 400, 9, 3, 3, 2, 2);

    HashMap<String, Shape> copy = anim.getAnimations();
    assertEquals(2, anim.getAnimations().size());
    copy.put("EE", new Ellipse("E"));

    assertEquals(2, anim.getAnimations().size());
  }

  // tests if the copy is copied correctly
  @Test
  public void correctCopyAnimation() {
    anim.addShape("R", "rectangle");
    anim.addShape("E", "ellipse");
    anim.addEvent("R", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    anim.addEvent("E", 20, 150, 300, 7, 3, 3, 3, 3, 100,
            250, 300, 6, 3, 3, 3, 3);
    anim.addEvent("R", 100, 250, 300, 6, 3, 3, 3, 3, 101,
            350, 400, 9, 3, 3, 2, 2);

    HashMap<String, Shape> copy = anim.getAnimations();

    for (String c : copy.keySet()) {
      assertEquals(true, anim.testAnimations().keySet().contains(c));
    }

    // technically this should be a deep test.txt
    // but since getAnimation calls getShape and getMotion
    // and those two motions are tested for, I'll leave it like this
    for (Shape s : copy.values()) {
      assertEquals(true, anim.testAnimations().containsValue(s));
    }
  }

  // tests the new updater methods
  @Test
  public void updateCurrentState() {
    Animation model = new Animation(0, 0, 500, 500);

    State start2 = new State(0, 0, 10, 10, 3, 3, 3);
    State end2 = new State(10, 10, 15, 15, 3, 3, 3);
    State end3 = new State(20, 40, 16, 16, 4, 4, 4);

    Motion e2 = new Motion(0, 10, start2, end2);

    assertEquals(e2.current(0), start2);
    assertEquals(e2.current(1), new State(1, 1, 10, 10, 3, 3, 3));
    assertEquals(e2.current(10), end2);

    Motion e3 = new Motion(10, 20, end2, end3);


    Shape rect = new Rect(start2, "R");

    rect.addEvent(e2);
    rect.addEvent(e3);

    rect.updateCurrentState(0);
    assertEquals(((Rect) rect).currentState, start2);

    rect.updateCurrentState(1);
    assertEquals(((Rect) rect).currentState,
            new State(1, 1, 10, 10, 3, 3, 3));

    rect.updateCurrentState(10);
    assertEquals(((Rect) rect).currentState, end2);

    model.addShape("R", "rectangle");

  }

  // tests the new builder
  @Test
  public void getX() {

    AnimationBuilder anim = new Animation.Builder();
    IAnimation model = ((Animation.Builder) anim).build();
    assertEquals(model.getX(), 0);
    assertEquals(model.getY(), 0);
    assertEquals(model.getWidth(), 100);
    assertEquals(model.getHeight(), 100);

    anim.setBounds(3, 3, 3, 3);
    anim.build();
    model = (IAnimation) anim.build();

    assertEquals(model.getX(), 3);
    assertEquals(model.getY(), 3);
    assertEquals(model.getWidth(), 3);
    assertEquals(model.getHeight(), 3);

    String test = "";
    try {
      anim.declareShape("R", "");
    } catch (IllegalArgumentException e) {
      test = "t1";
    }

    assertEquals("t1", test);

    try {
      anim.declareShape("", "rectangle");
    } catch (IllegalArgumentException e) {
      test = "t2";
    }
    assertEquals("t2", test);

    anim.declareShape("R", "rectangle");
    model = ((Animation.Builder) anim).build();

    assertEquals(1, model.getAnimations().size());

    try {
      anim.addMotion("", 0, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3
              , 3);
    } catch (IllegalArgumentException e) {
      test = "t3";
    }
    assertEquals("t3", test);

    try {
      anim.addMotion(null, 0, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3
              , 3);
    } catch (IllegalArgumentException e) {
      test = "t4";
    }
    assertEquals("t4", test);


    anim.addMotion("R", 0, 3, 3, 3, 3, 3, 3, 3, 3,
            3, 3, 3, 3, 3, 3
            , 3);
    model = ((Animation.Builder) anim).build();

    assertEquals(1, model.getShape("R").getMotions().size());

  }


  // tests getting the last tick in the animation
  @Test
  public void lastTick() {
    Animation anim = new Animation(0, 0, 500, 500);
    Shape rect = new Rect(
            new State(10, 20, 2, 20, 30, 30, 30), "R");
    Shape elipse = new Ellipse(
            new State(30, 50, 4, 4, 50, 60, 70), "E");
    anim.addShape("R", "rectangle");

    anim.addEvent("R", 0, 7, 3, 3, 3, 3,
            250, 300, 200, 7, 3, 3, 3, 3,
            250, 300);
    assertEquals(anim.lastTick(), 200);
    anim.addEvent("R", 200, 7, 3, 3, 3, 3,
            250, 300, 300, 3, 6, 6, 9, 9, 9, 9);
    assertEquals(anim.lastTick(), 300);

    anim.addEvent("R", 300, 3, 6, 6, 9, 9, 9, 9,
            500, 3, 6, 6, 9, 9, 9, 9);
    assertEquals(anim.lastTick(), 500);

    //shape
    Shape test = anim.getShape("R");
    assertEquals(false, test.stateExists(1));
    assertEquals(false, test.stateExists(150));
    assertEquals(true, test.stateExists(200));
    assertEquals(true, test.stateExists(300));
    //motion
    Motion m1 = test.getMotions().get(0);
    Motion m2 = test.getMotions().get(1);
    assertEquals(false, m1.stateExists(1));
    assertEquals(true, m1.stateExists(200));
    assertEquals(true, m2.stateExists(200));
    assertEquals(true, m2.stateExists(300));
    assertEquals(false, m2.stateExists(330));
  }

  // tests removing a shape
  @Test
  public void removeShape() {
    Animation anim = new Animation(0, 0, 500, 500);
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 1, 7, 3, 3, 3, 3,
            250, 255, 200, 7, 3, 3, 3, 3,
            250, 255);
    anim.addEvent("R", 200, 7, 3, 3, 3, 3,
            250, 255, 300, 10, 10, 3, 3, 3, 250, 255);

    assertEquals(1, anim.getAnimations().size());
    anim.removeShape("R");
    assertEquals(0, anim.getAnimations().size());
  }

  // tests removing a frame
  @Test
  public void removeFrame() {
    Animation anim = new Animation(0, 0, 500, 500);
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 1, 7, 3, 3, 3, 3,
            250, 255, 200, 7, 3, 3, 3, 3,
            250, 255);
    anim.addEvent("R", 200, 7, 3, 3, 3, 3,
            250, 255, 300, 10, 10, 3, 3, 3, 250, 255);

    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    assertEquals(true, anim.getAnimations().get("R").stateExists(200));
    anim.removeEvent("R", 200);
    assertEquals(1, anim.getAnimations().get("R").getMotions().size());
    assertEquals(false, anim.getAnimations().get("R").stateExists(200));
  }

  // tests adding a frame
  @Test
  public void addFrame() {
    Animation anim = new Animation(0, 0, 500, 500);
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 1, 7, 3, 3, 3, 3,
            250, 255, 200, 7, 3, 3, 3, 3,
            250, 255);
    anim.addEvent("R", 200, 7, 3, 3, 3, 3,
            250, 255, 300, 10, 10, 3, 3, 3, 250, 255);

    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    anim.addModifyFrame("R", 350, 10, 10, 50, 50, 255, 0, 0);
    assertEquals(3, anim.getAnimations().get("R").getMotions().size());
  }

  // tests modifying an existing frame
  @Test
  public void modifyFrame() {
    Animation anim = new Animation(0, 0, 500, 500);
    anim.addShape("R", "rectangle");
    anim.addEvent("R", 1, 7, 3, 3, 3, 3,
            250, 255, 200, 7, 3, 3, 3, 3,
            250, 255);
    anim.addEvent("R", 200, 7, 3, 3, 3, 3,
            250, 255, 300, 10, 10, 3, 3, 3, 250, 255);

    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    State old = anim.getAnimations().get("R").getMotions().get(0).getStarting();
    assertEquals(7, old.getX());
    assertEquals(3, old.getY());
    assertEquals(3, old.getWidth());
    assertEquals(3, old.getHeight());
    assertEquals(3, old.getR());
    assertEquals(250, old.getG());
    assertEquals(255, old.getB());

    anim.addModifyFrame("R", 1, 10, 10, 50, 50, 255, 0, 0);

    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    State newer = anim.getAnimations().get("R").getMotions().get(0).getStarting();

    assertEquals(10, newer.getX());
    assertEquals(10, newer.getY());
    assertEquals(50, newer.getWidth());
    assertEquals(50, newer.getHeight());
    assertEquals(255, newer.getR());
    assertEquals(0, newer.getG());
    assertEquals(0, newer.getB());
  }

  // tests that the output is an instance of a read only model
  @Test
  public void testToRO() {
    assertEquals(false, anim instanceof RoIAnimation);
    assertEquals(true, anim.toRO() instanceof RoIAnimation);
  }

  // throw error if empty, ie the user didn't select a shape
  @Test(expected = IllegalArgumentException.class)
  public void unselecetedShape() {
    anim.addModifyFrame(
            "", 5, 5, 5, 5, 5, 5, 5, 5);
  }

  // throw error if null name
  @Test(expected = IllegalArgumentException.class)
  public void nullSelected() {
    anim.addModifyFrame(
            null, 5, 5, 5, 5, 5, 5, 5, 5);
  }
}









