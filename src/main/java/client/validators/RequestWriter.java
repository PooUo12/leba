package client.validators;

import utils.sendingUtils.Request;
import utils.sendingUtils.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RequestWriter {
    private final ObjectOutputStream serverWriter;
    private final ObjectInputStream serverReader;
    public RequestWriter(ObjectOutputStream serverWriter, ObjectInputStream serverReader){
        this.serverWriter = serverWriter;
        this.serverReader = serverReader;
    }

    public void sendRequest(Request request) throws IOException, ClassNotFoundException {
        serverWriter.writeObject(request);
        getResponse();
    }

    public void getResponse() throws IOException, ClassNotFoundException {
        Response response = (Response) serverReader.readObject();
        System.out.println(response.getMessage());
    }


}
