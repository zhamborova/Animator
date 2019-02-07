package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for removing a shape.
 */
public class RemoveShape implements Command {
  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public RemoveShape(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Removes the shape then updates the available shapes display in the view.
   */
  @Override
  public void execute() {
    String remName = view.getSelectedShape();
    view.updateAvailableShapes("remove", remName);
    model.removeShape(remName);
  }
}
