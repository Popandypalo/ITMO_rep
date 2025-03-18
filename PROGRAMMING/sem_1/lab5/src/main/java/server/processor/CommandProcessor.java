package main.java.server.processor;

import main.java.common.Request;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.server.processor.commands.basic.Command;

import main.java.server.processor.commands.basic.*;
import main.java.server.processor.commands.modification.*;
import main.java.server.processor.commands.query.*;
import main.java.server.processor.commands.script.ExecuteScriptCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, обрабатывающий команды клиента.
 */
public class CommandProcessor {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    /**
     * Конструктор CommandProcessor.
     */
    public CommandProcessor(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        registerCommands();
    }

    /**
     * Регистрация всех команд.
     */
    private void registerCommands() {
        // Basic
        commandMap.put("help", new HelpCommand(this));
        commandMap.put("info", new InfoCommand());
        commandMap.put("show", new ShowCommand());
        commandMap.put("clear", new ClearCommand());
        commandMap.put("save", new SaveCommand());
        commandMap.put("exit", new ExitCommand());

        // Modification
        commandMap.put("add", new AddCommand());
        commandMap.put("update", new UpdateCommand());
        commandMap.put("remove_by_id", new RemoveByIdCommand());
        commandMap.put("add_if_max", new AddIfMaxCommand());
        commandMap.put("remove_greater", new RemoveGreaterCommand());
        commandMap.put("remove_all_by_price", new RemoveAllByPriceCommand());

        // Query
        commandMap.put("average_of_price", new AverageOfPriceCommand());
        commandMap.put("count_greater_than_price", new CountGreaterThanPriceCommand());
        commandMap.put("history", new HistoryCommand());

        // Script
        commandMap.put("execute_script", new ExecuteScriptCommand());
    }

    /**
     * Выполняет команду.
     *
     * @param request запрос клиента
     * @return результат выполнения команды
     * @throws CommandExecutionException ошибка выполнения команды
     */
    public String processCommand(Request request) throws CommandExecutionException {
        String commandName = request.getCommandName().toLowerCase();
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new CommandExecutionException("Неизвестная команда: " + commandName);
        }
        collectionManager.addToHistory(commandName);
        return command.execute(request, collectionManager, fileManager).getMessage();
    }

    /**
     * Генерирует справочную информацию по всем зарегистрированным командам.
     */
    public String generateHelpText() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Command> entry : commandMap.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue().getDescription()).append("\n");
        }
        return sb.toString();
    }
}