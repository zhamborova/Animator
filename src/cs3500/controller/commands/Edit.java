package cs3500.controller.commands;

import java.util.Objects;

import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * Serves no real purpose. It helped as a non official way to check if commands were reached.
 * Acts as a placeholder when checking for the actual edit.
 */
public class Edit implements Command {

  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public Edit(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
  }

  /**
   * Prints the edit we want in console to make sure we did the right thing. really just helped us
   * walk through the code.
   */
  @Override
  public void execute() {
    String edit = view.getEdit();
    System.out.println(edit);
    }
}
