package main.models;
/**
 * Класс, представляющий адрес.
 */
public class Address {
    private String street; // Длина строки не должна быть больше 142, Поле может быть null

    /**
     * Конструктор для создания адреса.
     *
     * @param street улица
     */
    public Address(String street) {
        if (street != null && street.length() > 142) {
            throw new IllegalArgumentException("Street length must be 142 characters or less");
        }
        this.street = street;
    }

    // Геттеры и сеттеры

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street != null && street.length() > 142) {
            throw new IllegalArgumentException("Street length must be 142 characters or less");
        }
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }
}
