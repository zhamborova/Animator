package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object that pauses or resumes the view. Our original implementation.
 */
public class PauseResume implements Command {

  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public PauseResume(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Pauses or plays the view.
   */
  @Override
  public void execute() {
    view.pauseOrPlay();
  }
}
