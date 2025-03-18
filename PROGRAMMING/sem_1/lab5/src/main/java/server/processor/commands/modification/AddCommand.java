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
 * Команда add: добавляет новый элемент в коллекцию.
 */
public class AddCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        try {
            Product product = ProductBuilder.buildProduct(request.getArguments());
            collectionManager.addProduct(product);
            return new Response("OK", "Продукт успешно добавлен.");
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Ошибка при добавлении продукта: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}