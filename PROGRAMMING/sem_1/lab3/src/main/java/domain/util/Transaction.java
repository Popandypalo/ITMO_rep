package main.java.domain.util;

import java.time.LocalDateTime;
import main.java.domain.entity.drink.Drink;
import main.java.domain.character.human.Human;

public record Transaction(LocalDateTime dateTime, Human buyer, Drink drink) {
    @Override
    public String toString() {
        return "Транзакция: " + dateTime + " | Покупатель: " + buyer.getName() + " | Напиток: " + drink.getName();
    }
}
