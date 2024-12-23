package main.java.domain.entity.drink;

import java.util.List;

public class DrinkFactory {

    private DrinkService drinkService;

    public DrinkFactory(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    public List<Drink> createDrinksFromFile(String filePath) {
        return drinkService.loadDrinks(filePath);
    }
}
