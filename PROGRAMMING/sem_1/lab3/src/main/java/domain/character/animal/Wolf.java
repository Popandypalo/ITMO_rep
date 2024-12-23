package main.java.domain.character.animal;

import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;

public class Wolf extends Animal {

    public Wolf(String name, ActionStrategy<Animal> strategy) {
        super(name, strategy);
    }

    @Override
    public void speak(String words) {
        super.speak("выть: " + words);
    }

    @Override
    public void sleep() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " спит со своей стаей.");
    }

    @Override
    public void rest() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " отдыхает, лежа в тени деревьев.");
    }
}
