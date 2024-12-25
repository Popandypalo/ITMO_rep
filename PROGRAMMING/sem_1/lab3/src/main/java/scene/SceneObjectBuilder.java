package main.java.scene;

import main.java.domain.character.animal.Animal;
import main.java.domain.character.animal.AnimalFactory;
import main.java.domain.character.human.Human;
import main.java.domain.character.human.HumanFactory;
import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.drink.DrinkFactory;
import main.java.domain.entity.drink.DrinkService;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.entity.kiosk.KioskFactory;
import main.java.domain.location.cage.Cage;
import main.java.domain.location.cage.CageFactory;
import main.java.domain.location.zoo.Zoo;
import main.java.domain.location.zoo.ZooFactory;
import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;

import java.util.List;

public class SceneObjectBuilder {

    public static Zoo buildZoo(String name, List<Cage> cages, List<Kiosk> kiosks) {
        Zoo zoo = ZooFactory.createZoo(name);
        cages.forEach(zoo::addCage);
        kiosks.forEach(zoo::addKiosk);
        return zoo;
    }

    public static Cage buildCage(String name, Animal animal) {
        return CageFactory.createCage(name, animal);
    }

    public static Kiosk buildKiosk(String name, List<String> shelfData, String drinkFilePath) {
        return KioskFactory.createKiosk(name, shelfData, drinkFilePath);
    }

    public static Animal buildAnimal(String type, String name, ActionStrategy<Animal> strategy) {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal animal = switch (type.toLowerCase()) {
            case "tiger" -> animalFactory.createTiger(name, strategy);
            case "wolf" -> animalFactory.createWolf(name, strategy);
            default -> throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        };
    
        return animal;
    }
    
    public static Human buildHuman(String name, boolean thirsty, boolean hot, ActionStrategy<Human> strategy) {
        int money = (int) ((Math.random() * (150 - 50)) + 50);
        Human human = new HumanFactory().createHuman(name, thirsty, hot, money);

        human.setStrategy(strategy);

        return human;
    }

    public static List<Drink> loadDrinks(String filePath) {
        try {
            return new DrinkFactory(new DrinkService()).createDrinksFromFile(filePath);
        } catch (Exception e) {
            Logger.log(Logger.LogType.ERROR, "Ошибка загрузки напитков: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}