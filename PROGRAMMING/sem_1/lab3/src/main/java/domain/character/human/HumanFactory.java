package main.java.domain.character.human;

import main.java.domain.util.Logger;

public class HumanFactory {

    public Human createHuman(String name, boolean thirsty, boolean hot) {
        Logger.log(Logger.LogType.CREATION, "Создание человека: '" + name + "'.");

        Human human = new Human(name, thirsty, hot);

        Logger.log("");
        return human;
    }
}
