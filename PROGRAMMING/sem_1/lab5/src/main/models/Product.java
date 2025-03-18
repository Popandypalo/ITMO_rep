package main.models;

import java.util.Date;
import java.util.Objects;

/**
 * Класс, представляющий продукт.
 */
public class Product implements Comparable<Product> {
    private static long idCounter = 0;

    private Long id; // Поле не может быть null, значение должно быть больше 0, уникальное, генерируется автоматически
    private String name; // Поле не может быть null, строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private Date creationDate; // Поле не может быть null, генерируется автоматически
    private Integer price; // Поле может быть null, значение должно быть больше 0
    private String partNumber; // Длина строки не должна быть больше 78, поле не может быть null
    private UnitOfMeasure unitOfMeasure; // Поле может быть null
    private Organization manufacturer; // Поле не может быть null

    /**
     * Конструктор для создания продукта.
     *
     * @param name          имя продукта
     * @param coordinates   координаты продукта
     * @param price         цена продукта
     * @param partNumber    номер части продукта
     * @param unitOfMeasure единица измерения продукта
     * @param manufacturer  производитель продукта
     */
    public Product(String name, Coordinates coordinates, Integer price, String partNumber,
                   UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = generateId();
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.coordinates = Objects.requireNonNull(coordinates, "Coordinates cannot be null");
        if (price != null && price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
        if (partNumber == null || partNumber.length() > 78) {
            throw new IllegalArgumentException("Part number cannot be null and must be 78 characters or less");
        }
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = Objects.requireNonNull(manufacturer, "Manufacturer cannot be null");
        this.creationDate = new Date();
    }

    /**
     * Генерирует уникальный идентификатор для продукта.
     *
     * @return уникальный идентификатор
     */
    private synchronized long generateId() {
        return ++idCounter;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = Objects.requireNonNull(coordinates, "Coordinates cannot be null");
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        if (price != null && price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        if (partNumber == null || partNumber.length() > 78) {
            throw new IllegalArgumentException("Part number cannot be null and must be 78 characters or less");
        }
        this.partNumber = partNumber;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = Objects.requireNonNull(manufacturer, "Manufacturer cannot be null");
    }

    @Override
    public int compareTo(Product other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", unitOfMeasure=" + unitOfMeasure +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
