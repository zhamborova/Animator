package cs3500.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

import cs3500.animation.RoIAnimation;
import cs3500.controller.IViewListener;
import cs3500.shape.Shape;

/**
 * A class representing a panel for an editable animation.
 */
public class EditPanel extends VisualPanel implements ActionListener {
  private RoIAnimation model;
  private int tick = 0;
  private int lastTick;
  private Timer t;
  private int speed;
  public boolean looping = false;
  public boolean forward = true;
  public JButton pause_resume;
  public JButton restart;
  public JButton start;
  public JButton loop;
  public JButton speedUp;
  public JButton slowDown;
  // opens the editor interface
  public JButton editor;
  private boolean started = false;
  private boolean editMode = false;
  public AvailableShapesPanel selection;
  public AddShapePanel addShapes;
  public EditFramePanel keyFrameInputs;
  public RemoveFramePanel removing;
  public JPanel layOut;
  // The purpose (also in README)
  // needed a way to pass the listener to actions that don't yet exist
  // the buttons and panels in the editor interface don't exist until the edit buttons pressed
  // I don't think having this field necessarily tightly couples the view to the controller,
  // because it doesn't
  // depend on its implementation, its just here to be passed to new actions.
  // The field is also a listener, not a controller in this case.
  public IViewListener controller;

  /**
   * Default constructor for a VisualPanel. Starts the timer, initializes the model, and sets the
   * background to white by default.
   *
   * @param model the model to initialize
   * @param speed the speed of the animation
   */
  public EditPanel(RoIAnimation model, int speed) {
    super(model, speed);
    // make this a composite view
    Objects.requireNonNull(model);
    this.speed = speed;
    this.model = model;
    this.lastTick = model.lastTick();
    this.setBackground(Color.WHITE);
    this.setSize(model.getWidth(), model.getHeight());
    // this.setLayout(null);
    this.t = new Timer(1000 / this.speed, this);

    pause_resume = new JButton();
    pause_resume.setPreferredSize(new Dimension(125, 40));
    // loc should be adjusted for canvas
    pause_resume.setLocation(model.getWidth(), model.getHeight());
    pause_resume.setText("PAUSE/RESUME");
    this.add(pause_resume);
    pause_resume.setActionCommand("pause resume");
    pause_resume.setVisible(true);


    loop = new JButton();
    loop.setPreferredSize(new Dimension(80, 40));
    loop.setLocation(model.getWidth(), model.getHeight());
    loop.setText("LOOP");
    this.add(loop);
    loop.setActionCommand("loop");
    loop.setVisible(true);


    start = new JButton();
    start.setPreferredSize(new Dimension(80, 40));
    start.setLocation(model.getWidth(), model.getHeight());
    start.setText("START");
    this.add(start);
    start.setActionCommand("start");
    start.setVisible(true);

    restart = new JButton();
    restart.setPreferredSize(new Dimension(90, 40));
    restart.setLocation(400, 600);
    restart.setText("RESTART");
    this.add(restart);
    restart.setActionCommand("restart");
    restart.setVisible(true);

    speedUp = new JButton();
    speedUp.setPreferredSize(new Dimension(120, 40));
    speedUp.setLocation(model.getWidth(), model.getHeight());
    speedUp.setText("SPEED UP");
    this.add(speedUp);
    speedUp.setActionCommand("speed up");
    speedUp.setVisible(true);

    slowDown = new JButton();
    slowDown.setPreferredSize(new Dimension(120, 40));
    slowDown.setLocation(model.getWidth(), model.getHeight());
    slowDown.setText("SLOW DOWN");
    this.add(slowDown);
    slowDown.setActionCommand("slow down");
    slowDown.setVisible(true);

    editor = new JButton();
    editor.setPreferredSize(new Dimension(100, 40));
    editor.setLocation(model.getWidth(), model.getHeight());
    editor.setText("EDIT");
    this.add(editor);
    editor.setActionCommand("edit");
    editor.setVisible(true);

    // sets up the frame for the editor window
    editor.addActionListener(
            e -> {
              // new window to appear for the editor
              JFrame frame = new JFrame("Editor");
              frame.setLocation(700,300);
              frame.setLayout(new BorderLayout());
              frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
              frame.setSize(1100,600);


              //outer most panel
              JPanel outer = new JPanel(new GridBagLayout());
              GridBagConstraints constraints = new GridBagConstraints();
              constraints.anchor = GridBagConstraints.WEST;
              constraints.insets = new Insets(10, 10, 10, 10);

              // shapes to be selected
              selection = new AvailableShapesPanel(model.getAnimations().entrySet());

              // for adding shapes
              addShapes = new AddShapePanel();

              // panel for editing keyframes
              keyFrameInputs = new EditFramePanel();

              // removing a keyframe
              removing = new RemoveFramePanel();

              layOut = new JPanel();
              layOut.setLayout(new BoxLayout(layOut, BoxLayout.PAGE_AXIS));
              layOut.add(keyFrameInputs);
              layOut.add(removing);
              layOut.add(addShapes);

              addShapes.setListeners(controller);
              keyFrameInputs.setListeners(controller);
              removing.setListeners(controller);

              constraints.gridx = 2;
              constraints.gridy = 2;
              outer.add(layOut, constraints);
              constraints.gridx = 0;
              constraints.gridy = 1;

              JLabel sel = new JLabel("Shapes");
              outer.add(sel);
              JLabel tic = new JLabel("Tick when opened: " +tick );
              outer.add(tic);
              outer.add(selection, constraints);
              frame.setResizable(true);
              frame.setVisible(true);

              //frame.pack();

              frame.add(outer);
            });
  }

  /**
   * Sets listeners to the panel. Lets any listeners that care know when the user wants to add or
   * modify a keyframe.
   *
   * @param buttons the listener
   */
  public void setListeners(IViewListener buttons) {
    // not great but i needed a way to pass the listener to the fields that don't exist
    // until the edit buttons pressed
    // or else theres a null pointer at the beginning
    // the null pointer doesn't break anything but it's annoying to have
    this.controller = buttons;
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buttons.action(e.getActionCommand());
      }
    };
    this.loop.addActionListener(listener);
    this.start.addActionListener(listener);
    this.restart.addActionListener(listener);
    this.pause_resume.addActionListener(listener);
    this.speedUp.addActionListener(listener);
  }

  /**
   * Enables or disables looping.
   */
  public void setLoop() {
    looping = !looping;
  }

  /**
   * Draws the shapes.
   * We know it is exactly the same in Visual Panel, but it is the only regard where these two
   * classes are similar.
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }


  /**
   * After each tick redraw the shapes.
   */
  public void actionPerformed(ActionEvent e) {
    // plays forward
    if (forward) {
      tick++;
      repaint();
      if (looping) {
        if (tick >= lastTick) {
          t.restart();
          tick = 0;
        }
      }
    }
    // rewinds the animation and restarts when tick is 0
    else if (!forward) {
      tick = 0;
      repaint();
      if (tick == 0) {
        forward = true;
        t.restart();
        lastTick = model.lastTick();
      }
    }
  }

  /**
   * Updates a copy of the shapes from the model to update the view.
   *
   * @return
   */
  public HashMap<String, Shape> updateShapes() {
    HashMap<String, Shape> animations = model.getAnimations();
    for (Map.Entry<String, Shape> entry : animations.entrySet()) {
      entry.getValue().updateCurrentState(tick);
    }
    return animations;
  }

  /**
   * Pauses or resumes the the timer.
   */
  public void pauseOrPlay() {
    if (t.isRunning()) {
      t.stop();
    } else if (!t.isRunning() && started) {
      t.start();
    }
  }

  public void start() {
    if(!t.isRunning() && !started) {
      t.start();
      started = true;
    }
  }

  /**
   * Rewinds or plays forward the animation.
   * If the animation was set to rewind, sets the last tick to 0.
   * If the animation was set to forward, sets the last tick to the last motion tick in the model.
   */
  public void toggleTime() {
    if (started) {
      tick = 0;
      t.restart();
    }
  }

  /**
   * Adjusts the speed of the animation.
   *
   * @param speed
   */
  public void toggleSpeed(int speed) {
    System.out.println(this.speed);
    this.speed =  this.speed + speed;
    this.t.setDelay(1000 / this.speed);
  }

  /**
   * Gets the name of the shape wants to add.
   *
   * @return
   */
  public String getAddShapeName() {
    return this.addShapes.getName();
  }

  /**
   * Gets the type of the shape the user wants to add.
   * @return
   */
  public String getAddType() {
    return this.addShapes.getType();
  }

  /**
   * Gets the name of the selected shape.
   */
  public String getSelectedShape() {
    return this.selection.getSelectedShape();
  }

  /**
   * Gets the user specified tick for a keyframe.
   *
   * @return
   */
  public int getT() {
    return this.keyFrameInputs.getT();
  }

  /**
   * Gets the user specified x posn for a keyframe.
   * @return
   */
  public int getXPosn() {
      return this.keyFrameInputs.getXPosn();
  }

  /**
   * Gets the user specified y posn for a keyframe.
   * @return
   */
  public int getYPosn() {
    return this.keyFrameInputs.getYPosn();
  }

  /**
   * Gets the user specified width for a keyframe.
   *
   * @return
   */
  public int getW() {
    return this.keyFrameInputs.getW();
  }

  /**
   * Gets the user specified height for a keyframe.
   *
   * @return
   */
  public int getH() {
    return this.keyFrameInputs.getH();
  }

  /**
   * Gets the user specified red for a keyframe.
   *
   * @return
   */
  public int getR() {
    return this.keyFrameInputs.getR();
  }

  /**
   * Gets the user specified green for a keyframe.
   *
   * @return
   */
  public int getG() {
    return this.keyFrameInputs.getG();
  }

  /**
   * Gets the user specified blue for a keyframe.
   *
   * @return
   */
  public int getB() {
    return this.keyFrameInputs.getB();
  }

  /**
   * Gets the tick for the keyframe to remove.
   *
   * @return
   */
  public int getTickRemove() {
    return this.removing.getTime();
  }

  /**
   * Updates the display of the available shapes according to the user action.
   *
   * @param action add or remove
   * @param name name of the shape
   */
  public void updateAvailableShapes(String action, String name) {
    this.selection.updateAvailableShapes(action, name);
  }

  /**
   * If a keyframe is added, updates the last tick of the view if the keyframe is now the last frame.
   *
   * @param tick
   */
  public void updateLastTick(int tick) {
    if (this.lastTick < tick) {
      this.lastTick = tick;
    }
  }

}


















