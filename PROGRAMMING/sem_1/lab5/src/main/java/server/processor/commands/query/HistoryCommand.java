package main.java.server.processor.commands.query;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.processor.commands.basic.Command;

/**
 * Команда history: выводит последние 6 команд (без их аргументов).
 */
public class HistoryCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        String history = String.join("\n", collectionManager.getCommandHistory());
        return new Response("OK", history.isEmpty() ? "История пуста." : history);
    }

    @Override
    public String getDescription() {
        return "history : вывести последние 6 команд (без их аргументов)";
    }
}