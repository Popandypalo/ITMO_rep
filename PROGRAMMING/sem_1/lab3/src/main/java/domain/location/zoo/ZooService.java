package main.java.domain.location.zoo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.domain.util.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ZooService {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveZoo(Zoo zoo, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(zoo, writer);
            Logger.log(Logger.LogType.ACTIVITY, "Зоопарк '" + zoo.getName() + "' сохранен в файл: " + filePath);
        } catch (IOException e) {
            Logger.log(Logger.LogType.ERROR, "Ошибка при сохранении зоопарка: " + e.getMessage());
        }
    }

    public static Zoo loadZoo(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Zoo zoo = gson.fromJson(reader, Zoo.class);
            Logger.log(Logger.LogType.ACTIVITY, "Зоопарк загружен из файла: " + filePath);
            return zoo;
        } catch (IOException e) {
            Logger.log(Logger.LogType.ERROR, "Ошибка при загрузке зоопарка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
