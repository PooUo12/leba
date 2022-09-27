package server.commands;

import utils.collections.Route;
import utils.exceptions.MustBeNotEmptyException;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public class UpdateCommand extends AbstractCommand {

    public UpdateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        try {
            Route route = (Route) params.get(0);
            route.setId(collectionManager.generateNewIdForCollection());
            int id = (int) params.get(1);
            if (collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            collectionManager.replaceById(id, route);
            return new Response("Route was updated successfully");
        } catch (MustBeNotEmptyException e) {
            return new Response("There is no route with this id");
        }
    }
}