package commands;

import collections.Route;
import exceptions.MustBeNotEmptyException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

public class MaxByFromCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

            public MaxByFromCommand(CollectionManager collectionManager) {
                super("max_by_from", "output any object from the collection whose field from value is the maximum");
                this.collectionManager = collectionManager;
            }

            /**
             * Executes the command.
             * @return Command exit status.
             */
            @Override
            public boolean execute(String argument) {
                try {
                    if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
                    float max = - 100000000;
                    Route maxRoute = null;
                    for (Route route: collectionManager.getCollection()) {
                        float x = route.getLocationFrom().getx();
                        float y = route.getLocationFrom().gety();
                        if (x + y >= max) {
                            max = x + y;
                            maxRoute = route;
                        }
                    }
                    System.out.println(maxRoute);
                    return true;
                } catch (WrongAmountOfElementsException exception) {
                    Console.printLn("This command calls without arguments");
                }
                return true;
            }
        }