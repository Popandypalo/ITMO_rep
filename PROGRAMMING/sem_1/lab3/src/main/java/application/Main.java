package main.java.application;


import main.java.scene.Scene;
import main.java.scene.SceneFactory;


public class Main {
    public static void main(String[] args) {
        try {
            Scene newScene = SceneFactory.createNewScene(
                    "Центральный зоопарк",
                    "Пестренький",
                    "src/main/resources/drinks.json"
            );

            newScene.play();

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}