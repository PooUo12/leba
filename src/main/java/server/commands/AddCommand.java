package server.commands;

import utils.collections.Route;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;


public class AddCommand extends AbstractCommand{

    public AddCommand(CollectionManager collectionManager){
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        Route route = (Route) params.get(0);
        route.setId(collectionManager.generateNewIdForCollection());
        collectionManager.addToCollection(route);
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        return new Response("Added successfully");

    }
}