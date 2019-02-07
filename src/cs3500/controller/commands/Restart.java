package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for restarting the view.
 */
public class Restart implements Command {

  IView view;
  IAnimation model;

  /**
   * The default constructor.
   *
   * @param view
   * @param model
   */
  public Restart(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Restarts the animation.
   */
  @Override
  public void execute() {
    view.toggleTime();
  }
}
