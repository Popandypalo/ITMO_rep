package main.java.server.processor.commands.basic;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;

/**
 * Команда info: выводит информацию о коллекции.
 */
public class InfoCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        return new Response("OK", collectionManager.getInfo());
    }

    @Override
    public String getDescription() {
        return "info : вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
}
