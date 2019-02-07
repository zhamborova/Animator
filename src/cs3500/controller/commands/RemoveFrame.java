package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for removing a keyframe.
 */
public class RemoveFrame implements Command {
  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public RemoveFrame(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Removes the keyframe at the given tick for the given shape.
   */
  @Override
  public void execute() {
    model.removeEvent(view.getSelectedShape(), view.getTickRemove());
  }
}
