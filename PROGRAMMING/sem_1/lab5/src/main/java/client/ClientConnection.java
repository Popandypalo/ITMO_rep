package main.java.client;

import main.java.client.exceptions.ClientConnectionException;
import main.java.common.Request;
import main.java.common.Response;

/**
 * Интерфейс соединения клиента с сервером.
 */
public interface ClientConnection {
    Response sendRequest(Request request) throws ClientConnectionException, ClientConnectionException;
}
