package main.java.client.exceptions;

/**
 * Ошибка парсинга команды.
 */
public class CommandParsingException extends Exception {
    public CommandParsingException(String message) {
        super(message);
    }
}
