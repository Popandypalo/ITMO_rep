package main.java.domain.location.cage;

import main.java.domain.character.animal.Animal;
import main.java.domain.location.Location;
import main.java.domain.util.Logger;

public class Cage implements Location {
    private final String name;
    private final Animal animal;

    public Cage(String name, Animal animal) {
        this.name = name;
        this.animal = animal;
        Logger.log(Logger.LogType.CREATION, "Клетка '" + name + "' создана для животного: " + animal.getName());
        Logger.log("");
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Клетка: " + name + ", содержит животное: " + animal.getName();
    }
}
