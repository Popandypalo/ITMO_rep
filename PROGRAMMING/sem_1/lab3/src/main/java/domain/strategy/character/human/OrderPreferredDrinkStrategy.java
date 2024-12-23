package main.java.domain.strategy.character.human;

import main.java.domain.character.human.Human;
import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.kiosk.Kiosk;

import java.util.List;

public class OrderPreferredDrinkStrategy implements HumanStrategy {
    private Kiosk kiosk;
    private final List<String> preferredDrinks;

    public OrderPreferredDrinkStrategy(Kiosk kiosk, List<String> preferredDrinks) {
        this.kiosk = kiosk;
        this.preferredDrinks = preferredDrinks;
    }

    public void setKiosk(Kiosk kiosk){
        this.kiosk = kiosk;
    }

    @Override
    public void execute(Human human) {
        for (String drinkName : preferredDrinks) {
            Drink drink = kiosk.getAvailableDrink(drinkName);
            if (drink != null) {
                human.orderDrink(kiosk, drink);
                return;
            }
        }
        System.out.println(human.getName() + " не смог найти напиток из списка предпочтений.");
    }
}
