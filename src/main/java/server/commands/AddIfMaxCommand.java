package server.commands;

import utils.collections.Route;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class AddIfMaxCommand extends AbstractCommand {

    public AddIfMaxCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }


    public Response execute(List<Object> params) {

        Route route = (Route) params.get(0);
        route.setId(collectionManager.generateNewIdForCollection());
        if (collectionManager.getCollection().size() == 0) {
            collectionManager.addToCollection(route);
            return new Response("Route added successfully");
        }

        Route maxRoute = collectionManager.getCollection()
                .stream()
                .max(Comparator.comparing(Route::getDistance))
                .orElseThrow(NoSuchElementException::new);
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        if (route.getDistance() > maxRoute.getDistance()) {
            collectionManager.addToCollection(route);
            return new Response("Route added successfully");
        } else {
            return new Response("Route wasn't added: not max");
        }

    }
}