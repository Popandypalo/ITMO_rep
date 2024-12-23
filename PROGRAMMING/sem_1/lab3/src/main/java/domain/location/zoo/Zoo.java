package main.java.domain.location.zoo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.domain.entity.Entity;
import main.java.domain.location.Location;
import main.java.domain.location.cage.Cage;
import main.java.domain.util.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Zoo implements Location {
    private final String name;
    private final List<Cage> cages;
    private final List<Entity> kiosks;

    public Zoo(String name) {
        this.name = name;
        this.cages = new ArrayList<>();
        this.kiosks = new ArrayList<>();
        Logger.log(Logger.LogType.CREATION, "Зоопарк '" + name + "' создан.");
    }

    public void addCage(Cage cage) {
        cages.add(cage);
        Logger.log(Logger.LogType.CREATION, "Клетка добавлена в зоопарк '" + name + "'.");
    }

    public void addKiosk(Entity kiosk) {
        kiosks.add(kiosk);
        Logger.log(Logger.LogType.CREATION, "Киоск добавлен в зоопарк '" + name + "'.");
    }

    public List<Cage> getCages() {
        return cages;
    }

    public List<Entity> getKiosks() {
        return kiosks;
    }

    public String getName() {
        return name;
    }

    public void saveToFile(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
            Logger.log(Logger.LogType.ACTIVITY, "Зоопарк '" + name + "' сохранен в файл: " + filePath);
        } catch (IOException e) {
            Logger.log(Logger.LogType.ERROR, "Ошибка при сохранении зоопарка: " + e.getMessage());
        }
    }


    @Override
    public String getDescription() {
        return "Зоопарк: " + name + ", содержит " + cages.size() + " клеток и " + kiosks.size() + " киосков.";
    }
}
