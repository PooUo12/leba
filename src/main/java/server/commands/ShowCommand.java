package server.commands;

import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public class ShowCommand extends AbstractCommand {

    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        return new Response(collectionManager.getCollection().toString());
    }
}