package managers;

import commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The CommandManager class is a singleton class that manages all the commands in the CLI
 */
public class CommandManager {

    private final List<ICommand> commands;
    private final ICommand addCommand;
    private final ICommand addIfMaxCommand;
    private final ICommand clearCommand;
    private final ICommand executeScriptCommand;
    private final ICommand exitCommand;
    private final ICommand infoCommand;
    private final ICommand saveCommand;
    private final ICommand showCommand;
    private final ICommand updateCommand;
    private final ICommand helpCommand;
    private final ICommand removeByIdCommand;
    private final ICommand removeGreaterCommand;
    private final ICommand averageDistanceCommand;
    private final ICommand maxByFromCommand;
    private final ICommand filterByDistanceCommand;
    private final ICommand headCommand;

    public CommandManager(ICommand addCommand, ICommand addIfMaxCommand, ICommand clearCommand, ICommand executeScriptCommand, ICommand exitCommand, ICommand infoCommand, ICommand saveCommand, ICommand showCommand, ICommand updateCommand, ICommand helpCommand, ICommand removeByIdCommand, ICommand removeGreaterCommand, ICommand averageDistanceCommand, ICommand maxByFromCommand, ICommand filterByDistanceCommand,  ICommand headCommand){
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.infoCommand = infoCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;
        this.helpCommand = helpCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.averageDistanceCommand = averageDistanceCommand;
        this.maxByFromCommand = maxByFromCommand;
        this.filterByDistanceCommand = filterByDistanceCommand;
        this.headCommand = headCommand;


        commands = new ArrayList<>(Arrays.asList(addCommand, addIfMaxCommand, clearCommand, executeScriptCommand,
                exitCommand, infoCommand, saveCommand, showCommand, updateCommand, helpCommand, removeByIdCommand, removeGreaterCommand,
                averageDistanceCommand, maxByFromCommand, filterByDistanceCommand, headCommand));



    }




    /**
     * If the command is not found, print a message to the user
     *
     * @param command The command that was not found.
     * @return Nothing.
     */
    public boolean noSuchCommand(String command) {
        Console.printLn("Command '" + command + "' was not found. Try to write 'help' for more info.");
        return false;
    }


    /**
     * Prints a list of all commands and their descriptions
     *
     * @param argument The argument passed to the help command.
     * @return The boolean value of the help command.
     */
    public boolean help(String argument) {
        if (!helpCommand.execute(argument)) {
            for (ICommand command : commands) {
                Console.printLn(command.getName() + " - " + command.getDescription());
            }
            return true;
        } else return false;
    }


    /**
     * This function is called when the user types "info" in the command line
     *
     * @param argument The argument to the command.
     * @return the response of right execution.
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }


    /**
     * This function is called when the user types "show" in the command line
     *
     * @param argument The argument to the command.
     * @return the response of right execution.
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }


    /**
     * This function is called when the user types "add" in the command line
     *
     * @param argument The argument to be added to the list.
     * @return the response of right execution.
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }


    /**
     * The update method takes a String argument and returns a boolean value
     *
     * @param argument The argument to the command.
     * @return the response of right execution
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }


    /**
     * Clear the current collection
     *
     * @param argument The argument to the command.
     * @return the response of right execution.
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }


    /**
     * The save function takes a string argument and returns a boolean value
     *
     * @param argument The argument to the command.
     * @return the response of right execution.
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }


    /**
     * The exit function is a method that takes a String argument.
     *
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }


    /**
     * Execute a script
     *
     * @param argument The argument to pass to the script.
     * @return the response of right execution.
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }


    /**
     * If the element is greater than the current maximum, then add it to the list
     *
     * @param argument The argument to be added to the list.
     * @return the response of right execution.
     */
    public boolean addIfMax(String argument) {
       return addIfMaxCommand.execute(argument);
    }

    /**
     * Remove an organization from the collection by its id
     *
     * @param argument The argument to pass to the command.
     * @return the response of right execution.
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
     public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

     public boolean averageDistance(String argument) {return averageDistanceCommand.execute(argument);}

     public boolean maxByFrom(String argument) {return maxByFromCommand.execute(argument);}

     public boolean filterByDistance(String argument) {return filterByDistanceCommand.execute(argument);}

     public boolean head(String argument) {return headCommand.execute(argument);}

    /**
     * This function is used to print out the string representation of the command manager
     *
     * @return The string "CommandManager (helper class for working with commands)"
     */
    @Override
    public String toString() {
        return "CommandManager (helper class for working with commands)";
    }

}
