package main.java.client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсер команд из скриптового файла.
 */
public class CommandParser {
    private final List<String> scriptHistory = new ArrayList<>();

    /**
     * Считывает команды из файла.
     *
     * @param fileName имя файла
     * @return список строк-команд
     * @throws IOException ошибка чтения файла
     */
    public List<String> parseScript(String fileName) throws IOException {
        if (scriptHistory.contains(fileName)) {
            throw new IOException("Обнаружена рекурсия при выполнении скрипта: " + fileName);
        }
        scriptHistory.add(fileName);

        List<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commands.add(line.trim());
            }
        }
        return commands;
    }

    /**
     * Очищает историю скриптов после выполнения.
     */
    public void clearHistory() {
        scriptHistory.clear();
    }
}