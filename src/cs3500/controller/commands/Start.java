package cs3500.controller.commands;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for starting the view.
 */
public class Start implements Command {

  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public Start(IView view, IAnimation model) {
    this.view = view;
    this.model = model;
  }

  /**
   * Starts the animation.
   */
  @Override
  public void execute() {
    view.start();
  }
}
