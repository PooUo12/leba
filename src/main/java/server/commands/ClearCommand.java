package server.commands;

import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;


public class ClearCommand extends AbstractCommand {

    public ClearCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        collectionManager.clearCollection();
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        return new Response("List was cleared");
    }
}
