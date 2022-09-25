package commands;

import collections.Route;
//import exceptions.IncorrectInputInScriptException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.RouteAsker;

import java.util.ArrayList;
import java.util.List;

public class FilterByDistanceCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final RouteAsker routeAsker;

    public FilterByDistanceCommand(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("filter_by_distance distance", "output elements whose distance field value is equal to the specified");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;

}
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            float fDistance = routeAsker.askDistance();
            List<Route> temp = new ArrayList<>();
            for (Route route : collectionManager.getCollection()) {
                if (fDistance == route.getDistance()) temp.add(route);
            }
            System.out.println(temp);
            return true;
        } catch (WrongAmountOfElementsException | IncorrectInputInScriptException e){
            Console.printError("This command calls without arguments");
        }
        return false;
        }
    }

