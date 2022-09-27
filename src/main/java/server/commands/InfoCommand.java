package server.commands;

import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;


public class InfoCommand extends AbstractCommand {

    public InfoCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {

        rootLogger.info("Command " + this.getClass().getName() + " executed");
        return new Response(collectionManager.infoAboutCollection());

    }
}