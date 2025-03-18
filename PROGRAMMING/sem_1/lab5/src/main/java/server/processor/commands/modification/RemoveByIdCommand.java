package main.java.server.processor.commands.modification;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.server.processor.commands.basic.Command;

/**
 * Команда remove_by_id: удаляет элемент из коллекции по его id.
 */
public class RemoveByIdCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        try {
            Long id = Long.parseLong(request.getArguments().get("id"));
            boolean removed = collectionManager.removeById(id);
            return removed ? new Response("OK", "Элемент с ID " + id + " удалён.")
                           : new Response("ERROR", "Элемент с таким ID не найден.");
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Некорректный формат ID.");
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}