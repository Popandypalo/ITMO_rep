package main.java.domain.character.animal;

import main.java.domain.character.Character;
import main.java.domain.entity.Entity;
import main.java.domain.util.Logger;

public abstract class Animal implements Character {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public void speak(String words) {
        Logger.log(Logger.LogType.ACTIVITY, name + " издает звук: " + words);
    }

    @Override
    public void observe(String observation) {
        Logger.log(Logger.LogType.ACTIVITY, name + " смотрит на: " + observation);
    }

    @Override
    public void performAction() {
        Logger.log(Logger.LogType.ACTIVITY, name + " выполняет действие.");
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