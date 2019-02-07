package cs3500.adapting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import cs3500.provider.ShapeType;
import cs3500.controller.IViewListener;
import cs3500.provider.EditorView;
import cs3500.provider.Transformation;
import cs3500.view.IView;

/**
 * Acts an adapter between our View interface and the provider's Editing view implementation.
 * Implementation varied a bit with this one. The way data was input and extracted from the View was
 * similar to our implementation, therefore adapting these methods were simple.
 */
public class ViewAdapter implements IView {
  EditorView view;

  /**
   * Default constructor.
   *
   * @param view implementation to adapt.
   */
  public ViewAdapter(EditorView view) {
    this.view = view;
  }

  /**
   * We had one method that handled pausing and playing since its just flipping a boolean.
   * However, the provider had separate methods. Throwing an exception is not ideal.
   */
  @Override
  public void pauseOrPlay() {
    throw new UnsupportedOperationException("choose one");
  }

  @Override
  public String exportAnimation() {
    return "";
  }

  @Override
  public void setListeners(IViewListener buttons) {
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        buttons.action(e.getActionCommand());
      }
    };

    this.view.addActionListener(listener);

  }

  @Override
  public void setLoop() {
    this.view.toggleLooping();
  }

  @Override
  public void toggleTime() {
    this.view.restart();
  }

  @Override
  public void toggleSpeed(int speed) {
    if (speed == 0) {
      return;
    }
    if (speed > 0) {
      this.view.increaseSpeed();
    }
    if (speed < 0) {
      this.view.decreaseSpeed();
    }
  }

  @Override
  public void start() {
    this.view.draw();
  }

  @Override
  public String getAddShapeName() {
    return this.view.getIdField();
  }

  @Override
  public String getAddType() {
    return this.view.getShapeOption();
  }

  @Override
  public String getSelectedShape() {
    return this.view.getIdField();
  }

  @Override
  public int getT() {
    String t = this.view.getTickField();
    if (t.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(t);
    }
  }

  @Override
  public int getXPosn() {
    String x = this.view.getXField();
      if (x.equals("")) {
        return 0;
      }
      else {
        return Integer.parseInt(this.view.getXField());
      }
  }

  @Override
  public int getYPosn() {
    String y = this.view.getYField();
    if (y.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getYField());
    }
  }

  @Override
  public int getW() {
    String w = this.view.getWidthField();
    if (w.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getWidthField());
    }
  }

  @Override
  public int getH() {
    String h = this.view.getHeightField();
    if (h.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getHeightField());
    }
  }

  @Override
  public int getR() {
    String r = this.view.getRedField();
    if (r.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getRedField());
    }
  }

  @Override
  public int getG() {
    String g = this.view.getGreenField();
    if (g.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getGreenField());
    }
  }

  @Override
  public int getB() {
    String b = this.view.getBlueField();
    if (b.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getBlueField());
    }
  }

  @Override
  public int getTickRemove() {
    String t = this.view.getTickField();
    if (t.equals("")) {
      return 0;
    }
    else {
      return Integer.parseInt(this.view.getTickField());
    }
  }

  /**
   * This method was specific to our implementation of how we displayed the shapes available to
   * edit.
   *
   * @param action add or remove the shape
   * @param name name of the shape
   */
  @Override
  public void updateAvailableShapes(String action, String name) {
    throw new UnsupportedOperationException("Irrelevant to implementation");
  }

  @Override
  public void updateLastTick(int tick) {
    this.view.acceptEndTick(tick);
  }

  @Override
  public void acceptTransformations(List<Transformation> transformations) {
   this.view.acceptTransformations(transformations);
  }

  @Override
  public void acceptIDsToTypes(Map<String, ShapeType> idsToTypes) {
    this.view.acceptIDsToTypes(idsToTypes);
  }

  @Override
  public void setKeyListener(KeyListener key) {
    this.view.addKeyListener(key);
  }

  @Override
  public void setFocus() {
    this.view.resetFocus();
  }

  @Override
  public String getEdit() {
    return this.view.getEditOption();
  }

  @Override
  public void play() {
    this.view.play();
  }

  @Override
  public void pause() {
    this.view.pause();
  }
}
