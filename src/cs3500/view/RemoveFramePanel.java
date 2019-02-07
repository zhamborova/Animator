package cs3500.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import cs3500.controller.IViewListener;

/**
 * Represents a section of the UI that handles removing a keyframe. Separate from EditFramePanel
 * because the only additional information necessary is the time.
 */
public class RemoveFramePanel extends JPanel {
  JButton remove;
  JTextField t;

  /**
   * Default constructor for RemoveFramePanel.
   */
  public RemoveFramePanel() {
    this.t = new JTextField(4);
    JLabel t1 = new JLabel("Tick");
    this.add(t1);
    this.add(t);
    this.remove = new JButton("Remove Frame");
    this.remove.setActionCommand("Remove Frame");
    this.add(remove);
  }

  /**
   * Returns the time where the frame should be removed.
   */
  public int getTime() {
    return Integer.parseInt(this.t.getText());
  }


  /**
   * Sets listeners to the panel. Lets any listeners know when the user wants to remove a keyframe.
   */
  public void setListeners(IViewListener buttons) {
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buttons.action(e.getActionCommand());
      }
    };
    this.remove.addActionListener(listener);
  }
}
