package main.java.server.processor.commands.basic;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.server.exceptions.FileWriteException;

/**
 * Команда save: сохраняет коллекцию в файл.
 */
public class SaveCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        try {
            fileManager.saveCollection(collectionManager.getProducts());
            return new Response("OK", "Коллекция успешно сохранена.");
        } catch (FileWriteException e) {
            throw new CommandExecutionException("Ошибка сохранения: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
