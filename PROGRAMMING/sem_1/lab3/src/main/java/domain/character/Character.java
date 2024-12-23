package main.java.domain.character;

public interface Character {
    void speak(String words);

    void observe(String observation);

    void performAction();

    void approach(Object object);

    String getName();

    void setName(String name);
}
