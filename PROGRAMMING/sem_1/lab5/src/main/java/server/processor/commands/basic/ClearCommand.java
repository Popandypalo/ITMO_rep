package main.java.server.processor.commands.basic;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;

/**
 * Команда clear: очищает коллекцию.
 */
public class ClearCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        collectionManager.clearCollection();
        return new Response("OK", "Коллекция очищена.");
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}