package client.validators;

import utils.exceptions.IncorrectInputInScriptException;
import utils.sendingUtils.Request;

import java.io.IOException;
import java.util.*;


/**
 * The Console class is a class that allows you to print to the console.
 */
public class Console {

    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    private final Scanner userScanner;
    private final RouteAsker routeAsker;
    private final RequestWriter requestWriter;

    public Console(RequestWriter requestWriter, Scanner userScanner, RouteAsker routeAsker) {
        this.userScanner = userScanner;
        this.routeAsker = routeAsker;
        this.requestWriter = requestWriter;
    }
    
    public void interactiveMode() throws IOException {
        String[] userCommand;
        try {
            List<Request> requests = null;
            while (requests == null || requests.isEmpty()) {
                Console.print(PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                CommandParser commandParser = new CommandParser(routeAsker);
                requests = commandParser.launchCommand(userCommand);
            }
            for (Request request: requests) {
                requestWriter.sendRequest(request);
            }


        } catch (NoSuchElementException exception) {
            Console.printError("User input not found!");
        } catch (IllegalStateException exception) {
            Console.printError("Unexpected error!");
        } catch (IncorrectInputInScriptException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Prints the given object to the standard output stream
     *
     * @param toOut The object to print.
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }


    /**
     * Prints the given object to the console
     *
     * @param toOut The object to print to the console.
     */
    public static void printLn(Object toOut) {
        System.out.println(toOut);
    }


    /**
     * Prints the error message to the console
     *
     * @param toOut The object to print out.
     */
    public static void printError(Object toOut) {
        System.out.println("Error: " + toOut);
    }

}

