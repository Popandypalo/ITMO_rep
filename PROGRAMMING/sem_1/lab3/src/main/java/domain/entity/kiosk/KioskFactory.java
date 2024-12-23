package main.java.domain.entity.kiosk;

import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.drink.DrinkService;
import main.java.domain.util.Logger;
import java.util.List;

public class KioskFactory {

    public static Kiosk createKiosk(String kioskName, List<String> shelfData, String drinksFilePath) {
        Logger.log(Logger.LogType.CREATION, "Создание киоска: '" + kioskName + "'.");

        DrinkService drinkService = new DrinkService();
        List<Drink> drinks = drinkService.loadDrinks(drinksFilePath);

        Kiosk kiosk = new Kiosk(kioskName);

        for (String shelfInfo : shelfData) {
            String[] parts = shelfInfo.split(":");
            String shelfName = parts[0];
            String[] drinkNames = parts[1].split(",");

            Logger.log(Logger.LogType.CREATION, "Создание полки: '" + shelfName + "' для киоска '" + kioskName + "'.");

            KioskShelf shelf = new KioskShelf(shelfName);
            for (String drinkName : drinkNames) {
                Drink drink = findDrinkByName(drinks, drinkName.trim());
                                if (drink != null) {
                                    shelf.addDrink(drink);
                                }
                            }
                
                            kiosk.addShelf(shelf);
                        }
                        Logger.log("");
                        return kiosk;
    }
                
    private static Drink findDrinkByName(List<Drink> drinks, String drinkName) {
        return drinks.stream()
                     .filter(drink -> drink.getName().equals(drinkName))
                     .findFirst()
                     .orElse(null);
    }
}
