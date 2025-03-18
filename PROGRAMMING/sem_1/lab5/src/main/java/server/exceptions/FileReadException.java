package main.java.server.exceptions;

/**
 * Ошибка чтения файла.
 */
public class FileReadException extends Exception {
    public FileReadException(String message) {
        super(message);
    }
}