package utils.sendingUtils;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 11L;
    private final String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
