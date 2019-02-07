package cs3500.view;

import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import cs3500.provider.ShapeType;
import cs3500.animation.Motion;
import cs3500.animation.RoIAnimation;
import cs3500.animation.State;
import cs3500.controller.IViewListener;
import cs3500.provider.Transformation;
import cs3500.shape.Shape;

/**
 * Represents a textual view of the animation. This implementation provides a view of the animation
 * through textual description. It provides functionality for translating data in the model to
 * text.
 */
public class TextView extends PrintBasedView {
  private final RoIAnimation model;
  private final StringBuilder print;
  private final String file;


  /**
   * Default constructor for a text based view.
   *
   * @param file name of the file to export the view. Can be null or empty.
   */
  public TextView(String file, RoIAnimation model) {
    super(); // speed isn't important in text view
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.print = new StringBuilder();
    this.file = file;
  }

  /**
   * Animates the given shape's motions.
   *
   * @param shape the shape to animate
   */
  public void animate(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("shape is null");
    }
    for (Motion motion : shape.getMotions()) {
      print.append("motion " + shape.getName() + " " + Integer.toString(motion.getStart()) + " " +
              printState(motion.getStarting()) + "   " + Integer.toString(motion.getEnd()) +
              printState(motion.getEnding()) + "\n");
    }
  }

  private String printState(State state) {
    StringBuilder print = new StringBuilder();
    print.append(" " + state.getX() + " " + state.getY() + " " + state.getWidth() + " " +
            state.getHeight() + " " + state.getR() + " " + state.getG() + " " + state.getB());

    return print.toString();
  }

  /**
   * Describes the creation of the given shape.
   *
   * @param shape the shape to be created
   */
  public void render(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("shape is null");
    }
    this.print.append("shape " + shape.getName() + " "
            + shape.getClass().getSimpleName().toLowerCase() + "\n");
  }

  @Override
  public String exportAnimation() {
    Map<String, Shape> everything = model.getAnimations();

    for (Shape shape : everything.values()) {
      this.render(shape);
      this.animate(shape);
    }

    // null or empty string means no file name was specified
    // defaults to the console
    if (file == null || file.trim().equals("") || file.equals("out")) {
      String printed = print.toString();
      return printed;
    }

    return writeToFile(file, print);
  }


  @Override
  public void setListeners(IViewListener buttons) {
    //ignored
  }

  @Override
  public void setLoop() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void toggleTime() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void toggleSpeed(int speed) {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void start() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getAddShapeName() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getAddType() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getSelectedShape() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getT() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getXPosn() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getYPosn() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getW() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getH() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getR() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getG() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getB() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getTickRemove() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void updateAvailableShapes(String action, String name) {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void updateLastTick(int tick) {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getEdit() {
    return "";
  }

  @Override
  public void acceptTransformations(List<Transformation> transformations) {
    throw new UnsupportedOperationException("Not Applicabe");
  }

  @Override
  public void acceptIDsToTypes(Map<String, ShapeType> idsToTypes) {
    throw new UnsupportedOperationException("Not Applicabe");
  }

  @Override
  public void setKeyListener(KeyListener key) {
    //ignored
  }

  @Override
  public void setFocus() {

  }

  @Override
  public void pauseOrPlay() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void play() {
    throw new UnsupportedOperationException("Not Applicabe");
  }

  @Override
  public void pause() {
    throw new UnsupportedOperationException("Not Applicabe");
  }
}
