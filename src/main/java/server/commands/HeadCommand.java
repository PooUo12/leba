package server.commands;

import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public class HeadCommand extends AbstractCommand {

    public HeadCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        if (collectionManager.getCollection().size() == 0) {
            return new Response("Collection is empty");
        } else {
            return new Response(collectionManager.getCollection().get(0).toString());
        }
    }
}

