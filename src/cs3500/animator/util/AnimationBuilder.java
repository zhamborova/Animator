package cs3500.animator.util;

public interface AnimationBuilder<Doc> {
  /**
   * Constructs a final document.
   *
   * @return the newly constructed document
   */
  Doc build();


  /**
   * Specify the bunding box to be used for the cs3500.exportAnimation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> setBounds(int x, int y, int width, int height);

  /**
   * Adds a new cs3500.shape to the growing document.
   *
   * @param name The unique name of the cs3500.shape to be added. No cs3500.shape with this name
   *             should already exist.
   * @param type The type of cs3500.shape (e.g. "ellipse", "rectangle") to be added. The set of
   *             supported shapes is unspecified, but should include "ellipse" and "rectangle" as a
   *             minimum.
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> declareShape(String name, String type);

  /**
   * Adds a transformation to the growing document.
   *
   * @param name The name of the cs3500.shape (added with {@link AnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the cs3500.shape
   * @param y1   The initial y-position of the cs3500.shape
   * @param w1   The initial width of the cs3500.shape
   * @param h1   The initial height of the cs3500.shape
   * @param r1   The initial red color-value of the cs3500.shape
   * @param g1   The initial green color-value of the cs3500.shape
   * @param b1   The initial blue color-value of the cs3500.shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the cs3500.shape
   * @param y2   The final y-position of the cs3500.shape
   * @param w2   The final width of the cs3500.shape
   * @param h2   The final height of the cs3500.shape
   * @param r2   The final red color-value of the cs3500.shape
   * @param g2   The final green color-value of the cs3500.shape
   * @param b2   The final blue color-value of the cs3500.shape
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> addMotion(String name,
                                  int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                                  int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /**
   * Adds an individual keyframe to the growing document.
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
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g,
                                    int b);
}
