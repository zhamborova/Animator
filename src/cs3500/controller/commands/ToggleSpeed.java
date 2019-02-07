package cs3500.controller.commands;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for toggling the speed. Used to increase or decrease the speed.
 */
public class ToggleSpeed implements Command {
  IView view;
  IAnimation model;
  int speed;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   * @param speed amount to adjust speed by.
   */
  public ToggleSpeed(IView view, IAnimation model, int speed) {
    this.view = view;
    this.model = model;
    this.speed = speed;
  }

  /**
   * Changes speed.
   */
  @Override
  public void execute() {
    view.toggleSpeed(speed);
  }
}
