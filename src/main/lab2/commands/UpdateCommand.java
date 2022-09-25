package commands;
//замена конструктора
import collections.Route;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.RouteAsker;

/**
 * The class is used to update the value of the collection element whose id is equal to the given one
 */
public class UpdateCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final RouteAsker routeAsker;

    public UpdateCommand(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("update id {element}", "update the value of the collection element whose id is equal to the given one");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     * The function takes an argument, which is the id of the route that will be updated.
     * If the argument is empty, the function throws an exception.
     * If the argument is not empty, the function checks if the route with the given id exists.
     * If it doesn't exist, the function throws an exception.
     * If the route exists, the function asks the user to enter the new values for the
     * route.
     * The function then replaces the old route with the new one.
     * The function returns true if the route was updated successfully
     *
     * @param argument The argument that the user entered.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(argument.isEmpty()) throw new WrongAmountOfElementsException();
            int id = Integer.parseInt(argument);
            if(collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            Route newRoute = new Route(
                    id,
                    routeAsker.askName(),
                    routeAsker.askCoordinates(),
                    routeAsker.askCreationDate(),
                    routeAsker.askLocationFrom(),
                    routeAsker.askLocationTo(),
                    routeAsker.askDistance()
            );
            collectionManager.replaceById(id, newRoute);
            Console.printLn("Route was updated successfully");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("No arguments in " + getName());
        } catch (NumberFormatException e) {
            Console.printError("The id have to be an Integer value");
        } catch (MustBeNotEmptyException e) {
            Console.printError("There is no route with this id");
        } catch (IncorrectInputInScriptException ignored) {
        }
        return false;
    }
}