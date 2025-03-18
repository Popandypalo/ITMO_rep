package main.java.server.exceptions;

/**
 * Ошибка записи в файл.
 */
public class FileWriteException extends Exception {
    public FileWriteException(String message) {
        super(message);
    }
}