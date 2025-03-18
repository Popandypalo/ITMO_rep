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
 * Команда update: обновляет значение элемента коллекции по id.
 */
public class UpdateCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        try {
            Long id = Long.parseLong(request.getArguments().get("id"));
            boolean removed = collectionManager.removeById(id);
            if (!removed) {
                return new Response("ERROR", "Элемент с таким ID не найден.");
            }
    
            Product product = ProductBuilder.buildProduct(request.getArguments());
            collectionManager.addProductWithId(product, id); // Вставляем с прежним ID
            return new Response("OK", "Продукт с ID " + id + " успешно обновлён.");
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Некорректный формат ID.");
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Ошибка при обновлении продукта: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции по id";
    }
}
