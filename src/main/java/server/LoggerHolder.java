package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHolder {
    private static Logger instance;
    private LoggerHolder(){}
    public static Logger getInstance(){
        if(instance == null){
            instance = LogManager.getRootLogger();
        }
        return instance;
    }
}
