package server.commands;

import utils.exceptions.MustBeNotEmptyException;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Response execute(List<Object> params) {
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        try {
            int id = (int) params.get(0);
            if (collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            collectionManager.removeByIdFromCollection(id);
            return new Response("Element was successfully removed");

        } catch (MustBeNotEmptyException e) {
            return new Response("There is no route with this id");
        }
    }
}