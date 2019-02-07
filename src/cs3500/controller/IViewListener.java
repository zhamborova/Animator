package cs3500.controller;

/**
 * An interface that represents the functionality of a listener. The listener is listening for
 * action events represented by a String.
 */
public interface IViewListener {

  /**
   * An action event. Decided to use a String for flexibility to new implementation.
   *
   * @param e the action event.
   */
  void action(String e);
}
