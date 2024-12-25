package main.java.domain.character.animal;

import main.java.domain.character.Character;
import main.java.domain.entity.Entity;
import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;

public abstract class Animal implements Character {

    private String name;
    private boolean visible;
    private ActionStrategy<Animal> currentStrategy;

    public Animal(String name, ActionStrategy<Animal> strategy) {
        this.name = name;
        this.currentStrategy = strategy;
        this.visible = true;
    }

    @Override
    public void speak(String words) {
        Logger.log(Logger.LogType.ACTIVITY, name + " издает звук: " + words);
    }

    @Override
    public void observe(String observation) {
        String visibility = isVisible() ? "видно" : "не видно";
        Logger.log(Logger.LogType.ACTIVITY, name + " смотрит на: " + observation + ". Это " + visibility);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setStrategy(ActionStrategy<Animal> strategy) {
        this.currentStrategy = strategy;
    }

    public ActionStrategy<Animal> getStrategy(){
        return currentStrategy;
    }

    @Override
    public void performAction() {
        if (currentStrategy != null) {
            currentStrategy.execute(this);
        }
    }

    @Override
    public void approach(Object object) {
        String description;
    
        if (object instanceof Character) {
            description = ((Character) object).getName();
        } else if (object instanceof Entity) {
            description = ((Entity) object).getDescription();
        } else {
            description = "неизвестный объект";
        }
    
        Logger.log(Logger.LogType.ACTIVITY, name + " подходит к " + description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public abstract void sleep(); 

    public abstract void rest();
}
