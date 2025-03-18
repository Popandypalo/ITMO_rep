package main.java.client;

import java.io.*;
import java.net.Socket;

import main.java.client.exceptions.ClientConnectionException;
import main.java.common.Request;
import main.java.common.Response;

/**
 * Клиентское соединение через сокеты.
 */
public class SocketClientConnection implements ClientConnection {
    private final String host;
    private final int port;

    public SocketClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Response sendRequest(Request request) throws ClientConnectionException {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(request);
            return (Response) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new ClientConnectionException("Ошибка соединения: " + e.getMessage());
        }
    }
}