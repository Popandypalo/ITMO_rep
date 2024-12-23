package main.java.domain.character.animal;

import main.java.domain.util.Logger;

public class AnimalFactory {

    public Animal createTiger(String name) {
        Logger.log(Logger.LogType.CREATION, "Создание тигра с именем: " + name);
        Logger.log("");
        return new Tiger(name);
    }

    public Animal createWolf(String name) {
        Logger.log(Logger.LogType.CREATION, "Создание волка с именем: " + name);
        Logger.log("");
        return new Wolf(name);
    }
}
