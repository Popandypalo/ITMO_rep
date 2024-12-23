package main.java.application;

import main.java.domain.character.animal.Animal;
import main.java.domain.character.animal.AnimalFactory;
import main.java.domain.character.human.Human;
import main.java.domain.character.human.HumanFactory;
import main.java.domain.entity.drink.Drink;
import main.java.domain.entity.drink.DrinkService;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.entity.kiosk.KioskFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Загрузка напитков
        DrinkService drinkService = new DrinkService();
        List<Drink> drinks = drinkService.loadDrinks("src/main/resources/drinks.json");

        // Создание киосков
        KioskFactory kioskFactory = new KioskFactory();
        Kiosk kiosk1 = kioskFactory.createKiosk("Киоск 1", List.of("Полка 1: Лимонад, Кола"), drinks);
        Kiosk kiosk2 = kioskFactory.createKiosk("Киоск 2", List.of("Полка 2: Газированная вода с сиропом, Вода"), drinks);

        // Создание фабрики для людей
        HumanFactory humanFactory = new HumanFactory();

        // Создание человека
        Human human = humanFactory.createHuman("Иван", true, true); // Человек голоден и жаждет

        // Человек подходит и смотрит на киоски
        human.approach(kiosk1);
        human.observe(kiosk1.toString());

        // Человек заказывает напиток в Киоске 1 (Лимонад)
        human.orderDrink(kiosk1, drinks.get(0)); // Лимонад из первого киоска

        // Человек подходит ко второму киоску
        human.approach(kiosk2);
        human.observe(kiosk2.toString());

        // Человек заказывает напиток во втором киоске (Вода)
        human.orderDrink(kiosk2, drinks.get(1)); // Вода из второго киоска

        AnimalFactory animalFactory = new AnimalFactory();

        // Создание тигра и волка
        Animal tiger = animalFactory.createTiger("Тигр");
        Animal wolf = animalFactory.createWolf("Волк");

        // Взаимодействие с животными
        tiger.speak("громко рычит!");
        wolf.speak("выет в ночи");

        tiger.performAction();
        wolf.performAction();

        // Спят и отдыхают
        tiger.sleep();
        wolf.sleep();

        tiger.rest();
        wolf.rest();
    }
}