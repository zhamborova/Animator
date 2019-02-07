package cs3500.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cs3500.animation.IAnimation;
import cs3500.controller.commands.Command;
import cs3500.view.IView;

/**
 * Represents the controller using a the command design pattern. Design choice: Originally had
 * applying the KeyListener and handling keys in the adapter for views. Initially there because we
 * didn't want to tightly couple Swing with the controller. We put it back here since ended up
 * dividing up the controller into different places.
 *
 * It isn't ideal that we implemented the KeyListener interface since it couples our controller
 * to Swing specific implementation.
 */
public class CommandController implements IViewListener, KeyListener {
  IAnimation model;
  IView view;
  CommandFactory factory;

  /**
   * Default constructor for a controller.
   */
  public CommandController(IAnimation model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.view = view;
    this.view.setListeners(this);
    this.view.setKeyListener(this);
    this.factory = new CommandFactory(this.view, this.model);
    factory.set();

  }

  @Override
  public void action(String e) {
    Command command = factory.apply(e);
    command.execute();
    view.setFocus();
  }

  /**
   * For looping. L key toggles the loop.
   */
  @Override
  public void keyTyped(KeyEvent e) {
  }

  /**
   * Used to adjust animation speed. Up key for increase, down for decrease.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    String l = Character.toString(e.getKeyChar());
    if (l.equals("l")) {
      Command command = factory.apply("loop");
      command.execute();
    }
    if (e.getKeyCode() == 38) {
      Command command = factory.apply("speed up");
      command.execute();
    }
    if (e.getKeyCode() == 40) {
      Command command = factory.apply("slow down");
      command.execute();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
