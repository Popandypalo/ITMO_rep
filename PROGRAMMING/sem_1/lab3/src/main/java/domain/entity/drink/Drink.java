package main.java.domain.entity.drink;

import main.java.domain.entity.Entity;

public class Drink implements Entity {
    private String name;
    private String type;
    private int price;

    public Drink(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return "Напиток: " + name + " (" + type + "), цена: " + price + " руб";
    }

    @Override
    public String toString() {
        return name + " (" + type + "), цена: " + price + " руб";
    }
}
