package main.java.server.processor.commands.basic;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.processor.CommandProcessor;

/**
 * Команда help: выводит справку по доступным командам.
 */
public class HelpCommand implements Command {
    private final CommandProcessor processor;

    public HelpCommand(CommandProcessor processor) {
        this.processor = processor;
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        String helpText = processor.generateHelpText();
        return new Response("OK", helpText);
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
