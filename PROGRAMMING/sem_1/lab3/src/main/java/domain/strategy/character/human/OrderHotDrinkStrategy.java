package main.java.domain.strategy.character.human;

import main.java.domain.character.human.Human;
import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.util.Logger;

public class OrderHotDrinkStrategy implements HumanStrategy {
    private Kiosk kiosk;
    
    public OrderHotDrinkStrategy(Kiosk kiosk) {
        this.kiosk = kiosk;
    }
    
    public void setKiosk(Kiosk kiosk){
        this.kiosk = kiosk;
    }

    @Override
    public void execute(Human human) {
        Drink drink = kiosk.getAvailableDrink("Газированная вода с сиропом");
        if (drink != null) {
            human.orderDrink(kiosk, drink);
        } else {
            Logger.log(Logger.LogType.ERROR, "В киоске нет лимонада для " + human.getName());
        }
    }
}