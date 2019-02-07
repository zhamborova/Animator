package cs3500.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import cs3500.shape.Shape;

/**
 * A panel that represents a section of the UI that handles displaying and selecting the shapes
 * available to edit in the animation.
 */
public class AvailableShapesPanel extends JPanel {
  DefaultListModel<String> model;
  JList options;
  JScrollPane list;
  String highlight = "";

  /**
   * The default constructor for an AvailableShapesPanel.
   *
   * @param shapes in the animation.
   */
  public AvailableShapesPanel(Set<Map.Entry<String, Shape>> shapes) {
    this.model = setModel(shapes);
    JList listNames = new JList(model);
    this.options = listNames;
    this.list = new JScrollPane(listNames);
    this.add(list);


  }

  /**
   * Creates a default list model with a list of the names of the shapes in the animation.
   *
   * @param shapes in the animation
   */
  public static DefaultListModel<String> setModel(Set<Map.Entry<String, Shape>> shapes) {
    ArrayList<String> nameSet = new ArrayList<>();
    for (Map.Entry<String, Shape> entry : shapes) {
      nameSet.add(entry.getValue().getName());
    }
    String[] names = nameSet.toArray(new String[nameSet.size()]);
    DefaultListModel<String> model = new DefaultListModel<>();
    for (String s : names) {
      model.addElement(s);
    }
    return model;
  }

  /**
   * Gets the name of the shape the user has currently selected in the list.
   */
  public String getSelectedShape() {
    return options.getSelectedValue().toString();
  }

  /**
   * Updates the display of what shapes are available.
   *
   * @param action add or remove the given name of the shape to the display
   * @param name   name of the shape
   */
  public void updateAvailableShapes(String action, String name) {
    switch (action) {
      case "add":
        if (!this.model.contains(name)) {
          this.model.addElement(name);
        }
        break;
      case "remove":
        this.model.removeElement(name);
        break;
      default:
        break;
    }
  }

}
