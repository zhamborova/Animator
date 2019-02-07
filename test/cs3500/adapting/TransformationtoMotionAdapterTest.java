package cs3500.adapting;

import org.junit.Test;

import java.awt.*;

import cs3500.animation.Motion;
import cs3500.animation.State;
import cs3500.provider.ImmutablePoint;
import cs3500.shape.Ellipse;
import cs3500.shape.Shape;

import static org.junit.Assert.*;

public class TransformationtoMotionAdapterTest {

  @Test
  public void startTick() {
    State start = new State(100, 300, 2, 3, 3, 3, 3);
    State end = new State(200, 300, 2, 4, 3, 3, 3);
    State start1 = new State(300, 300, 4, 3, 3, 3, 3);
    State end1 = new State(350, 400, 9, 5, 3, 2, 2);

    Motion m = new Motion(10, 20, start,end);
    Motion m1 = new Motion(0, 100, start1,end1);
    TransformationtoMotionAdapter transform = new TransformationtoMotionAdapter(m, "E");
    TransformationtoMotionAdapter transform1 = new TransformationtoMotionAdapter(m1, "B");
    ///start tick
    assertEquals(10, transform.startTick());
    assertEquals(0, transform1.startTick());
    //end tick
    assertEquals(20, transform.endTick());
    assertEquals(100,transform1.endTick());
    //description
    assertEquals( "motion E 10  100 300 2 3 3 3 3   20 200 300 2 4 3 3 3", transform.description());
    assertEquals("motion B 0  300 300 4 3 3 3 3   100 350 400 9 5 3 2 2", transform1.description());
    //getStartLocation
    assertEquals(100, transform.getStartLocation().x);
    assertEquals(300, transform1.getStartLocation().x);
    assertEquals(300, transform.getStartLocation().y);
    assertEquals(300, transform1.getStartLocation().y);
   ///getEndLocation
    assertEquals(200, transform.getEndLocation().x);
    assertEquals(350, transform1.getEndLocation().x);
    assertEquals(300, transform.getEndLocation().y);
    assertEquals(400, transform1.getEndLocation().y);
    ///getStartWidth
    assertEquals(2, transform.getStartWidth());
    assertEquals(4, transform1.getStartWidth());
    //getEndWidth
    assertEquals(2, transform.getEndWidth());
    assertEquals(9, transform1.getEndWidth());
    //getStartHeight
    assertEquals(3, transform.getStartHeight());
    assertEquals(3, transform.getStartHeight());
    //getEndHeight
    assertEquals(4, transform.getEndHeight());
    assertEquals(5, transform1.getEndHeight());
    //getColorStart
    assertEquals( new Color(3,3,3), transform.getStartColor());
    assertEquals(new Color(3,3,3), transform1.getStartColor());
    //getColorEnd
    assertEquals( new Color(3,3,3), transform.getEndColor());
    assertEquals(new Color(3,2,2), transform1.getEndColor());
    //getId
    assertEquals("E",  transform.getID());
    assertEquals("B", transform1.getID());

  }
}