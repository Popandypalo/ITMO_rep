package main.java.domain.strategy.character.animal;

import java.util.Random;

import main.java.domain.character.animal.Animal;
import main.java.domain.strategy.ActionStrategy;

public class PerformRandomActionStrategy implements ActionStrategy<Animal> {
    private final ActionStrategy<Animal>[] strategies;

    @SafeVarargs
    public PerformRandomActionStrategy(ActionStrategy<Animal>... strategies) {
        this.strategies = strategies;
    }

    @Override
    public void execute(Animal animal) {
        int index = new Random().nextInt(strategies.length);
        strategies[index].execute(animal);
    }

}