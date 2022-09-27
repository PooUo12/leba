package server.commands;

import utils.collections.Route;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public class AverageDistanceCommand extends AbstractCommand {

    public AverageDistanceCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        try {
            double sum = 0;
            long quantity = collectionManager.getCollection().size();
            for (Route route : collectionManager.getCollection()) {
                sum += route.getDistance();
            }
            return new Response("Average: " + sum / quantity);
        } catch (ArithmeticException e) {
            return new Response("Can't count average: list is empty");
        }
    }
}