package cs3500.controller.commands;

import java.util.Objects;

import cs3500.adapting.AnimationModelAdapter;
import cs3500.adapting.ViewAdapter;
import cs3500.view.IView;

/**
 * Function object for adding a frame in the provider Editor View.
 */
public class AddFrameEditor implements Command {
  IView view;
  AnimationModelAdapter model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public AddFrameEditor(IView view, AnimationModelAdapter model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Gets the tick to add the frame from the view. Adds a new empty keyframe into the model.
   * Then updates what the view has to reflect the changes.
   */
  @Override
  public void execute() {
    String shape = view.getSelectedShape();
    int t = view.getT();
    model.addKeyFrame(shape, t);
    view.acceptIDsToTypes(model.getIDsToTypes());
    view.acceptTransformations(model.getTransformations());
  }
}
