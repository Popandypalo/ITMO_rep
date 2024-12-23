package main.java.scene;

import main.java.domain.character.Character;
import main.java.domain.character.animal.Animal;
import main.java.domain.character.human.Human;
import main.java.domain.entity.Entity;
import main.java.domain.location.cage.Cage;
import main.java.domain.location.zoo.Zoo;
import main.java.domain.util.Logger;

public class Scene {
    private final String description;
    private final Zoo zoo;
    private final Character protagonist;

    public Scene(String description, Zoo zoo, Character protagonist) {
        this.description = description;
        this.zoo = zoo;
        this.protagonist = protagonist;
    }

    public String getDescription() {
        return description;
    }

    public void play() {
        Human human = (Human) protagonist;

        Logger.log(Logger.LogType.ACTIVITY, "Сцена начинается: " + description);

        for (Cage cage : zoo.getCages()) {
            Animal animal = (Animal) cage.getAnimal();
            human.observe(animal.getName());
            if (animal.getStrategy() != null) {
                animal.performAction();
            } else {
                Logger.log(Logger.LogType.ERROR, "У " + animal.getName() + " нет стратегии для выполнения действия.");
            }
        }

        for (Entity kiosk : zoo.getKiosks()) {
            human.approach(kiosk);

            human.complainAboutHeat();

            if (human.getStrategy() != null) {
                human.performAction();
            } else {
                Logger.log(Logger.LogType.ERROR, "У " + human.getName() + " нет стратегии для выполнения действия.");
            }
        }

        Logger.log(Logger.LogType.ACTIVITY, "Сцена завершена.");
    }

    public Character getProtagonist() {
        return protagonist;
    }

}
