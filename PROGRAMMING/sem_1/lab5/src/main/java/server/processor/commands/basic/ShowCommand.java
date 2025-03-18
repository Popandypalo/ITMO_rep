package main.java.server.processor.commands.basic;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.models.Product;

/**
 * Команда show: выводит все элементы коллекции.
 */
public class ShowCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        StringBuilder sb = new StringBuilder();
        for (Product p : collectionManager.getProducts()) {
            sb.append(p.toString()).append("\n");
        }
        return new Response("OK", sb.isEmpty() ? "Коллекция пуста." : sb.toString());
    }

    @Override
    public String getDescription() {
        return "show : вывести все элементы коллекции";
    }
}
