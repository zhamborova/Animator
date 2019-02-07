package cs3500.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;



import cs3500.controller.IViewListener;

/**
 * A panel that represents a section of the UI that handles adding and removing shapes.
 */
public class AddShapePanel extends JPanel {
  private final JTextField name;
  private final JTextField type;
  private final JButton add;
  private final JButton remove;

  /**
   * The Default constructor for an AddShapePanel.
   */
  public AddShapePanel() {
    this.name = new JTextField(10);
    this.type = new JTextField(10);
    this.add = new JButton("Add Shape");
    this.remove = new JButton("Remove Shape");
    this.add.setActionCommand("Add Shape");
    this.remove.setActionCommand("Remove Shape");
    JLabel name1 = new JLabel("Name");
    this.add(name1);
    this.add(name);
    JLabel type1 = new JLabel("Type: rectangle/ellipse");
    this.add(type1);
    this.add(type);
    this.add(add);
    this.add(remove);
  }

  /**
   * Returns the name of the shape to be added.
   */
  public String getName() {
    return this.name.getText();
  }

  /**
   * Returns the type of the shape to be added.
   */
  public String getType() {
    return this.type.getText();
  }

  /**
   * Sets an IViewListener. This allows any listener to know when a shape was added or removed and
   * decide if they want to act on it.
   */
  public void setListeners(IViewListener buttons) {
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buttons.action(e.getActionCommand());
      }
    };
    this.add.addActionListener(listener);
    this.remove.addActionListener(listener);
  }
}
