package cs3500.controller.commands;

import cs3500.adapting.AnimationModelAdapter;
import cs3500.view.IView;

public class RemoveShapeEditor implements Command {
  IView view;
  AnimationModelAdapter model;

  public RemoveShapeEditor(IView view, AnimationModelAdapter model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void execute() {
    System.out.println(view.getSelectedShape());
    model.removeShape(view.getSelectedShape());
    view.acceptTransformations(model.getTransformations());
    view.acceptIDsToTypes(model.getIDsToTypes());





  }
}
