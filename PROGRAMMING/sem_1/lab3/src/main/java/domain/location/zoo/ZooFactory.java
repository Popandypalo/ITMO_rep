package main.java.domain.location.zoo;

import main.java.domain.util.Logger;

public class ZooFactory {

    public static Zoo createZoo(String name) {
        Logger.log(Logger.LogType.CREATION, "Создание зоопарка с именем: " + name);
        Logger.log("");
        return new Zoo(name);
    }
}
