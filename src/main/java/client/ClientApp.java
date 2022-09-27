package client;

import client.validators.RequestWriter;
import client.validators.Console;
import client.validators.RouteAsker;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientApp {

    int i = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HostDefiner hostDefiner = new HostDefiner(scanner);
        InetAddress host;
        int port;
        RouteAsker routeAsker = new RouteAsker(scanner);
        ClientApp clientApp = new ClientApp();

        while (true) {
            try {
                host = hostDefiner.setHost();
                port = hostDefiner.setPort();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong port");
            } catch (UnknownHostException e) {
                System.out.println("Wrong host");
            }
        }
        clientApp.serverConnect(scanner, host, port, routeAsker);
    }

    private void serverConnect(Scanner scanner, InetAddress host, int port, RouteAsker routeAsker) {
        ObjectInputStream serverReader;
        ObjectOutputStream serverWriter;
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host, port))) {
            serverWriter = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            serverReader = new ObjectInputStream(socketChannel.socket().getInputStream());
            RequestWriter requestWriter = new RequestWriter(serverWriter, serverReader);
            Console console = new Console(requestWriter, scanner, routeAsker);
            while (true) {
                console.interactiveMode();
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());


        } catch (IOException ex) {
            System.out.println("Trying to connect again...");
            if (i > 5) {
                System.out.println("Too many attempts closing client");
                System.exit(0);
            }
            i += 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            serverConnect(scanner, host, port, routeAsker);
        }
    }
}
