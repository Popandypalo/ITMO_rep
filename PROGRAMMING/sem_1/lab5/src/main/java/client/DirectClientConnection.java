package main.java.client;

import main.java.client.exceptions.ClientConnectionException;
import main.java.common.Request;
import main.java.common.Response;
import main.java.server.RequestHandler;

/**
 * Прямое соединение клиента и сервера без сети.
 */
public class DirectClientConnection implements ClientConnection {
    private final RequestHandler requestHandler;

    public DirectClientConnection(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Response sendRequest(Request request) throws ClientConnectionException {
        try {
            return requestHandler.handleRequest(request);
        } catch (Exception e) {
            throw new ClientConnectionException("Ошибка связи с сервером: " + e.getMessage());
        }
    }
}