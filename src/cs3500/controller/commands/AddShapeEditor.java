package cs3500.controller.commands;

import java.util.Objects;

import cs3500.provider.ShapeType;
import cs3500.adapting.AnimationModelAdapter;
import cs3500.view.IView;

/**
 * Function object for adding shapes in the provider editor view.
 */
public class AddShapeEditor implements Command {
  IView view;
  AnimationModelAdapter model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public AddShapeEditor(IView view, AnimationModelAdapter model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Adds a shape with the given name and type.
   * Updates the view to reflect these changes.
   */
  @Override
  public void execute() {
    String option = view.getAddType();
    System.out.println(option);
    ShapeType type;

    if (option.equals("Rectangle RB")) {
      type = ShapeType.RECTANGLE;
    }
    else {
      type = ShapeType.ELLIPSE;
    }

    this.model.addShape(view.getAddShapeName(), type);
    view.acceptIDsToTypes(model.getIDsToTypes());
    view.acceptTransformations(model.getTransformations());
  }
}
