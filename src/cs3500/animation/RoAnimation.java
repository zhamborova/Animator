package cs3500.animation;

import java.util.HashMap;
import java.util.List;

import cs3500.shape.Shape;

/**
 * Implementation of a read only animation. The read only has the same functionality as a mutable
 * animation Except it blocks off mutable functionality.
 */
public class RoAnimation extends Animation implements RoIAnimation {
  /**
   * Default constructor for the Animation model.
   */
  public RoAnimation(int x, int y, int w, int h, HashMap<String, Shape> map) {
    super(x, y, w, h, map);
  }

  public RoAnimation(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  @Override
  public void addEvent(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                       int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("unsupported function");
  }


  @Override
  public void addShape(String name, String type) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("unsupported function");
  }

  @Override
  public void removeEvent(String name, int tick) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("unsupported function");
  }

  @Override
  public HashMap<String, Shape> testAnimations() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("unsupported function");
  }

  @Override
  public void addModifyFrame(
          String shapeName, int tick, int x, int y, int w, int h, int r, int g, int b)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("unsupported function");
  }

  @Override
  public List<Shape> getShapes() {
    throw new UnsupportedOperationException("unsupported function");
  }

  @Override
  public HashMap<String, Shape> getAnimationsDir() {
    throw new UnsupportedOperationException("unsupported function");
  }

  @Override
  public List<Motion> getAllMotionsDir(String shape) {
    throw new UnsupportedOperationException("unsupported function");
  }

}
