package main.java.server.processor.commands.query;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.processor.commands.basic.Command;

/**
 * Команда average_of_price: выводит среднее значение поля price для всех элементов коллекции.
 */
public class AverageOfPriceCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        double avg = collectionManager.getAveragePrice();
        return new Response("OK", "Средняя цена: " + avg);
    }

    @Override
    public String getDescription() {
        return "average_of_price : вывести среднее значение поля price для всех элементов коллекции";
    }
}