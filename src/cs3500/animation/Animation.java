package cs3500.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import cs3500.animator.util.AnimationBuilder;
import cs3500.shape.Ellipse;
import cs3500.shape.Rect;
import cs3500.shape.Shape;

/**
 * Represents the Model for a simple Animator. The for a simple Animator stores the data necessary
 * to draw an exportAnimation. The model keeps track of the tick, dimensions of the canvas, the
 * shapes and their motions.
 */
public class Animation implements IAnimation {
  private final int x;
  private final int y;
  private final int w;
  private final int h;
  // used a linked hashmap to ensure that shapes are in the order that they are added
  // this allows testing and simple sorting of the shapes orders
  // hashmap for quick acccess based on the name of the shape we desire
  private final HashMap<String, Shape> animations;

  /**
   * The Builder for creating a model. The builder manipulates a mutable model, but returns a RO
   * model to the view to prevent further changes.
   */
  public static final class Builder implements AnimationBuilder<IAnimation> {
    // the builder should have the fields of the exportAnimation to pass in
    // but how else can i call my original methods?
    IAnimation model;

    /**
     * By default sets the origin to 0,0 and a 100 by 100 canvas.
     */
    public Builder() {
      this.model = new Animation(0, 0, 100, 100);
    }

    @Override
    public IAnimation build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IAnimation> setBounds(int x, int y, int width, int height) {
      this.model = new Animation(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> declareShape(String name, String type) {
      if (name == null || type == null || name.trim().equals("") || type.trim().equals("")) {
        throw new IllegalArgumentException();
      }
      model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimation> addMotion(String name, int t1, int x1,
                                                  int y1, int w1, int h1, int r1, int g1,
                                                  int b1, int t2, int x2, int y2, int w2, int h2,
                                                  int r2, int g2, int b2) {
      if (name == null || name.trim().equals("")) {
        throw new IllegalArgumentException();
      }

      model.addEvent(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
      return this;
    }

    /**
     * Did not need addKeyFrame for the purposes of this assignment. We understand it is a point in
     * the exportAnimation where the objects need to converge. When we add it to a motion the motion
     * will have to add an additional State that ties the previous motion to this frame.
     *
     * @param name The name of the cs3500.shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @param x    The x-position of the cs3500.shape
     * @param y    The y-position of the cs3500.shape
     * @param w    The width of the cs3500.shape
     * @param h    The height of the cs3500.shape
     * @param r    The red color-value of the cs3500.shape
     * @param g    The green color-value of the cs3500.shape
     * @param b    The blue color-value of the cs3500.shape
     */
    @Override
    public AnimationBuilder<IAnimation> addKeyframe(String name, int t, int x, int y, int w,
                                                    int h, int r, int g, int b) {
      if (name == null || name.trim().equals("")) {
        throw new IllegalArgumentException();
      }

      model.addModifyFrame(name, t, x, y, w, h, r, g, b);
      return this;
    }
  }


  /**
   * Default constructor for the Animation model.
   */
  public Animation(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.animations = new LinkedHashMap<>();

  }

  /**
   * Builder constructor for the Animation model.
   */
  protected Animation(int x, int y, int w, int h, HashMap<String, Shape> map) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.animations = map;

  }

  @Override
  public int[] getCanvas() {
    int[] canvas = new int[4];
    canvas[0] = this.x;
    canvas[1] = this.y;
    canvas[2] = this.w;
    canvas[3] = this.h;
    return canvas;
  }

  @Override
  public void addShape(String name, String type) throws IllegalArgumentException {
    if (name == null || animations.containsKey(name) || type == null || name.trim().equals("")
            ||
            type.trim().equals("")) {
      throw new IllegalArgumentException();
    } else {

      Map<String, Function<String, Shape>> declarableShapes = new HashMap<>();
      declarableShapes.put("ellipse", new Function<String, Shape>() {
        @Override
        public Shape apply(String n) {
          return new Ellipse(n);
        }
      });
      declarableShapes.put("rectangle", new Function<String, Shape>() {
        @Override
        public Shape apply(String n) {
          return new Rect(n);
        }
      });

      Function<String, Shape> shape = declarableShapes.getOrDefault(type, null);
      if (shape == null) {
        throw new IllegalArgumentException();
      }

      Shape declare = shape.apply(name);

      animations.put(name, declare);
    }
  }

  @Override
  public void addEvent(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                       int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
          throws IllegalArgumentException {
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException();
    }
    Shape shape = animations.getOrDefault(name, null);
    if (shape == null) {
      throw new IllegalArgumentException("Shape not found");
    }

    State startState = new State(x1, y1, w1, h1, r1, g1, b1);
    State endState = new State(x2, y2, w2, h2, r2, g2, b2);
    Motion motion = new Motion(t1, t2, startState, endState);

    shape.addEvent(motion);

  }

  @Override
  public void removeEvent(String name, int tick) throws IllegalArgumentException {
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException();
    }

    Shape toRemove = this.animations.getOrDefault(name, null);
    if (toRemove == null) {
      throw new IllegalArgumentException();
    }

    toRemove.removeEvent(tick);
  }

  @Override
  public void removeShape(String name) {
    if (name == null || name.trim().equals("") || !animations.containsKey(name)) {
      throw new IllegalArgumentException();
    }
    System.out.println(this.animations.containsKey(name));
    this.animations.remove(name);
  }

  // kept this just for testing purposes
  // not used for the purposes of the view
  @Override
  public String printAnimationState() {
    StringBuilder state = new StringBuilder();
    Set<Map.Entry<String, Shape>> key = animations.entrySet();
    for (Map.Entry<String, Shape> k : key) {
      Shape shape = k.getValue();
      state.append(shape.printShape() + "\n");
      state.append(shape.printAllMotions() + "\n");
    }

    return state.toString();
  }

  @Override
  public HashMap<String, Shape> getAnimations() {
    LinkedHashMap<String, Shape> copy = new LinkedHashMap<>();
    Set<Map.Entry<String, Shape>> key = animations.entrySet();
    for (Map.Entry<String, Shape> k : key) {
      copy.put(k.getKey(), k.getValue().copyShape());
    }
    return copy;
  }

  @Override
  public HashMap<String, Shape> getAnimationsDir() throws IllegalArgumentException {
    return this.animations;
  }

  @Override
  public List<Motion> getAllMotionsDir(String shape) throws IllegalArgumentException {
    Shape s = this.animations.get(shape);
    return s.getMotionsDir();
  }

  /**
   * This method is used for purely testing reasons. Is blocked by the read only interface.
   */
  public HashMap<String, Shape> testAnimations() {
    return this.animations;
  }

  @Override
  public Shape getShape(String name) {
    if (name == null || name.trim().equals("") ||
            animations.getOrDefault(name, null) == null) {
      throw new IllegalArgumentException("invalid name");
    }
    Shape shape = animations.get(name);
    return shape.copyShape();
  }

  @Override
  public List<Motion> getAllMotions(String name) {
    // is this better than contains?
    if (name == null || name.trim().equals("") ||
            animations.getOrDefault(name, null) == null) {
      throw new IllegalArgumentException("invalid name");
    }

    Shape shape = animations.get(name);
    ArrayList<Motion> copyMotions = shape.copyAllMotions();

    return copyMotions;
  }

  @Override
  public RoIAnimation toRO() {
    return new RoAnimation(this.x, this.y, this.w, this.h, this.animations);
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getWidth() {
    return w;
  }

  @Override
  public int getHeight() {
    return h;
  }

  @Override
  public int lastTick() {

    ArrayList<Integer> last = new ArrayList<>();
    Set<Map.Entry<String, Shape>> key = animations.entrySet();
    for (Map.Entry<String, Shape> k : key) {
      for (Motion m : k.getValue().getMotions()) {

        last.add(m.getEnd());
      }
    }
    int max = last.get(0);
    for (int i = 1; i < last.size(); i++) {
      if (last.get(i) > max) {
        max = last.get(i);
      }
    }
    return max;
  }

  /**
   * Determines if a keyframe at the given tick for the given shape exists.
   *
   * @param shapeName given shape
   * @param tick      given tick
   */
  private boolean stateExists(String shapeName, int tick) {
    return this.animations.get(shapeName).stateExists(tick);
  }

  @Override
  public void addModifyFrame(String shapeName, int tick, int x, int y, int w, int h, int r, int g,
                             int b) {

    if (shapeName == null || shapeName.trim().equals("")) {
      throw new IllegalArgumentException("Choose a shape from the list!");
    }
    State newState = new State(x, y, w, h, r, b, g);
    // if a keyframe at the given tick exists, modify it
    if (stateExists(shapeName, tick)) {
      this.animations.get(shapeName).modifyState(tick, newState);
    }
    // else add a new keyframe
    else {
      animations.get(shapeName).addState(tick, newState);
    }
  }

  @Override
  public List<Shape> getShapes() {
    List<Shape> shapes = new ArrayList<>();
    for (Shape s : this.animations.values()) {
      shapes.add(s);
    }
    return shapes;
  }
}













