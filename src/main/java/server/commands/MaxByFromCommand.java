package server.commands;

import utils.collections.Route;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public class MaxByFromCommand extends AbstractCommand {

    public MaxByFromCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }


    public Response execute(List<Object> params) {
        if (collectionManager.getCollection().size() == 0) {
            return new Response("Collection is empty");
        }
        float max = -100000000;
        Route maxRoute = null;
        for (Route route : collectionManager.getCollection()) {
            float x = route.getLocationFrom().getx();
            float y = route.getLocationFrom().gety();
            if (x + y >= max) {
                max = x + y;
                maxRoute = route;
            }
        }
        rootLogger.info("Command " + this.getClass().getName() + " executed");

        return new Response(maxRoute.toString());
    }
}