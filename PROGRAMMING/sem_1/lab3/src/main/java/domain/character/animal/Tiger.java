package main.java.domain.character.animal;

import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;

public class Tiger extends Animal {

    public Tiger(String name, ActionStrategy<Animal> strategy) {
        super(name, strategy);
    }

    @Override
    public void speak(String words) {
        super.speak("рычит: " + words);
    }

    @Override
    public void sleep() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " ложится спать в своей клетке.");
    }

    @Override
    public void rest() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " отдыхает в тени.");
    }
}
