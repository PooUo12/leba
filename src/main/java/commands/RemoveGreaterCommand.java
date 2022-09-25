package commands;

import collections.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.RouteAsker;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterCommand extends AbstractCommand{

    private final CollectionManager collectionManager;
    private final RouteAsker routeAsker;

    public RemoveGreaterCommand(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("remove_greater {element}", "remove all items from the collection that exceed the specified");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Route routeToFind = new Route(
                    routeAsker.setId(),
                    routeAsker.askName(),
                    routeAsker.askCoordinates(),
                    routeAsker.askCreationDate(),
                    routeAsker.askLocationFrom(),
                    routeAsker.askLocationTo(),
                    routeAsker.askDistance()
            );
            List<Route> temp = new ArrayList<>();
            for (Route route: collectionManager.getCollection()) {
                if (route.compareTo(routeToFind) > 0) temp.add(route);
            }

            for (Route route: temp) {
                collectionManager.getCollection().remove(route);
            }

            Console.printLn("Greater route remove!");
            return true;
            } catch (WrongAmountOfElementsException e){
            Console.printError("This command calls without arguments");
        }   catch (IncorrectInputInScriptException ignored) {
        }
            return false;
    }
}

