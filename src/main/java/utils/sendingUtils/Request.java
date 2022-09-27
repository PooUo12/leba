package utils.sendingUtils;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 10L;
    private final Object arg;
    private final String name;


    public Request(Object arg, String name){
        this.arg = arg;
        this.name = name;
    }


    public Object getArg() {
        return arg;
    }

    public String getName() {
        return name;
    }
}
