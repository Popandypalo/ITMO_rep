package main.java.domain.entity.drink;

import main.java.domain.entity.Entity;
import main.java.domain.exception.OutOfStockException;

public class Drink implements Entity {
    private String name;
    private String type;
    private int price;
    private int stock;

    public Drink(String name, String type, int price, int stock) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void reduceStock() {
        if (stock <= 0) {
            throw new OutOfStockException("Напиток '" + name + "' закончился.");
        }
        stock--;
    }

    @Override
    public String getDescription() {
        return "Напиток: " + name + " (" + type + "), цена: " + price + " руб, в наличии: " + stock;
    }

    @Override
    public String toString() {
        return name + " (" + type + "), цена: " + price + " руб, в наличии: " + stock;
    }
}