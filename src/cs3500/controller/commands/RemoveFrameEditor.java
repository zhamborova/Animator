package cs3500.controller.commands;

import java.util.Objects;

import cs3500.adapting.AnimationModelAdapter;
import cs3500.view.IView;

/**
 * Function object for removing a keyframe for the provider's editor view.
 */
public class RemoveFrameEditor implements Command {
  IView view;
  AnimationModelAdapter model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public RemoveFrameEditor(IView view, AnimationModelAdapter model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Removes the keyframe then updates the view to reflect the changes.
   */
  @Override
  public void execute() {
    model.removeKeyFrame(view.getSelectedShape(), view.getTickRemove());
    view.acceptIDsToTypes(model.getIDsToTypes());
    view.acceptTransformations(model.getTransformations());
  }
}
