import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.provider.ShapeType;
import cs3500.animation.Animation;
import cs3500.animation.IAnimation;
import cs3500.animation.RoIAnimation;
import cs3500.controller.Controller;
import cs3500.controller.IViewListener;
import cs3500.provider.Transformation;
import cs3500.view.IView;

import static org.junit.Assert.assertEquals;

/**
 * Tests to see if the controller calls the correct methods based on the given action. The mock view
 * has boolean values as switches that are by default false. When the correct method is called,
 * these values are set to true. The methods are tested for functionality, this tests for the
 * callback.
 */
public class ControllerTest {
  IAnimation model = new Animation(100, 100, 100, 100);
  MockView view;
  Controller controller;

  /**
   * Initializes initial variables.
   */
  public void run(IAnimation m) {
    m.addShape("R", "rectangle");
    m.addModifyFrame("R", 1, 10, 10, 50, 50, 255, 0, 0);
    m.addModifyFrame("R", 20, 50, 10, 50, 50, 0, 255, 0);
    view = new MockView(model.toRO(), 1);
    controller = new Controller(model, view);
  }

  // tests that the loop method is called
  @Test
  public void testLoop() {
    run(model);
    assertEquals(false, view.loop);
    controller.action("loop");
    assertEquals(true, view.loop);
  }

  // tests that the pause/resume method is called
  @Test
  public void pauseResume() {
    run(model);
    assertEquals(false, view.start);
    controller.action("pause resume");
    assertEquals(true, view.start);
    controller.action("pause resume");
    assertEquals(false, view.start);
  }

  // tests that start is called
  @Test
  public void start() {
    run(model);
    assertEquals(false, view.start);
    controller.action("start");
    assertEquals(true, view.start);
  }

  // tests that restart is called
  @Test
  public void restart() {
    run(model);
    assertEquals(false, view.toggleTime);
    controller.action("restart");
    assertEquals(true, view.toggleTime);
  }

  // tests that speed up is called and speed is increased
  // the value is checked because the value to increase is defined by the controller
  @Test
  public void speedUp() {
    run(model);
    assertEquals(1, view.speed);
    controller.action("speed up");
    assertEquals(2, view.speed);
  }

  // tests that slow down is called and speed is decreased
  // the value is checked because the value to decrease is defined by the controller
  @Test
  public void slowDown() {
    run(model);
    assertEquals(1, view.speed);
    controller.action("slow down");
    assertEquals(0, view.speed);
  }

  // tests that a shape is added
  @Test
  public void addShape() {
    run(model);
    assertEquals(false, view.getName);
    assertEquals(false, view.updateAvailable);
    assertEquals(1, model.getAnimations().size());
    controller.action("Add Shape");
    assertEquals(true, view.getName);
    assertEquals(true, view.updateAvailable);
    assertEquals(2, model.getAnimations().size());
    assertEquals(true, model.getAnimations().containsKey("E"));
  }

  // tests that a shape is removed
  @Test
  public void removeShape() {
    run(model);
    assertEquals(false, view.getSelectedShape);
    assertEquals(false, view.updateAvailable);
    assertEquals(1, model.getAnimations().size());
    controller.action("Remove Shape");
    assertEquals(true, view.getSelectedShape);
    assertEquals(true, view.updateAvailable);
    assertEquals(0, model.getAnimations().size());
    assertEquals(false, model.getAnimations().containsKey("R"));
  }

  // tests that a frame is added
  @Test
  public void addFrame() {
    run(model);
    assertEquals(false, view.getT);
    assertEquals(1, view.lastTick);
    assertEquals(2, model.getAnimations().get("R").getMotions().size());
    assertEquals(1, view.lastTick);
    controller.action("Add Mod Frame");
    assertEquals(true, view.getT);
    assertEquals(10, view.lastTick);
    assertEquals(3, model.getAnimations().get("R").getMotions().size());
  }

  // tests that a frame is removed
  @Test
  public void removeFrame() {
    run(model);
    assertEquals(false, view.getSelectedShape);
    assertEquals(false, view.getTickRemove);
    assertEquals(2, model.getAnimations().get("R").getMotions().size());
    controller.action("Remove Frame");
    assertEquals(true, view.getSelectedShape);
    assertEquals(true, view.getTickRemove);
    assertEquals(1, model.getAnimations().get("R").getMotions().size());
  }

  // tests that the listener is set and called
  @Test
  public void testListener() {
    run(model);
    view.setListeners(controller);
    assertEquals("loop", view.test.getActionCommand());
    assertEquals(false, view.loop);
    controller.action(view.test.getActionCommand());
    assertEquals(true, view.loop);
  }

  // tests that a null model crashses
  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    new Controller(null, view);
  }

  // tests that a null view crashses
  @Test(expected = IllegalArgumentException.class)
  public void nullView() {
    new Controller(model, null);
  }

  // tests that a null both crashses
  @Test(expected = IllegalArgumentException.class)
  public void bothNull() {
    new Controller(null, null);
  }

  /**
   * Represents a mock view that implements an IView. The mock view is used to check if the correct
   * methods are called by the controller in reponse to an action/callback.
   *
   * Alternatively to make this more flexible: we could make the fields booleans. Each time the
   * corresponding method is called it would allow us to see how many times the method was called
   * and if this matches our expectation. owever, for our purposes we kept it as booleans.
   */
  public class MockView implements IView {
    RoIAnimation model;
    int speed;
    int lastTick;
    boolean loop;
    boolean toggleTime;
    boolean start;
    boolean getName;
    boolean getType;
    boolean getSelectedShape;
    boolean getT;
    boolean getX;
    boolean getY;
    boolean getW;
    boolean getH;
    boolean getR;
    boolean getG;
    boolean getB;
    boolean getTickRemove;
    boolean updateAvailable;
    JButton test;

    /**
     * Default constructor for a mock view, sets fields to false by default
     *
     * @param model the model
     * @param speed the speed
     */
    public MockView(RoIAnimation model, int speed) {
      this.model = model;
      this.speed = speed;
      this.lastTick = 1;
      this.loop = false;
      this.toggleTime = false;
      this.start = false;
      this.getName = false;
      this.getType = false;
      this.getSelectedShape = false;
      this.getT = false;
      this.getX = false;
      this.getY = false;
      this.getW = false;
      this.getH = false;
      this.getR = false;
      this.getG = false;
      this.getB = false;
      this.getTickRemove = false;
      this.updateAvailable = false;
      this.test = new JButton();
      this.test.setActionCommand("loop");
    }

    @Override
    public void pauseOrPlay() {
      this.start = !this.start;
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public String exportAnimation() {
      return "";
    }

    @Override
    public void setListeners(IViewListener buttons) {
      ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          buttons.action(e.getActionCommand());
        }
      };
      test.addActionListener(listener);
    }

    @Override
    public void setLoop() {
      this.loop = true;
    }

    @Override
    public void toggleTime() {
      this.toggleTime = true;
    }

    @Override
    public void toggleSpeed(int speed) {
      this.speed = this.speed + speed;
    }

    @Override
    public void start() {
      this.start = true;
    }

    @Override
    public String getAddShapeName() {
      this.getName = true;
      return "E";
    }

    @Override
    public String getAddType() {
      this.getType = true;
      return "ellipse";
    }

    @Override
    public String getSelectedShape() {
      this.getSelectedShape = true;
      return "R";
    }

    @Override
    public int getT() {
      this.getT = true;
      return 10;
    }

    @Override
    public int getXPosn() {
      this.getX = true;
      return 10;
    }

    @Override
    public int getYPosn() {
      this.getY = true;
      return 10;
    }

    @Override
    public int getW() {
      this.getW = true;
      return 10;
    }

    @Override
    public int getH() {
      this.getH = true;
      return 10;
    }

    @Override
    public int getR() {
      this.getR = true;
      return 10;
    }

    @Override
    public int getG() {
      this.getG = true;
      return 10;
    }

    @Override
    public int getB() {
      this.getB = true;
      return 10;
    }

    @Override
    public int getTickRemove() {
      this.getTickRemove = true;
      return 20;
    }

    @Override
    public void updateAvailableShapes(String action, String name) {
      this.updateAvailable = true;
    }

    @Override
    public void updateLastTick(int tick) {
      this.lastTick = tick;
    }

    @Override
    public String getEdit() {
      return "";
    }

    @Override
    public void acceptTransformations(List<Transformation> transformations) {

    }

    @Override
    public void acceptIDsToTypes(Map<String, ShapeType> idsToTypes) {

    }

    @Override
    public void setKeyListener(KeyListener key) {

    }

    @Override
    public void setFocus() {

    }
  }
}