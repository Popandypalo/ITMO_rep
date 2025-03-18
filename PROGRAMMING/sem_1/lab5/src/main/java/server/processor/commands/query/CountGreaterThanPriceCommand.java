package main.java.server.processor.commands.query;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.server.processor.commands.basic.Command;

/**
 * Команда count_greater_than_price: выводит количество элементов, значение поля price которых больше заданного.
 */
public class CountGreaterThanPriceCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException {
        String priceStr = request.getArguments().get("price");
        if (priceStr == null) throw new CommandExecutionException("Не указано значение price.");
        try {
            int price = Integer.parseInt(priceStr);
            long count = collectionManager.countGreaterThanPrice(price);
            return new Response("OK", "Количество элементов с ценой больше " + price + ": " + count);
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Некорректный формат цены.");
        }
    }

    @Override
    public String getDescription() {
        return "count_greater_than_price price : вывести количество элементов, значение поля price которых больше заданного";
    }
}
