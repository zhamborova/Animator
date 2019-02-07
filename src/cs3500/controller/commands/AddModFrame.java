package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for adding a frame.
 */
public class AddModFrame implements Command {
  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public AddModFrame(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Adds a new keyframe with the specified values at the specified time. Updates last tick if need.
   */
  @Override
  public void execute() {
    int t = view.getT();
    view.updateLastTick(t);
    model.addModifyFrame(view.getSelectedShape(), t, view.getXPosn(), view.getYPosn(),
            view.getW(), view.getH(), view.getR(), view.getG(), view.getB());
  }
}
