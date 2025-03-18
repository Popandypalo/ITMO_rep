package main.java.server;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import main.java.common.Request;
import main.java.common.Response;
import main.java.server.exceptions.FileReadException;

/**
 * Главный класс для запуска сервера.
 */
public class ServerMain {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Необходимо указать имя файла с коллекцией (например: data.json)");
            return;
        }

        String fileName = args[0];
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(fileName);

        try {
            collectionManager.getProducts().addAll(fileManager.loadCollection());
            System.out.println("Коллекция успешно загружена. Элементов: " + collectionManager.getProducts().size());
        } catch (FileReadException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        RequestHandler requestHandler = new RequestHandler(collectionManager, fileManager);

        try (ServerSocket serverSocket = new ServerSocket(9876)) {
            System.out.println("Сервер запущен на порту 9876...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    Request request = (Request) in.readObject();
                    Response response = requestHandler.handleRequest(request);
                    out.writeObject(response);

                } catch (Exception e) {
                    System.out.println("Ошибка обработки запроса: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }
}

