package cs3500.provider;

/**
 * Represents a type of shape.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), ELLIPSE("ellipse");

  private final String description;

  /**
   * Constructs a ShapeType.
   *
   * @param description The description of the shape.
   */
  ShapeType(String description) {
    this.description = description;
  }

  /**
   * Returns a description of this ShapeType.
   *
   * @return a description of this ShapeType.
   */
  public String getDescription() {
    return this.description;
  }
}
