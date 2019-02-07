package cs3500.adapting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.animation.Animation;
import cs3500.animation.Motion;
import cs3500.animation.State;
import cs3500.provider.ShapeType;
import cs3500.provider.Transformation;

import static org.junit.Assert.*;

public class AnimationModelAdapterTest {

  @Test
  public void description() {
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
    AnimationModelAdapter model = new AnimationModelAdapter(anim);

    State start = new State(100, 300, 2, 3, 3, 3, 3);
    State end = new State(200, 300, 2, 3, 3, 3, 3);
    State start1 = new State(200, 300, 2, 3, 3, 3, 3);
    State end1 = new State(150, 400, 9, 3, 3, 2, 2);
    State start2 = new State(100, 300, 2, 3, 3, 3, 3);
    State end2 = new State(200, 300, 2, 3, 3, 3, 3);
    State start3 = new State(200, 300, 2, 3, 3, 3, 3);
    State end3 = new State(150, 400, 9, 3, 3, 2, 2);
    State start4 = new State(150, 400, 9, 3, 3, 2, 2);
    State end4 = new State(450, 400, 9, 3, 3, 2, 2);

    Motion m = new Motion(1, 10, start, end);
    Motion m1 = new Motion(10, 20, start1, end1);
    Motion m2 = new Motion(1, 10, start2, end2);
    Motion m3 = new Motion(10, 20, start3, end3);
    Motion m5 = new Motion(20, 30, start4, end4);

    Transformation t1 = new TransformationtoMotionAdapter(m, "R");
    Transformation t2 = new TransformationtoMotionAdapter(m1, "R");
    Transformation t3 = new TransformationtoMotionAdapter(m2, "E");
    Transformation t4 = new TransformationtoMotionAdapter(m3, "E");
    Transformation t5 = new TransformationtoMotionAdapter(m5, "E");

    //I present most brutal testing ood has ever seen
    List<Transformation> trans = new ArrayList<>();
    trans.add(t1);
    trans.add(t2);
    trans.add(t3);
    trans.add(t4);
    assertEquals(4, model.getTransformations().size());
    for (int i = 0; i < 4; i++) {

      assertEquals(trans.get(i).endTick(), model.getTransformations().get(i).endTick());
      assertEquals(trans.get(i).startTick(), model.getTransformations().get(i).startTick());
      assertEquals(trans.get(i).getStartWidth(), model.getTransformations().get(i).getStartWidth());
      assertEquals(trans.get(i).getEndWidth(), model.getTransformations().get(i).getEndWidth());
      assertEquals(trans.get(i).getStartHeight(), model.getTransformations().get(i).getStartHeight());
      assertEquals(trans.get(i).getEndHeight(), model.getTransformations().get(i).getEndHeight());
      assertEquals(trans.get(i).getStartColor(), model.getTransformations().get(i).getStartColor());
      assertEquals(trans.get(i).getEndColor(), model.getTransformations().get(i).getEndColor());
    }

    HashMap<String, ShapeType> ids = new HashMap<>();
    ids.put("E", ShapeType.ELLIPSE);
    ids.put("R", ShapeType.RECTANGLE);
    assertEquals(ids.get("R"), model.getIDsToTypes().get("R"));
    assertEquals(ids.get("E"), model.getIDsToTypes().get("E"));


    ///addTransformation
    assertEquals(4, model.getTransformations().size());
    model.addTransformation(t5);
    trans.add(t5);
    assertEquals(5, model.getTransformations().size());
    assertEquals(trans.get(4).endTick(), model.getTransformations().get(4).endTick());
    assertEquals(trans.get(4).startTick(), model.getTransformations().get(4).startTick());
    assertEquals(trans.get(4).getStartWidth(), model.getTransformations().get(4).getStartWidth());
    assertEquals(trans.get(4).getEndWidth(), model.getTransformations().get(4).getEndWidth());
    assertEquals(trans.get(4).getStartHeight(), model.getTransformations().get(4).getStartHeight());
    assertEquals(trans.get(4).getEndHeight(), model.getTransformations().get(4).getEndHeight());
    assertEquals(trans.get(4).getStartColor(), model.getTransformations().get(4).getStartColor());
    assertEquals(trans.get(4).getEndColor(), model.getTransformations().get(4).getEndColor());



    ///getLocation
    assertEquals(anim.getX(), model.getLocation().x);
    assertEquals(anim.getY(), model.getLocation().y);

    //AddShape
    model.addShape("RR", ShapeType.RECTANGLE);
    model.addShape("EE", ShapeType.ELLIPSE);

    assertEquals(ShapeType.RECTANGLE,model.getIDsToTypes().get("RR"));
    assertEquals(ShapeType.ELLIPSE, model.getIDsToTypes().get("EE"));

    String s = "";
    try {
      model.addShape("", ShapeType.ELLIPSE);
    fail("fail to catch exception");
    } catch (IllegalArgumentException e) {
      s = "working";
    }
    assertEquals("working", s);

    try {
      model.addShape(null, ShapeType.ELLIPSE);
      fail("fail to catch exception");
    } catch (IllegalArgumentException e) {
      s = "working1";
    }
    assertEquals("working1", s);
    try {
      model.addShape("s", null);
      fail("fail to catch exception");
    } catch (IllegalArgumentException e) {
      s = "working2";
    }
    assertEquals("working2", s);

    anim.getShape("RR");
    ///RemoveShape
    assertEquals(4,model.getIDsToTypes().size());
    model.removeShape("RR");
    assertEquals(3,model.getIDsToTypes().size());
    model.removeShape("EE");
    assertEquals(2,model.getIDsToTypes().size());

    try {
      anim.getShape("RR");

    } catch (IllegalArgumentException e) {
      s = "working3";
    }
    assertEquals("working3", s);

    try {
      anim.getShape("EE");

    } catch (IllegalArgumentException e) {
      s = "working4";
    }
    assertEquals("working4", s);

    try {
      model.removeShape("EE");

    } catch (IllegalArgumentException e) {
      s = "working5";
    }
    assertEquals("working5", s);


    try {
      model.removeShape("");

    } catch (IllegalArgumentException e) {
      s = "working6";
    }
    assertEquals("working6", s);


    try {
      model.removeShape(null);

    } catch (IllegalArgumentException e) {
      s = "working7";
    }
    assertEquals("working7", s);



/// Add/Remove frame
    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    model.addKeyFrame("R",200);
    assertEquals(3, anim.getAnimations().get("R").getMotions().size());
    assertEquals(true, anim.getAnimations().get("R").stateExists(200));
    model.removeKeyFrame   ("R", 200);
    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    assertEquals(false, anim.getAnimations().get("R").stateExists(200));



    try {
      model.removeKeyFrame("",200);

    } catch (IllegalArgumentException e) {
      s = "working8";
    }
    assertEquals("working8", s);
    try {
      model.removeKeyFrame("RRR",20);

    } catch (IllegalArgumentException e) {
      s = "working9";
    }
    assertEquals("working9", s);
    try {
      model.removeKeyFrame(null,20);

    } catch (IllegalArgumentException e) {
      s = "working10";
    }
    assertEquals("working10", s);



    try {
      model.addKeyFrame("",200);

    } catch (IllegalArgumentException e) {
      s = "working11";
    }
    assertEquals("working11", s);
    try {
      model.removeKeyFrame("RRR",20);

    } catch (IllegalArgumentException e) {
      s = "working12";
    }
    assertEquals("working12", s);
    try {
      model.removeKeyFrame(null,20);

    } catch (IllegalArgumentException e) {
      s = "working13";
    }
    assertEquals("working13", s);


//ModifyKeyFrame

    assertEquals(2, anim.getAnimations().get("R").getMotions().size());
    State old = anim.getAnimations().get("R").getMotions().get(0).getStarting();

    assertEquals(100, old.getX());
    assertEquals(300, old.getY());
    assertEquals(2, old.getWidth());
    assertEquals(3, old.getHeight());
    assertEquals(3, old.getR());
    assertEquals(3, old.getG());
    assertEquals(3, old.getB());

    model.modifyKeyFrame("R", 1, 10, 10, 50, 50, 255, 0, 0);

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

}