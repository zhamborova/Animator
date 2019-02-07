package cs3500.controller.commands;

import java.util.Objects;

import cs3500.adapting.AnimationModelAdapter;
import cs3500.view.IView;

/**
 * Function object for modifying a frame in the provider's edit view.
 */
public class ModifyFrameEditor implements Command {
  IView view;
  AnimationModelAdapter model;

  /**
   * Default constructor.
   * @param view
   * @param model uses the adapter here.
   */
  public ModifyFrameEditor(IView view, AnimationModelAdapter model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Modifies an existing keyframe. Updates the view to reflect the changes.
   */
  @Override
  public void execute() {
    String shape = view.getAddShapeName();
    model.modifyKeyFrame(shape, view.getT(), view.getXPosn(), view.getYPosn(), view.getW(),
            view.getH(), view.getR(), view.getG(), view.getB());
    view.updateLastTick(model.getEndTick());
    view.acceptIDsToTypes(model.getIDsToTypes());
    view.acceptTransformations(model.getTransformations());
  }
}
