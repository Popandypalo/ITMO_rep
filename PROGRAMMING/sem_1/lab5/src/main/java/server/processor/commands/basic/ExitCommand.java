package main.java.server.processor.commands.basic;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;

/**
 * Команда exit: завершает работу сервера.
 */
public class ExitCommand implements Command {
    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        System.exit(0); // Можно доработать под graceful shutdown
        return new Response("OK", "Завершение работы сервера."); // unreachable
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
