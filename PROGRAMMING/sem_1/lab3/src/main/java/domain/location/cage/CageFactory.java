package main.java.domain.location.cage;

import main.java.domain.character.animal.Animal;
import main.java.domain.util.Logger;

public class CageFactory {

    public static Cage createCage(String name, Animal animal) {
        Logger.log(Logger.LogType.CREATION, "Создание клетки: " + name + " для животного: " + animal.getName());
        return new Cage(name, animal);
    }
}
