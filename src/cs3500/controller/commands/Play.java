package cs3500.controller.commands;


import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for resuming/starting the view.
 */
public class Play implements Command{

  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public Play(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Plays the view.
   */
  @Override
  public void execute() {
    this.view.play();
  }
}
