package main.java.client.exceptions;

/**
 * Ошибка соединения клиента с сервером.
 */
public class ClientConnectionException extends Exception {
    public ClientConnectionException(String message) {
        super(message);
    }
}

