package cs3500.provider;


/**
 * Represents an AnimationModel that is framed by a window.
 */
public interface WindowedAnimationModel extends AnimationModel {
  /**
   * Returns the width of the window for this WindowedAnimationModel.
   *
   * @return the width of the window for this WindowedAnimationModel.
   */
  int getWidth();

  /**
   * Returns the height of the window for this WindowedAnimationModel.
   *
   * @return the height of the window for this WindowedAnimationModel.
   */
  int getHeight();

  /**
   * Returns the location of the top left of this WindowedAnimationModel's window relative to the
   * computer screen.
   *
   * @return the location of the top left of this WindowedAnimationModel's window relative to the
   *         computer screen.
   */
  ImmutablePoint getLocation();
}
