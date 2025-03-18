package main.models;

import java.util.Objects;

/**
 * Класс, представляющий координаты.
 */
public class Coordinates {
    private Integer x; // Поле не может быть null
    private Long y; // Поле не может быть null

    /**
     * Конструктор для создания координат.
     *
     * @param x координата X
     * @param y координата Y
     */
    public Coordinates(Integer x, Long y) {
        this.x = Objects.requireNonNull(x, "X cannot be null");
        this.y = Objects.requireNonNull(y, "Y cannot be null");
    }

    // Геттеры и сеттеры

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = Objects.requireNonNull(x, "X cannot be null");
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = Objects.requireNonNull(y, "Y cannot be null");
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
