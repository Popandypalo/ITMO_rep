package main.java.domain.character.animal;

import main.java.domain.util.Logger;

public class Wolf extends Animal {

    public Wolf(String name) {
        super(name);
    }

    @Override
    public void speak(String words) {
        super.speak("выть: " + words);
    }

    @Override
    public void performAction() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " бегает в стае.");
    }

    @Override
    public void sleep() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " спит под звездами.");
    }

    @Override
    public void rest() {
        Logger.log(Logger.LogType.ACTIVITY, getName() + " отдыхает, лежа в тени деревьев.");
    }
}
