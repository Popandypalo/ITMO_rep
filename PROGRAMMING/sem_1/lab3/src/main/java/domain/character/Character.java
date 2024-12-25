package main.java.domain.character;

import main.java.domain.interface_.VisibleEntity;

public interface Character extends VisibleEntity {
    void speak(String words);

    void observe(String observation);

    void performAction();

    void approach(Object object);

    String getName();

    void setName(String name);
}

