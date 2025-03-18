package main.java.server.processor.commands.modification;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.server.processor.commands.basic.Command;

/**
 * Команда remove_all_by_price: удаляет из коллекции все элементы, значение поля price которых равно заданному.
 */
public class RemoveAllByPriceCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        String priceStr = request.getArguments().get("price");
        if (priceStr == null) throw new CommandExecutionException("Не указано значение price.");
        try {
            int price = Integer.parseInt(priceStr);
            int initialSize = collectionManager.getProducts().size();
            collectionManager.removeAllByPrice(price);
            int removedCount = initialSize - collectionManager.getProducts().size();
            return new Response("OK", "Удалено элементов с ценой " + price + ": " + removedCount);
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Некорректный формат цены.");
        }
    }

    @Override
    public String getDescription() {
        return "remove_all_by_price price : удалить из коллекции все элементы, значение поля price которых эквивалентно заданному";
    }
}