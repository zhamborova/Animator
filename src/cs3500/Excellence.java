package cs3500;


import java.io.FileReader;
import java.io.IOException;

import cs3500.animation.Animation;
import cs3500.animation.IAnimation;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.controller.CommandController;
import cs3500.view.IView;
import cs3500.view.ViewFactory;

/**
 * Main class for using an animation.
 */
public final class Excellence {
  /**
   * Acts as the entry point for the animation program.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    String filename = "";
    String view = "";
    int speed = 30;
    String dest = "";
    Readable file;
    IView v;
    IAnimation model;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          i++;
          filename = args[i];
          break;
        case "-view":
          i++;
          view = args[i];
          break;
        case "-speed":
          i++;
          speed = Integer.parseInt(args[i]);
          break;
        case "-out":
          i++;
          dest = args[i];
          break;
        default:
          break;
      }
    }

    ViewFactory factory = new ViewFactory();
    AnimationReader reader = new AnimationReader();
    AnimationBuilder<IAnimation> builder = new Animation.Builder();

    file = giveFile(filename);

    if (file == null) {
      throw new IllegalArgumentException();
    }

    model = reader.parseFile(file, builder);

    v = factory.factoryView(view, dest, speed, model);
    v.exportAnimation();

    CommandController control = new CommandController(model, v);
  }

  /**
   * Outputs a readable file for the animation reader to parse.
   *
   * @param filename
   * @return
   */
  private static Readable giveFile(String filename) {
    try {
      return new FileReader(filename);
    } catch (IOException e) {
    }
    return null;
  }
}






