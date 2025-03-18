package main.java.server.processor.commands.modification;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.server.processor.ProductBuilder;
import main.java.server.processor.commands.basic.Command;
import main.models.Product;

/**
 * Команда remove_greater: удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreaterCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        try {
            Product product = ProductBuilder.buildProduct(request.getArguments());
            int initialSize = collectionManager.getProducts().size();
            collectionManager.getProducts().removeIf(p -> p.compareTo(product) > 0);
            int removedCount = initialSize - collectionManager.getProducts().size();
            return new Response("OK", "Удалено элементов: " + removedCount);
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Ошибка при удалении элементов: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}