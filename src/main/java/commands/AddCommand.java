package commands;

import collections.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.RouteAsker;


/**
 * The class is responsible for adding an organization to the collection
 */
public class AddCommand extends AbstractCommand{

    private final CollectionManager collectionManager;
    private final RouteAsker routeAsker;

    public AddCommand(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("add {element}", "add a new element to the collection");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }



    /**
     * The function adds an organization to the collection
     *
     * @param argument The argument that the user entered.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new Route(
                    routeAsker.setId(),
                    routeAsker.askName(),
                    routeAsker.askCoordinates(),
                    routeAsker.askCreationDate(),
                    routeAsker.askLocationFrom(),
                    routeAsker.askLocationTo(),
                    routeAsker.askDistance()
            ));
            Console.printLn("New route was created successfully");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("This command calls without arguments");
        } catch (IncorrectInputInScriptException ignored) {
        }
        return false;
    }
}