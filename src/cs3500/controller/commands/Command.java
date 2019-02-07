package cs3500.controller.commands;

/**
 * Represents a ccmmand. A command is essentially a runnable.
 * All commands have one purpose initiated
 * by the execute method.
 */
public interface Command {

  /**
   * Executes the command.
   */
  void execute();
}
