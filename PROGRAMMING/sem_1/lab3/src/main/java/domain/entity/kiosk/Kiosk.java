package main.java.domain.entity.kiosk;

import main.java.domain.entity.Entity;
import main.java.domain.entity.drink.Drink;
import main.java.domain.util.Logger;
import java.util.ArrayList;
import java.util.List;

public class Kiosk implements Entity {

    private final String name;
    private final List<KioskShelf> shelves;

    public Kiosk(String name) {
        this.name = name;
        this.shelves = new ArrayList<>();
        Logger.log(Logger.LogType.CREATION, "Киоск '" + name + "' создан.");
    }

    public void addShelf(KioskShelf shelf) {
        shelves.add(shelf);
        Logger.log(Logger.LogType.CREATION, "Полка '" + shelf.getName() + "' добавлена в киоск '" + name + "'.");
    }

    public KioskShelf findShelfForDrink(Drink drink) {
        for (KioskShelf shelf : shelves) {
            if (shelf.hasDrink(drink)) {
                return shelf;
            }
        }
        return null;
    }

    public void serveDrink(String shelfName, Drink drink) {
        KioskShelf shelf = findShelfByName(shelfName);
        if (shelf != null && shelf.hasDrink(drink)) {
            openFaucet(shelfName, drink);
            Logger.log(Logger.LogType.ACTIVITY, name + " выдает: " + drink.getName() + " с полки " + shelfName);
            shelf.removeDrink(drink);
        } else {
            Logger.log(Logger.LogType.ERROR, "Напиток " + drink.getName() + " недоступен на полке " + shelfName);
        }
    }

    private KioskShelf findShelfByName(String name) {
        return shelves.stream()
                      .filter(shelf -> shelf.getName().equals(name))
                      .findFirst()
                      .orElse(null);
    }

    private void openFaucet(String shelfName, Drink drink) {
        Logger.log(Logger.LogType.ACTIVITY, "Открытие краника для напитка: " + drink.getName() + " с полки " + shelfName);
    }

    public Drink getAvailableDrink(String drinkName) {
        for (KioskShelf shelf : shelves) {
            for (Drink drink : shelf.getDrinks()) {
                if (drink.getName().equalsIgnoreCase(drinkName)) {
                    return drink;
                }
            }
        }
        return null;
    }

    @Override
    public String getDescription() {
        return "Киоск: " + name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" у него есть полки:\n");
        for (KioskShelf shelf : shelves) {
            sb.append("- ").append(shelf).append("\n");
        }
        return sb.toString();
    }
}
