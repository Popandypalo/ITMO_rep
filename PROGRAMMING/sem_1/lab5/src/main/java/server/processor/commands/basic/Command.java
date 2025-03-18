package main.java.server.processor.commands.basic;

import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.exceptions.CommandExecutionException;
import main.java.common.Request;

/**
 * Интерфейс для всех команд.
 */
public interface Command {
    /**
     * Выполняет команду.
     *
     * @param request            запрос клиента
     * @param collectionManager  менеджер коллекции
     * @param fileManager        менеджер файлов
     * @return результат выполнения команды
     * @throws CommandExecutionException ошибка выполнения
     */
    Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) throws CommandExecutionException;

    /**
     * @return краткое описание команды для help.
     */
    String getDescription();
}