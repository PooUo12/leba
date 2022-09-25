package commands;

import collections.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.RouteAsker;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class AverageDistanceCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final RouteAsker routeAsker;

    public AverageDistanceCommand(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("average_of_distance", "print the average value of the distance field for all items in the collection");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     *
     *
     * @param argument The argument that the user enters command line.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            double sum = 0;
            long quantity = collectionManager.getCollection().size();
            for (Route route: collectionManager.getCollection()) {
                sum += route.getDistance();
            }
            System.out.println(sum / quantity);
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("Usage of (" + argument + ") in " + getName());
        }
        return false;
    }
}