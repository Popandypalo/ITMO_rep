package main.java.domain.strategy.character.animal;

import main.java.domain.character.animal.Animal;
import main.java.domain.strategy.ActionStrategy;

public class RestStrategy implements ActionStrategy<Animal> {
    @Override
    public void execute(Animal animal) {
        animal.rest();
    }
}