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
 * Represents an implementation of an animation's view using SVG format. Provides methods that
 * translate data from the model into SVG syntax. Currently the view uses a read only version of the
 * model to prevent any mutation.
 */
public class SvgView extends PrintBasedView {
  private int rate;
  private RoIAnimation model;
  private StringBuilder print;
  private String file;


  /**
   * Default constructor for a SvgView.
   *
   * @param rate  speed of the animation.
   * @param file  the name of the file destination. Can be null to represent System.out.
   * @param model an immutable model with the data of the animaton.
   */
  public SvgView(int rate, String file, RoIAnimation model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.rate = rate;
    this.model = model;
    this.print = new StringBuilder();
    this.file = file;
  }

  /**
   * Animates a shape.
   *
   * @param shape the shape to be animated
   */
  public String animate(Shape shape) {
    List<Motion> motions = shape.getMotions();
    for (Motion motion : motions) {
      aniMotion(shape, motion.getStart(), motion.getEnd(), motion.getStarting(), motion.getEnding());
    }
    print.append("<" + "/" + shape.getClass().getSimpleName().toLowerCase() + ">\n");

    return print.toString();
  }

  /**
   * Sets the canvas of the animation.
   *
   * @param dims contains 4 values, the x and y of the origin and the width and height of the
   *             canvas
   */
  public String setCanvas(int... dims) {
    return "<svg " + "width=" + addQuotes("" + dims[2]) + " height=" + addQuotes("" + dims[3])
            +
            " version=" + addQuotes("1.1") + " viewBox=" + addQuotes("" + dims[0] + " " +
            dims[1] + " " + dims[2] + " " + dims[3]) + " xmlns=\"http://www.w3.org/2000/svg\"" + ">\n";
  }

  /**
   * Animates a shape.
   *
   * @param shape to be animated
   * @param t1    start time of animation
   * @param t2    end time of animation
   * @param start start state of animation
   * @param end   end state of animation
   */
  public String aniMotion(Shape shape, int t1, int t2, State start, State end) {
    // adjusts to ms
    t1 = t1 * 1000;
    t2 = t2 * 1000;
    // while there's only two it's easier to keep track this way without bloating our code with
    // commands or function objects that may not be extended
    if (start.detectColor(end)) {
      print.append(colorType("fill", t1, t2, start, end));
    }
    if (start.detectSize(end)) {
      if (shape.getClass().getSimpleName().equals("Ellipse")) {
        print.append(sizeType("rx", "ry", t1, t2, start, end));
      } else if (shape.getClass().getSimpleName().equals("Rect")) {
        print.append(sizeType("width", "height", t1, t2, start, end));
      }
    }
    if (start.detectMove(end)) {
      if (shape.getClass().getSimpleName().equals("Rect")) {
        print.append(moveType("x", "y", t1, t2, start, end));
      } else if (shape.getClass().getSimpleName().equals("Ellipse")) {
        print.append(moveType("cx", "cy", t1, t2, start, end));
      }
    }
    return print.toString();
  }

  /**
   * Animates a change in position.
   *
   * @param x     name of x attribute
   * @param y     name of y attribute
   * @param t1    start time
   * @param t2    end time
   * @param start start state
   * @param end   end state
   */
  private String moveType(String x, String y, int t1, int t2, State start, State end) {
    ;
    print.append("<animate attributeType=\"xml\" " + "begin=" +
            addQuotes("" + (t1 / rate) + "ms")
            + " dur=" + addQuotes("" + (((t2 - t1) / rate) + 1) + "ms")
            +
            " attributeName=" + addQuotes(x) + " from=" + addQuotes("" + start.getX()) + " to=" +
            addQuotes("" + end.getX()) +
            " fill=\"freeze\"" + " />\n"
            +
            "<animate attributeType=\"xml\" " + "begin=" + addQuotes("" +
            (t1 / rate) + "ms")
            + " dur=" + addQuotes("" + (((t2 - t1) / rate) + 1) + "ms")
            +
            " attributeName=" + addQuotes(y) + " from=" + addQuotes("" + start.getY()) + " to=" +
            addQuotes("" + end.getY()) +
            " fill=\"freeze\"" + " />\n");
    return x;
  }

  /**
   * Animates a change in color.
   *
   * @param name  name of shape
   * @param t1    start time
   * @param t2    end time
   * @param start start state
   * @param end   end state
   */
  private String colorType(String name, int t1, int t2, State start, State end) {
    print.append("<animate attributeType=\"xml\"" + " begin=" +
            addQuotes(t1 / rate + "ms")
            + " dur=" + addQuotes("" + (((t2 - t1) / rate) + 1) + "ms")
            +
            " attributeName=" + addQuotes(name) + " from=" + addQuotes("rgb" + "(" + start.getR()
            + "," + start.getG() +
            "," + start.getB() + ")") + " to=" + addQuotes("rgb" + "(" + end.getR() +
            "," + end.getG() + "," +
            end.getB() + ")") + " fill=\"freeze\"" + " />\n");
    return name;
  }


  /**
   * Animates a change in size.
   */
  private String sizeType(String w, String h, int t1, int t2, State start, State end) {
    print.append("<animate attributeType=\"xml\"" + " begin=" + addQuotes("" + t1 / rate + "ms") +
            " dur=" + addQuotes("" + (((t2 - t1) / rate) + 1) + "ms")
            +
            " attributeName=" + addQuotes(w) + " from=" + addQuotes("" + start.getWidth()) +
            " to=" + addQuotes("" + end.getWidth()) + " " +
            "fill=\"freeze\"" + " />\n"
            +
            "<animate attributeType=\"xml\"" + " begin=" + addQuotes("" +
            t1 / rate + "ms") +
            " dur=" + addQuotes("" + (((t2 - t1) / rate) + 1) + "ms")
            +
            " attributeName=" + addQuotes(h) + " from=" + addQuotes("" + start.getHeight()) +
            " to=" + addQuotes("" + end.getHeight()) + " " +
            "fill=\"freeze\"" + " />\n");
    return w;
  }

  /**
   * Adds quotes to the String.
   *
   * @param s String that needs quotes
   */
  private String addQuotes(String s) {
    return "\"" + s + "\"";
  }

  /**
   * Initializes a Shape in the Svg animation.
   *
   * @param shape to be initialized
   */
  public String render(Shape shape) {
    State state = shape.copyState();
    List<Motion> motions = shape.getMotions();
    if (shape.getClass().getSimpleName().equals("Rect")) {
      print.append("<" + shape.getClass().getSimpleName().toLowerCase() + " " + "id=" +
              addQuotes(shape.getName()) + " x=" + addQuotes("" + state.getX())
              + " y=" + addQuotes("" + state.getY()) + " width=" + addQuotes("" +
              state.getWidth()) + " height=" + addQuotes("" + state.getHeight()) + " fill="
              + addQuotes("rgb" + "(" + state.getR() + "," + state.getG() +
              "," + state.getB() + ")") +
              " visibility=\"visible\" >\n");
    } else if (shape.getClass().getSimpleName().equals("Ellipse")) {
      print.append("<" + shape.getClass().getSimpleName().toLowerCase() + " " + "id=" +
              addQuotes(shape.getName()) + " cx=" + addQuotes("" + state.getX())
              + " cy=" + addQuotes("" + state.getY()) + " rx=" + addQuotes("" +
              state.getWidth()) + " ry=" + addQuotes("" + state.getHeight()) + " fill="
              + addQuotes("rgb" + "(" + state.getR() + "," + state.getG() +
              "," + state.getB() + ")") +
              " visibility=\"visible\" >\n");
    }
    if (motions.size() > 0) {
      int t2 = motions.get(0).getStart();
      State end = motions.get(0).getStarting();
      this.aniMotion(shape, t2, t2, shape.copyState(), end);
    }
    return "";
  }

  /**
   * Exports the Svg to a destination specified in the file.
   */
  public String exportAnimation() {
    print.append(this.setCanvas(model.getCanvas()));
    Map<String, Shape> everything = model.getAnimations();

    for (Shape shape : everything.values()) {
      this.render(shape);
      this.animate(shape);
    }
    print.append("</svg>\n");

    // null or empty string means no file name was specified
    // defaults to the console
    if (file == null || file.trim().equals("") || file.equals("out")) {
      String printed = print.toString();
      System.out.println(printed);
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

  }

}
