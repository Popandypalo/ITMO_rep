package main.java.client;


import java.util.Scanner;
import java.util.Map;

import main.java.client.exceptions.ClientConnectionException;
import main.java.common.Request;
import main.java.common.Response;
import main.java.server.processor.commands.script.ExecuteScriptCommand;

/**
 * Главный класс клиента для взаимодействия с сервером.
 */
public class ClientMain {
    private final ClientConnection connection;
    private final Scanner scanner;
    private final CommandInputHandler inputHandler;

    /**
     * Конструктор клиента.
     *
     * @param connection соединение (Direct или Socket)
     */
    public ClientMain(ClientConnection connection) {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
        this.inputHandler = new CommandInputHandler(scanner);
    }

    /**
     * Запуск клиента.
     */
    public void run() {
        System.out.println("Клиент запущен. Введите команду (help):");

        while (true) {
            try {
                System.out.print("> ");
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String command = parts[0].toLowerCase();
                Map<String, String> argsMap = new java.util.HashMap<>();

                if (command.equals("exit")) {
                    System.out.println("Завершение клиента.");
                    break;
                }

                switch (command) {
                    case "help":
                    case "info":
                    case "show":
                    case "clear":
                    case "save":
                    case "history":
                    case "average_of_price":
                        sendRequest(new Request(command, argsMap));
                        break;

                    case "add":
                    case "add_if_max":
                    case "remove_greater":
                        argsMap.putAll(inputHandler.readProductFields());
                        sendRequest(new Request(command, argsMap));
                        break;

                    case "update":
                        if (parts.length < 2) {
                            System.out.println("Ошибка: требуется id.");
                            continue;
                        }
                        argsMap.put("id", parts[1]);
                        argsMap.putAll(inputHandler.readProductFields());
                        sendRequest(new Request(command, argsMap));
                        break;

                    case "remove_by_id":
                    case "remove_all_by_price":
                    case "count_greater_than_price":
                        if (parts.length < 2) {
                            System.out.println("Ошибка: требуется аргумент.");
                            continue;
                        }
                        argsMap.put("value", parts[1]);
                        sendRequest(new Request(command, argsMap));
                        break;

                    case "execute_script":
                        if (parts.length < 2) {
                            System.out.println("Ошибка: укажите имя файла скрипта.");
                            continue;
                        }
                        ExecuteScriptCommand.executeScript(parts[1], connection, inputHandler);
                        break;

                    default:
                        System.out.println("Неизвестная команда. Введите 'help'.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка клиента: " + e.getMessage());
            }
        }
    }

    /**
     * Отправка запроса и обработка ответа.
     *
     * @param request запрос
     */
    private void sendRequest(Request request) {
        try {
            Response response = connection.sendRequest(request);
            System.out.println(response.getMessage());
        } catch (ClientConnectionException e) {
            System.out.println("Ошибка соединения: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка выполнения команды: " + e.getMessage());
        }
    }
}