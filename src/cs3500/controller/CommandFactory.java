package cs3500.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import cs3500.adapting.AnimationModelAdapter;
import cs3500.adapting.ExtendView;
import cs3500.adapting.ViewAdapter;
import cs3500.animation.IAnimation;
import cs3500.controller.commands.AddFrameEditor;
import cs3500.controller.commands.AddModFrame;
import cs3500.controller.commands.AddShape;
import cs3500.controller.commands.AddShapeEditor;
import cs3500.controller.commands.Command;
import cs3500.controller.commands.Edit;
import cs3500.controller.commands.Loop;
import cs3500.controller.commands.ModifyFrameEditor;
import cs3500.controller.commands.Pause;
import cs3500.controller.commands.PauseResume;
import cs3500.controller.commands.Play;
import cs3500.controller.commands.RemoveFrame;
import cs3500.controller.commands.RemoveFrameEditor;
import cs3500.controller.commands.RemoveShape;
import cs3500.controller.commands.RemoveShapeEditor;
import cs3500.controller.commands.Restart;
import cs3500.controller.commands.Start;
import cs3500.controller.commands.ToggleSpeed;
import cs3500.view.IView;

/**
 * Creates the commands based on the String input.
 */
public class CommandFactory {
  Map<String, BiFunction<IView, IAnimation, Command>> commands;
  IView view;
  IAnimation model;

  /**
   * Default constructor.
   *
   * @param view
   * @param model
   */
  public CommandFactory(IView view, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    this.view = view;
    this.model = model;
    this.commands = new HashMap<>();
  }

  /**
   * Sets the available command objects to their String commands.
   */
  public void set() {
    //commands for our implementationn
    commands.put("loop", (IView v, IAnimation m) -> { return new Loop(v, m);});
    commands.put("pause resume", (IView v, IAnimation m) -> { return new PauseResume(v, m);});
    commands.put("start", (IView v, IAnimation m) -> { return new Start(v, m);});
    commands.put("restart", (IView v, IAnimation m) -> { return new Restart(v, m);});
    commands.put("speed up", (IView v, IAnimation m) -> { return new ToggleSpeed(v, m, 1);});
    commands.put("slow down", (IView v, IAnimation m) -> { return new ToggleSpeed(v, m, -1);});
    commands.put("Add Shape", (IView v, IAnimation m) -> { return new AddShape(v, m);});
    commands.put("Remove Shape", (IView v, IAnimation m) -> { return new RemoveShape(v, m);});
    commands.put("Add Mod Frame", (IView v, IAnimation m) -> { return new AddModFrame(v, m);});
    commands.put("Remove Frame", (IView v, IAnimation m) -> { return new RemoveFrame(v, m);});

    //commands added for provider's implementation
    commands.put("Play Button", (IView v, IAnimation m) -> { return new Play(v, m);});
    commands.put("Pause Button", (IView v, IAnimation m) -> { return new Pause(v, m);});
    commands.put("Restart Button", (IView v, IAnimation m) -> { return new Restart(v, m);});
    commands.put("Do Edit Button", (IView v, IAnimation m) -> {
      //the provider didn't separate out each edit so we have to differentiate here
      String com = view.getEdit();
      Command edit = new Edit(v, m);
      AnimationModelAdapter mod = new AnimationModelAdapter(model);
      switch (com) {
        case "Add key frame RB":
          edit =  new AddFrameEditor(v, mod);
          break;
        case "Modify key frame RB":
          edit = new ModifyFrameEditor(v, mod);
          break;
        case "Remove key frame RB":
          edit =  new RemoveFrameEditor(v, mod);
          break;
        case "Add shape RB":
          edit = new AddShapeEditor(v, mod);
          break;
        case "Remove shape RB":
          edit = new RemoveShapeEditor(v, mod);
          break;
        default:
          break;
      }
      return edit;});
  }

  /**
   * Applies the command based on the String command.
   *
   * @param com given command
   * @return
   */
  public Command apply(String com) {
    Objects.requireNonNull(com);
    BiFunction<IView, IAnimation, Command> cmd = this.commands.getOrDefault(com, null);
    Command command = cmd.apply(this.view, this.model);
    return command;
  }
}