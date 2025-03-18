package main.java.server.processor.commands.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import main.java.client.ClientConnection;
import main.java.client.CommandInputHandler;
import main.java.client.exceptions.ClientConnectionException;
import main.java.common.Request;
import main.java.common.Response;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.processor.commands.basic.Command;

/**
 * Команда execute_script: выполнить скрипт из указанного файла.
 * 
 * Так как клиент выполняет скрипт, на сервере дополнительной реализации не требуется.
 */
public class ExecuteScriptCommand implements Command {
    private static final Set<String> scriptHistory = new HashSet<>();

    @Override
    public Response execute(Request request, CollectionManager collectionManager, FileManager fileManager) {
        // Скрипт исполняется на клиенте, но сервер может вернуть подтверждение
        return new Response("OK", "Команда execute_script выполняется на стороне клиента.");
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : выполнить скрипт из указанного файла";
    }

    public static void executeScript(String fileName, ClientConnection connection, CommandInputHandler inputHandler) {
        if (scriptHistory.contains(fileName)) {
            System.out.println("Рекурсия обнаружена: " + fileName);
            return;
        }

        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            System.out.println("Ошибка: нет доступа к файлу " + fileName);
            return;
        }

        scriptHistory.add(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                System.out.println("> " + line);
                String[] parts = line.split("\\s+");
                String command = parts[0].toLowerCase();

                Map<String, String> args = new HashMap<>();

                switch (command) {
                    case "add":
                    case "add_if_max":
                    case "remove_greater":
                        args.putAll(inputHandler.readProductFields());
                        break;
                    case "update":
                        if (parts.length < 2) {
                            System.out.println("Нет id для update.");
                            continue;
                        }
                        args.put("id", parts[1]);
                        args.putAll(inputHandler.readProductFields());
                        break;
                    case "remove_by_id":
                    case "remove_all_by_price":
                    case "count_greater_than_price":
                        if (parts.length < 2) {
                            System.out.println("Нет аргумента.");
                            continue;
                        }
                        args.put("value", parts[1]);
                        break;
                    case "execute_script":
                        if (parts.length < 2) {
                            System.out.println("Нет имени файла.");
                            continue;
                        }
                        executeScript(parts[1], connection, inputHandler); // рекурсия
                        continue;
                }

                Request request = new Request(command, args);
                try {
                    Response response = connection.sendRequest(request);
                    System.out.println(response.getMessage());
                } catch (ClientConnectionException e) {
                    System.out.println("Ошибка соединения: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Ошибка выполнения команды: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения скрипта: " + e.getMessage());
        } finally {
            scriptHistory.remove(fileName);
        }
    }
}