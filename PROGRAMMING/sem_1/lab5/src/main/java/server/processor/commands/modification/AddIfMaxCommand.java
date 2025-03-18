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
 * Команда add_if_max: добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.
 */
public class AddIfMaxCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        try {
            Product product = ProductBuilder.buildProduct(request.getArguments());
            if (collectionManager.getMaxProduct().map(max -> product.compareTo(max) > 0).orElse(true)) {
                collectionManager.addProduct(product);
                return new Response("OK", "Продукт добавлен, так как он больше максимального.");
            } else {
                return new Response("OK", "Продукт не добавлен, так как он не превышает максимальный.");
            }
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Ошибка при добавлении продукта: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
