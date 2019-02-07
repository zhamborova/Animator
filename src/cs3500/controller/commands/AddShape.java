package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Function object for adding a shape.
 */
public class AddShape implements Command {

  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public AddShape(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Adds a shape with the given type and name to the model.
   * Updates the display of available shapes in the view.
   */
  @Override
  public void execute() {
    String addName = view.getAddShapeName();
    view.updateAvailableShapes("add", addName);
    model.addShape(addName, view.getAddType());
  }
}
