package cs3500.adapting;

import java.awt.*;

import cs3500.animation.Motion;
import cs3500.provider.ImmutablePoint;
import cs3500.provider.Transformation;

/**
 * Our model and the provider's model stored data differently. For the most part it's the same, our
 * motion contained the values from one key frame to the other, by storing these in two State
 * classes. Each State representing a keyframe. It seems the provider put all the values in
 * Transformation. The biggest difference was we did not assign our keyframe implementations their
 * shape names while the provider's Transformation does. Since our implementations were similar,
 * adapting was mostly simple method calls.
 *
 * Main issue we faced: we passed data to the views through an immutable model. When the controller
 * made changes to the model, although the view can't make any changes, it was able to observe
 * these changes. The provider's view does not contain a model, but the values themselves.
 * When we adapted their Transformation with our Motion, the reference was lost. This made us
 * introduce functionality into our interface that we don't really want. This is explained more
 * specifically in the docs for the IView interface.
 */
public class TransformationtoMotionAdapter implements Transformation {
  private Motion motion;
  private String name;


  /**
   * The default constructor.
   *
   * @param motion motion with values.
   * @param name   name of shape.
   */
  public TransformationtoMotionAdapter(Motion motion, String name) {
    this.motion = motion;
    this.name = name;
  }

  @Override
  public int startTick() {
    return motion.getStart();
  }

  @Override
  public int endTick() {
    return motion.getEnd();
  }

  @Override
  public String description() {
    return motion.printMotion(name);
  }

  @Override
  public ImmutablePoint getStartLocation() {
    return new ImmutablePoint(motion.getStarting().getX(), motion.getStarting().getY());
  }

  @Override
  public int getStartWidth() {
    return motion.getStarting().width;
  }

  @Override
  public int getStartHeight() {
    return motion.getStarting().height;
  }

  @Override
  public Color getStartColor() {
    return new Color(motion.getStarting().r, motion.getStarting().g, motion.getStarting().b);
  }

  /**
   * We kept the x and y position values separate. The provider stored these in a ImmutablePoint
   * object.
   */
  @Override
  public ImmutablePoint getEndLocation() {
    return new ImmutablePoint(motion.getEnding().getX(), motion.getEnding().getY());
  }

  @Override
  public int getEndWidth() {
    return motion.getEnding().width;
  }

  @Override
  public int getEndHeight() {
    return motion.getEnding().getHeight();
  }

  /**
   * We kept each color RGB value separate. The provider put these in the Color object.
   */
  @Override
  public Color getEndColor() {
    return new Color(motion.getEnding().r, motion.getEnding().g, motion.getEnding().b);
  }

  /**
   * What we refer to as the Shape's name, the provider calls the Shape ID.
   */
  @Override
  public String getID() {
    return this.name;
  }
}
