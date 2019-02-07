package cs3500.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.provider.ShapeType;
import cs3500.animation.RoIAnimation;
import cs3500.controller.IViewListener;
import cs3500.provider.Transformation;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;


/**
 * Represents the view of an animation with an editor interface.
 */
public class EditView extends JFrame implements IView {

  RoIAnimation model;
  EditPanel panel;
  int speed;

  /**
   * Default constructor for the VisualView.
   *
   * @param model contains the data of the animatio.
   * @param speed speed of the animation.
   */
  public EditView(RoIAnimation model, int speed) {
    super();
    Objects.requireNonNull(model);
    this.model = model;
    this.setLayout(new BorderLayout());
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.speed = speed;
    this.panel = new EditPanel(model, this.speed);
    JScrollPane scrollBar = new JScrollPane(panel, VERTICAL_SCROLLBAR_ALWAYS,
            HORIZONTAL_SCROLLBAR_ALWAYS);
    panel.setVisible(true);
    panel.setPreferredSize(new Dimension(model.getWidth() + 15, model.getHeight() + 5));
    this.setResizable(false);
    this.add(scrollBar, BorderLayout.CENTER);
    this.setResizable(true);
    this.setVisible(true);
  }

  /**
   * Override getPreferredSize to return dimensions of given canvas sizes.
   *
   * @return new Dimension
   */
  public Dimension getPreferredSize() {
    return new Dimension(this.model.getWidth(), this.model.getHeight());
  }

  @Override
  public void pauseOrPlay() {
    this.panel.pauseOrPlay();
  }

  @Override
  public void play() {
    this.pauseOrPlay();
  }

  @Override
  public void pause() {
    this.pauseOrPlay();
  }

  @Override
  public String exportAnimation() {
    return "";
  }

  @Override
  public void setListeners(IViewListener listener) {
    this.panel.setListeners(listener);
  }

  @Override
  public void setLoop() {
    this.panel.setLoop();
  }

  @Override
  public void toggleTime() {
    this.panel.toggleTime();
  }

  @Override
  public void toggleSpeed(int speed) {
    this.speed = this.speed + speed;
    this.panel.toggleSpeed(speed);
  }

  @Override
  public void start() {
    this.panel.start();
  }

  @Override
  public String getAddShapeName() {
    return this.panel.getAddShapeName();
  }

  @Override
  public String getAddType() {
    return this.panel.getAddType();
  }

  @Override
  public String getSelectedShape() {
    return this.panel.getSelectedShape();
  }

  @Override
  public int getT() {
    return this.panel.getT();
  }

  @Override
  public int getXPosn() {

    return this.panel.getXPosn();

  }

  @Override
  public int getYPosn() {
    return this.panel.getYPosn();
  }

  @Override
  public int getW() {
    return this.panel.getW();
  }

  @Override
  public int getH() {
    return this.panel.getH();
  }

  @Override
  public int getR() {
    return this.panel.getR();
  }

  @Override
  public int getG() {
    return this.panel.getG();
  }

  @Override
  public int getB() {
    return this.panel.getB();
  }

  @Override
  public int getTickRemove() {
    return this.panel.getTickRemove();
  }

  @Override
  public void updateAvailableShapes(String action, String name) {
    this.panel.updateAvailableShapes(action, name);
  }

  public void updateLastTick(int tick) {
    this.panel.updateLastTick(tick);
  }

  @Override
  public String getEdit() {
    return "";
  }

  @Override
  public void acceptTransformations(List<Transformation> transformations) {
    throw new UnsupportedOperationException("Not Applicabe");
  }

  @Override
  public void acceptIDsToTypes(Map<String, ShapeType> idsToTypes) {
    throw new UnsupportedOperationException("Not Applicabe");
  }

  @Override
  public void setKeyListener(KeyListener key) {
  }

  @Override
  public void setFocus() {
    this.requestFocus();
  }
}


