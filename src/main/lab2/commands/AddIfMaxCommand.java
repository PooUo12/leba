package commands;

import collections.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.RouteAsker;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class AddIfMaxCommand extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final RouteAsker routeAsker;

    public AddIfMaxCommand(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("add_if_max {element}", "add a new element to the collection if its value is greater than the value of the largest element in this collection");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     * If the collection is empty, the route is added to the collection.
     * Otherwise, the route is added to the collection only if its distance is higher
     * than the distance of the
     * route with the highest distance in the collection
     *
     * @param argument The argument that the user enters command line.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Route askerRoute = new Route(
                    routeAsker.setId(),
                    routeAsker.askName(),
                    routeAsker.askCoordinates(),
                    routeAsker.askCreationDate(),
                    routeAsker.askLocationFrom(),
                    routeAsker.askLocationTo(),
                    routeAsker.askDistance()
            );

            if(collectionManager.getCollection().size() == 0){
                collectionManager.addToCollection(askerRoute);
                Console.printLn("Route was added successfully");
                return true;
            }

            Route maxRoute = collectionManager.getCollection()
                    .stream()
                    .max(Comparator.comparing(Route::getDistance))
                    .orElseThrow(NoSuchElementException::new);

            if(askerRoute.getDistance() > maxRoute.getDistance()){
                collectionManager.addToCollection(askerRoute);
                Console.printLn("Route was added successfully");
            } else {
                Console.printLn("Route is not enough to add");
            }
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("This command calls without arguments");
        } catch (IncorrectInputInScriptException ignored) {
        }
        return false;
    }
}