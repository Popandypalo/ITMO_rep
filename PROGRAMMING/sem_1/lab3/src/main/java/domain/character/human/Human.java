package main.java.domain.character.human;

import java.time.LocalDateTime;

import main.java.domain.character.Character;
import main.java.domain.entity.Entity;
import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.entity.kiosk.KioskButton;
import main.java.domain.entity.kiosk.KioskShelf;
import main.java.domain.exception.OutOfStockException;
import main.java.domain.strategy.ActionStrategy;
import main.java.domain.util.Logger;
import main.java.domain.util.Transaction;
public class Human implements Character {
    private String name;
    private boolean thirsty;
    private boolean hot;
    private ActionStrategy<Human> currentStrategy;
    private int money;
    private boolean visible;

    public Human(String name, boolean thirsty, boolean hot, int money) {
        this.name = name;
        this.thirsty = thirsty;
        this.hot = hot;
        this.money = money;
        this.visible = true;
    }

    @Override
    public void speak(String words) {
        Logger.log(Logger.LogType.ACTIVITY, name + " говорит: " + words);
    }

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
        return this.visible;
    }

    public void setStrategy(ActionStrategy<Human> strategy) {
        this.currentStrategy = strategy;
    }

    public ActionStrategy<Human> getStrategy() {
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
                    try {
                        drink.reduceStock();
                        if (money >= drink.getPrice()) {
                            money -= drink.getPrice();
                            kiosk.serveDrink(shelf.getName(), drink);
                            Logger.log(Logger.LogType.INTERACTION, name + " купил напиток '" + drink.getName() + "'. Осталось денег: " + money);

                            Transaction transaction = new Transaction(LocalDateTime.now(), this, drink);
                            Logger.log(Logger.LogType.GENERAL, transaction.toString());
                        } else {
                            Logger.log(Logger.LogType.ERROR, name + " не хватает денег на напиток '" + drink.getName() + "'.");
                        }
                    } catch (OutOfStockException e) {
                        Logger.log(Logger.LogType.ERROR, e.getMessage());
                    }
                } else {
                    Logger.log(Logger.LogType.ERROR, "Кнопка для напитка " + drink.getName() + " не найдена.");
                }
            } else {
                Logger.log(Logger.LogType.ERROR, "Напиток '" + drink.getName() + "' не найден в киоске.");
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