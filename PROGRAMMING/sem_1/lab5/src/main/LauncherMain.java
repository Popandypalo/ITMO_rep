package main;

import main.java.client.ClientMain;
import main.java.client.DirectClientConnection;
import main.java.server.CollectionManager;
import main.java.server.FileManager;
import main.java.server.RequestHandler;

/**
 * Запускатель сервера и клиента.
 */
public class LauncherMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: java LauncherMain <data.json> <mode>");
            System.out.println("mode: direct или socket");
            return;
        }

        String fileName = args[0];
        String mode = args[1];

        if (mode.equalsIgnoreCase("direct")) {
            System.out.println("Режим: прямое программное соединение.");

            CollectionManager collectionManager = new CollectionManager();
            FileManager fileManager = new FileManager(fileName);

            try {
                collectionManager.getProducts().addAll(fileManager.loadCollection());
                System.out.println("Коллекция загружена.");
            } catch (Exception e) {
                System.out.println("Ошибка загрузки: " + e.getMessage());
            }

            RequestHandler handler = new RequestHandler(collectionManager, fileManager);
            DirectClientConnection connection = new DirectClientConnection(handler);
            ClientMain client = new ClientMain(connection);
            client.run();

        } else if (mode.equalsIgnoreCase("socket")) {
            System.out.println("Сетевой режим. Сервер и клиент должны запускаться отдельно.");
            // Тут нада подрехтить чутка и нормалды для 6 лабы
        } else {
            System.out.println("Неизвестный режим.");
        }
    }
}
