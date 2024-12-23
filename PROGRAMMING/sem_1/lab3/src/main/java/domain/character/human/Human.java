package main.java.domain.character.human;

import main.java.domain.character.Character;
import main.java.domain.entity.Entity;
import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.entity.kiosk.KioskButton;
import main.java.domain.entity.kiosk.KioskShelf;
import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;

public class Human implements Character {

    private String name;
    private boolean thirsty;
    private boolean hot;
    private ActionStrategy<Human> currentStrategy;

    public Human(String name, boolean thirsty, boolean hot) {
        this.name = name;
        this.thirsty = thirsty;
        this.hot = hot;
    }

    @Override
    public void speak(String words) {
        Logger.log(Logger.LogType.ACTIVITY, name + " говорит: " + words);
    }

    @Override
    public void observe(String observation) {
        Logger.log(Logger.LogType.ACTIVITY, name + " смотрит на: " + observation);
    }

    public void setStrategy(ActionStrategy<Human> strategy) {
        this.currentStrategy = strategy;
    }

    public ActionStrategy<Human> getStrategy(){
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

    public boolean isThirsty() {
        return thirsty;
    }

    public void setThirsty(boolean thirsty) {
        this.thirsty = thirsty;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public void orderDrink(Kiosk kiosk, Drink drink) {
        if (thirsty) {
            Logger.log(Logger.LogType.INTERACTION, name + " заказывает " + drink.getName());
            KioskShelf shelf = kiosk.findShelfForDrink(drink);
            if (shelf != null) {
                KioskButton button = shelf.getButtons().stream()
                                          .filter(b -> b.getLabel().equals(drink.getName()))
                                          .findFirst()
                                          .orElse(null);
                if (button != null) {
                    button.press();
                    Logger.log(Logger.LogType.INTERACTION, name + " нажал кнопку для напитка: " + drink.getName());
                    kiosk.serveDrink(shelf.getName(), drink);
                } else {
                    Logger.log(Logger.LogType.ERROR, "Кнопка для напитка " + drink.getName() + " не найдена.");
                }
            } else {
                Logger.log(Logger.LogType.ERROR, "Напиток " + drink.getName() + " не найден в киоске.");
            }
        } else {
            Logger.log(Logger.LogType.INTERACTION, name + " не хочет пить.");
        }
    }
    
    public void complainAboutHeat() {
        if (hot) {
            speak("мне жарко!");
        } else {
            speak("мне совсем не жарко.");
        }
    }
}
