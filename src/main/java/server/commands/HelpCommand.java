package server.commands;

import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;


public class HelpCommand extends AbstractCommand {

    public HelpCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }


    public Response execute(List<Object> params) {
        rootLogger.info("Command " + this.getClass().getName() + " executed");
        return new Response(CommandsList.ADD + CommandsList.ADD_IF + CommandsList.CLEAR + CommandsList.SCRIPT + CommandsList.EXIT + CommandsList.INFO + CommandsList.SHOW + CommandsList.UPDATE + CommandsList.HELP + CommandsList.REMOVE_BY + CommandsList.REMOVE_GREATER + CommandsList.AVERAGE + CommandsList.MAX_FROM + CommandsList.FILTER + CommandsList.HEAD);
    }
}