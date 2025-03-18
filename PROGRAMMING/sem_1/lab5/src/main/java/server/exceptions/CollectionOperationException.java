package main.java.server.exceptions;

/**
 * Ошибка при работе с коллекцией.
 */
public class CollectionOperationException extends Exception {
    public CollectionOperationException(String message) {
        super(message);
    }
}
