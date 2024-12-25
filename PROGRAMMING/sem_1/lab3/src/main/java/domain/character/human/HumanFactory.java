package main.java.domain.character.human;

import main.java.domain.util.Logger;

public class HumanFactory {

    public Human createHuman(String name, boolean thirsty, boolean hot, int money) {
        Logger.log(Logger.LogType.CREATION, "Создание человека: '" + name + "'. C кошельком: " + money);

        Human human = new Human(name, thirsty, hot, money);

        Logger.log("");
        return human;
    }
}
