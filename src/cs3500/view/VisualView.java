package cs3500.view;

import java.awt.*;
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
 * Represents the class that implements the Visual View.
 */
public class VisualView extends JFrame implements IView {
  RoIAnimation model;

  /**
   * Default constructor for the VisualView.
   *
   * @param model contains the data of the animatio.
   * @param speed speed of the animation.
   */
  public VisualView(RoIAnimation model, int speed) {
    super();
    Objects.requireNonNull(model);
    this.model = model;
    this.setLayout(new BorderLayout());
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    VisualPanel panel = new VisualPanel(model, speed);
    JScrollPane scrollBar = new JScrollPane(panel, VERTICAL_SCROLLBAR_ALWAYS,
            HORIZONTAL_SCROLLBAR_ALWAYS);

    panel.setVisible(true);
    panel.setPreferredSize(new Dimension(model.getWidth() + 15, model.getHeight() + 5));
    panel.start();
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
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void play() {

  }

  @Override
  public void pause() {

  }

  @Override
  public String exportAnimation() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void setListeners(IViewListener buttons) {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void setLoop() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void toggleTime() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void toggleSpeed(int speed) {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void start() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getAddShapeName() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getAddType() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public String getSelectedShape() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getT() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getXPosn() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getYPosn() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getW() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getH() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getR() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getG() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getB() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public int getTickRemove() {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void updateAvailableShapes(String action, String name) {
    throw new UnsupportedOperationException("Not Applicable");
  }

  @Override
  public void updateLastTick(int tick) {
    throw new UnsupportedOperationException("Not Applicable");
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
    //ignored
  }

  @Override
  public void setFocus() {

  }
}










