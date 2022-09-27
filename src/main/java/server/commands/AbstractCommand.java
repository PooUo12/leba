package server.commands;

import org.apache.logging.log4j.Logger;
import server.LoggerHolder;
import server.collection.CollectionManager;
import utils.sendingUtils.Response;

import java.util.List;

public abstract class AbstractCommand{

    protected final CollectionManager collectionManager;
    static final Logger rootLogger = LoggerHolder.getInstance();


    public AbstractCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    public abstract Response execute(List<Object> params);
}