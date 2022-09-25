package commands;

import collections.Route;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeadCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "first element of collection");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.getCollection().size() == 0) {
                System.out.println("Collection is empty");
                return false;
            }
            System.out.println(collectionManager.getCollection().get(0));
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("No arguments in " + getName());
        }
        return false;
    }
}

