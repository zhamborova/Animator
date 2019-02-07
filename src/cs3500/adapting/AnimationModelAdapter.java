package cs3500.adapting;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs3500.animation.IAnimation;
import cs3500.animation.Motion;
import cs3500.provider.ShapeType;
import cs3500.provider.ImmutablePoint;
import cs3500.provider.Transformation;
import cs3500.provider.WindowedAnimationModel;
import cs3500.shape.Ellipse;
import cs3500.shape.Rect;
import cs3500.shape.Shape;

/**
 * Works as an adapter between our model implementation and the provider's model interface. It is a
 * two way adapter so we can still use it as an implementation of our interface as well. Our
 * implementations were similar, therefore most adapting was a simple method call. The main
 * difference was adapting how we stored data and the provider did. This is further documented in
 * TransformationMotionAdapter.
 */
public class AnimationModelAdapter implements WindowedAnimationModel {
  IAnimation animation;

  /**
   * The default constructor for the adapter.
   */
  public AnimationModelAdapter(IAnimation animation) {
    this.animation = animation;
  }

  @Override
  public String description() {
    return animation.printAnimationState();
  }

  /**
   * Our model stored the data in a different format. The method adapts how we stored our data to
   * the way the provider did.
   */
  @Override
  public List<Transformation> getTransformations() {
    HashMap<String, Shape> map = this.animation.getAnimationsDir();
    Set<String> ids = map.keySet();
    List<Transformation> transformations = new ArrayList<>();
    for (String id : ids) {
      List<Motion> motions = this.animation.getAllMotionsDir(id);
      for (Motion m : motions) {
        Transformation tm = new TransformationtoMotionAdapter(m, id);
        transformations.add(tm);
      }
    }
    return transformations;
  }

  /**
   * Made a hashmap where each string name/id is assigned to its type of shape.
   */
  @Override
  public Map<String, ShapeType> getIDsToTypes() {
    HashMap<String, Shape> map = this.animation.getAnimationsDir();
    Map<String, ShapeType> idToTypes = new HashMap<>();
    Set<String> ids = map.keySet();
    for (String id : ids) {
      Shape shape = map.get(id);
      if (shape instanceof Rect) {
        idToTypes.put(id, ShapeType.RECTANGLE);
      }
      if (shape instanceof Ellipse) {
        idToTypes.put(id, ShapeType.ELLIPSE);
      }
    }
    return idToTypes;
  }

  /**
   * Broke down a Transformation into its individual data to add to our model.
   *
   * @param t The transformation to add.
   */
  @Override
  public void addTransformation(Transformation t) throws IllegalArgumentException {
    String name = t.getID();
    ImmutablePoint start = t.getStartLocation();
    ImmutablePoint end = t.getEndLocation();
    Color sC = t.getStartColor();
    Color eC = t.getEndColor();
    this.animation.addEvent(name, t.startTick(), start.x, start.y, t.getStartWidth(),
            t.getStartHeight(),
            sC.getRed(), sC.getGreen(), sC.getBlue(), t.endTick(), end.x, end.y, t.getEndWidth(),
            t.getEndHeight(), eC.getRed(), eC.getGreen(), eC.getBlue());
  }

  //straightforward
  @Override
  public void addShape(String id, ShapeType type) throws IllegalArgumentException {
    if (id == null || type == null) {
      throw new IllegalArgumentException();
    }
    this.animation.addShape(id, type.getDescription());
  }

  //straightfoward
  @Override
  public void removeShape(String id) {
    this.animation.removeShape(id);
  }

  /**
   * The provider initially sets the keyframe to all 0 values.
   *
   * @param id   The name of the shape.
   * @param tick The tick to add the keyframe at.
   */
  @Override
  public void addKeyFrame(String id, int tick) throws IllegalArgumentException {
    this.animation.addModifyFrame(id, tick, 0, 0, 0, 0, 0, 0, 0);
  }

  @Override
  public void modifyKeyFrame(String id, int tick, int x, int y, int w, int h, int r, int g, int b)
          throws IllegalArgumentException {
    this.animation.addModifyFrame(id, tick, x, y, w, h, r, g, b);
  }

  @Override
  public void removeKeyFrame(String id, int tick) throws IllegalArgumentException {
    this.animation.removeEvent(id, tick);
  }

  @Override
  public int getEndTick() {
    return animation.lastTick();
  }

  @Override
  public int getWidth() {
    return this.animation.getWidth();
  }

  @Override
  public int getHeight() {
    return this.animation.getHeight();
  }

  @Override
  public ImmutablePoint getLocation() {
    ImmutablePoint loc = new ImmutablePoint(this.animation.getX(), this.animation.getY());
    return loc;
  }
}
