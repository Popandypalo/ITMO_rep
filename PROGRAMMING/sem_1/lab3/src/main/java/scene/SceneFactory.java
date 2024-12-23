package main.java.scene;

import main.java.domain.character.animal.Animal;
import main.java.domain.character.human.Human;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.location.cage.Cage;
import main.java.domain.location.zoo.Zoo;
import main.java.domain.location.zoo.ZooService;
import main.java.domain.strategy.ActionStrategy;
import main.java.domain.strategy.character.animal.RestStrategy;
import main.java.domain.strategy.character.animal.SleepStrategy;
import main.java.domain.strategy.character.human.HumanStrategy;
import main.java.domain.strategy.character.human.OrderHotDrinkStrategy;
import main.java.domain.strategy.character.human.OrderPreferredDrinkStrategy;
import main.java.domain.strategy.character.human.OrderThirstyDrinkStrategy;
import main.java.domain.util.Logger;

import java.util.List;
import java.util.Random;

public class SceneFactory {
private static final Random random = new Random();

    private static final List<HumanStrategy> humanStrategies = List.of(
            new OrderThirstyDrinkStrategy(null),
            new OrderHotDrinkStrategy(null),
            new OrderPreferredDrinkStrategy(null, List.of("Вода", "Кола"))
    );

    private static final List<ActionStrategy<Animal>> animalStrategies = List.of(
            new SleepStrategy(),
            new RestStrategy()
    );
    public static Scene createNewScene(String zooName, String protagonistName, String drinkFilePath) {
        try {
            Logger.log(Logger.LogType.CREATION, "Создание новой сцены...");
            Logger.log("");

            Kiosk kiosk = SceneObjectBuilder.buildKiosk(
                    "Киоск с напитками",
                    List.of("Полка 1: Газированная вода с сиропом, Вода"),
                    drinkFilePath
            );

            HumanStrategy humanStrategy = humanStrategies.get(random.nextInt(humanStrategies.size()));
            ActionStrategy<Animal> animalStrategy_for_wolf = animalStrategies.get(random.nextInt(animalStrategies.size()));
            ActionStrategy<Animal> animalStrategy_for_tiger = animalStrategies.get(random.nextInt(animalStrategies.size()));

            humanStrategy.setKiosk(kiosk);

            Cage tigerCage = SceneObjectBuilder.buildCage("Клетка с тигром", 
                    SceneObjectBuilder.buildAnimal("tiger", "Тигр", animalStrategy_for_tiger));
            Cage wolfCage = SceneObjectBuilder.buildCage("Клетка с волком", 
                    SceneObjectBuilder.buildAnimal("wolf", "Волк", animalStrategy_for_wolf));

            Zoo zoo = SceneObjectBuilder.buildZoo(zooName, List.of(tigerCage, wolfCage), List.of(kiosk));

            Human protagonist = SceneObjectBuilder.buildHuman(protagonistName, random.nextBoolean(), random.nextBoolean(), humanStrategy);

            return new Scene("Посещение зоопарка " + protagonistName, zoo, protagonist);
        } catch (Exception e) {
            Logger.log(Logger.LogType.ERROR, "Ошибка при создании сцены: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Scene loadScene(String filePath, String protagonistName, ActionStrategy<Human> humanStrategy) {
        try {
            Logger.log(Logger.LogType.GENERAL, "Загрузка сохраненной сцены...");

            Zoo zoo = ZooService.loadZoo(filePath);

            Human protagonist = SceneObjectBuilder.buildHuman(protagonistName, true, true, humanStrategy);

            return new Scene("Восстановленная сцена с " + protagonistName, zoo, protagonist);
        } catch (Exception e) {
            Logger.log(Logger.LogType.ERROR, "Ошибка при загрузке сцены: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
