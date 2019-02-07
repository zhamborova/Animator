package cs3500.provider;

import java.util.Objects;

/**
 * Immutable class representing a location on the Cartesian plane. Preferred over
 * java.awt.ImmutablePoint because the fields can't be tampered with.
 */
public class ImmutablePoint {
  public final int x;
  public final int y;

  /**
   * Convenience method for creating a new ImmutablePoint.
   *
   * @param x The x-coordinate.
   * @param y The y-coordinate.
   * @return a new ImmutablePoint.
   */
  public static ImmutablePoint point(int x, int y) {
    return new ImmutablePoint(x, y);
  }

  /**
   * Constructs a ImmutablePoint at location (x, y).
   *
   * @param x The x-coordinate.
   * @param y The y-coordinate.
   */
  public ImmutablePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ImmutablePoint)) {
      return false;
    }

    ImmutablePoint that = (ImmutablePoint) o;
    return (this.x == that.x) && (this.y == that.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
