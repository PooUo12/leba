package server.commands;

import utils.collections.Route;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.ArrayList;
import java.util.List;

public class FilterByDistanceCommand extends AbstractCommand {

    public FilterByDistanceCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        float fDistance = (float) params.get(0);
        List<Route> temp = new ArrayList<>();
        for (Route route : collectionManager.getCollection()) {
            if (fDistance == route.getDistance()) temp.add(route);
        }
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        return new Response(temp.toString());
    }
}

