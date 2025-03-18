package main.java.server;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.processor.CommandProcessor;
import main.java.server.exceptions.CommandExecutionException;

/**
 * Обрабатывает входящие запросы клиента.
 */
public class RequestHandler {
    private final CommandProcessor commandProcessor;

    public RequestHandler(CollectionManager collectionManager, FileManager fileManager) {
        this.commandProcessor = new CommandProcessor(collectionManager, fileManager);
    }

    /**
     * Обрабатывает запрос клиента.
     *
     * @param request запрос
     * @return ответ сервера
     */
    public Response handleRequest(Request request) {
        try {
            String result = commandProcessor.processCommand(request);
            return new Response("OK", result);
        } catch (CommandExecutionException e) {
            return new Response("ERROR", e.getMessage());
        } catch (Exception e) {
            return new Response("ERROR", "Неожиданная ошибка: " + e.getMessage());
        }
    }
}