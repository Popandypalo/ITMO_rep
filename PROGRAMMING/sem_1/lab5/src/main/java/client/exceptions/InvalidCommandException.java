package main.java.client.exceptions;

/**
 * Исключение при неверной команде.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
