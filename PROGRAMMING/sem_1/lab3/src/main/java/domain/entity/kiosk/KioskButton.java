package main.java.domain.entity.kiosk;

import main.java.domain.util.Logger;

public class KioskButton {

    private final String label;

    public KioskButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void press() {
        Logger.log(Logger.LogType.ACTIVITY, "Кнопка '" + label + "' нажата.");
    }
}