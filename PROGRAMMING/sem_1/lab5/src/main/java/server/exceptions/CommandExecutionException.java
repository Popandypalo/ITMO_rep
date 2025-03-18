package main.java.server.exceptions;

/**
 * Ошибка выполнения команды.
 */
public class CommandExecutionException extends Exception {
    public CommandExecutionException(String message) {
        super(message);
    }
}
