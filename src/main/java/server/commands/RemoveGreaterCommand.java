package server.commands;

import utils.collections.Route;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        Route route = (Route) params.get(0);
        route.setId(collectionManager.generateNewIdForCollection());
        List<Route> temp = new ArrayList<>();
        for (Route rt : collectionManager.getCollection()) {
            if (rt.compareTo(route) > 0) temp.add(route);
        }

        for (Route rt : temp) {
            collectionManager.getCollection().remove(rt);
        }
        rootLogger.info("Command " + this.getClass().getName() + " executed");

        return new Response(temp.size() + " elements were removed");
    }
}

