package main.java.domain.character.animal;

import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;

public class AnimalFactory {

    public Animal createTiger(String name, ActionStrategy<Animal> strategy) {
        Logger.log(Logger.LogType.CREATION, "Создание тигра с именем: " + name);
        Logger.log("");
        return new Tiger(name, strategy);
    }

    public Animal createWolf(String name, ActionStrategy<Animal> strategy) {
        Logger.log(Logger.LogType.CREATION, "Создание волка с именем: " + name);
        Logger.log("");
        return new Wolf(name, strategy);
    }

}
