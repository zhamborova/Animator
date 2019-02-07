package cs3500.view;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Represents the common functionality of print based views.
 */
public abstract class PrintBasedView implements IView {

  /**
   * Prints the view to the specified destination.
   *
   * @param filename destination of the printed animation
   * @param toPrint  the printt based animation to print
   */
  public String writeToFile(String filename, StringBuilder toPrint) {
    Writer writer = null;
    String printed = toPrint.toString();
    try {
      //
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),
              "utf-8"));
      writer.flush();
      writer.write(printed);
    } catch (IOException e) {
      e.getMessage();
    } finally {
      try {
        writer.close();
      } catch (IOException e) { //ignore//
      }
    }
    return printed;
  }
}
