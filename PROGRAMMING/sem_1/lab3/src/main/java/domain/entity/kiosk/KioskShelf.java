package main.java.domain.entity.kiosk;

import main.java.domain.entity.drink.Drink;
import main.java.domain.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class KioskShelf {

    private final String name;
    private final List<Drink> drinks;
    private final List<KioskButton> buttons;

    public KioskShelf(String name) {
        this.name = name;
        this.drinks = new ArrayList<>();
        this.buttons = new ArrayList<>();
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
        buttons.add(new KioskButton(drink.getName()));
        Logger.log(Logger.LogType.CREATION, "Добавлен напиток '" + drink.getName() + "' на полку '" + name + "'.");
    }

    public boolean hasDrink(Drink drink) {
        return drinks.contains(drink);
    }

    public void removeDrink(Drink drink) {
        drinks.remove(drink);
        buttons.removeIf(button -> button.getLabel().equals(drink.getName()));
    }

    public List<KioskButton> getButtons() {
        return new ArrayList<>(buttons);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " содержит напитки: " + drinks;
    }
}
