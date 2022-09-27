package server;

import org.apache.logging.log4j.Logger;
import server.collection.CollectionManager;
import server.collection.FileManager;
import sun.misc.Signal;
import utils.sendingUtils.Request;
import utils.sendingUtils.Response;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerApp {

    private final CollectionManager collectionManager = new CollectionManager();

    static final Logger rootLogger = LoggerHolder.getInstance();

    public static void main(String[] args) {

        int port = 6869;
        ServerApp serverApp = new ServerApp();
        serverApp.start(port);

    }

    private void start(int port) {
        CommandDefiner commandDefiner = new CommandDefiner(collectionManager);
        FileManager fileManager = new FileManager();
        collectionManager.setCollection(fileManager.readCollection());
        rootLogger.info("Collection added");
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            setupShutDownWork(serverSocket) ;
            setupSignalHandler();

            rootLogger.info("Server is listening on port " + port);

            clientConnect(commandDefiner, serverSocket);

        }catch (BindException e){
            System.out.println("Binding error");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clientConnect(CommandDefiner commandDefiner, ServerSocket serverSocket) throws IOException {
        Request userRequest;
        while (true) {
            Socket server = serverSocket.accept();

            rootLogger.info("New client connected");

            try (ObjectInputStream clientReader = new ObjectInputStream(server.getInputStream());
                 ObjectOutputStream clientWriter = new ObjectOutputStream(server.getOutputStream())) {
                do {
                    userRequest = (Request) clientReader.readObject();
                    Response response = commandDefiner.define(userRequest);
                    clientWriter.writeObject(response);
                    clientWriter.flush();
                } while (true);
            } catch (ClassNotFoundException e) {
                server.close();
                e.printStackTrace();
            } catch (EOFException | SocketException e) {
                server.close();
                rootLogger.info("Client disconnected");
            } catch (IOException e){
                rootLogger.error("Response send error");
            }
        }
    }

    private void setupSignalHandler() {
        Signal.handle(new Signal("TSTP"), signal -> collectionManager.save());
        rootLogger.info("Saving collection");
    }

    private void setupShutDownWork(ServerSocket server) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                rootLogger.info("Emergency exit");
                collectionManager.save();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}