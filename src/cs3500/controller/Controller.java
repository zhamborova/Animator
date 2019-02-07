package cs3500.controller;


import cs3500.animation.IAnimation;
import cs3500.view.IView;

/**
 * The controller is used to separate the View and the Model. It also acts as a way to separate from
 * a library specific interface, such as SVG and Swing.
 */
public class Controller implements IViewListener {
  IAnimation model;
  IView view;

  /**
   * Default constructor for a controller.
   */
  public Controller(IAnimation model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.view = view;
    this.view.setListeners(this);

  }

  /**
   * Decided that time is stored in the View instead of the controller. There are methods that the
   * controller can use to update the time in the view according to the event. This may more tightly
   * couple the view and the controller.
   *
   * @param e the action event.
   */
  @Override
  public void action(String e) {
    switch (e) {
      case "loop":
        view.setLoop();
        break;
      case "pause resume":
        view.pauseOrPlay();
        break;
      case "start":
        view.start();
        break;
      case "restart":
        view.toggleTime();
        break;
      case "speed up":
        // the controller decides how much the speed should change by
        // the method in the view is flexible for this
        int up = 1;
        view.toggleSpeed(up);
        break;
      case "slow down":
        int down = -1;
        view.toggleSpeed(down);
        break;
      case "Add Shape":
        String addName = view.getAddShapeName();
        view.updateAvailableShapes("add", addName);
        model.addShape(addName, view.getAddType());
        break;
      case "Remove Shape":
        String remName = view.getSelectedShape();
        view.updateAvailableShapes("remove", remName);
        model.removeShape(remName);
        break;
      case "Add Mod Frame":
        int t = view.getT();
        view.updateLastTick(t);
        model.addModifyFrame(view.getSelectedShape(), t, view.getXPosn(), view.getYPosn(),
                view.getW(), view.getH(), view.getR(), view.getG(), view.getB());
        break;
      case "Remove Frame":
        model.removeEvent(view.getSelectedShape(), view.getTickRemove());
        break;



      default:
        break;
    }
  }
}