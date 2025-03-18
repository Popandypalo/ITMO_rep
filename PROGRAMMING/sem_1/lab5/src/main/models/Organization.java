package main.models;

import java.util.Objects;

/**
 * Класс, представляющий организацию.
 */
public class Organization {
    private static int idCounter = 0;

    private int id; // Значение должно быть больше 0, уникальное, генерируется автоматически
    private String name; // Поле не может быть null, строка не может быть пустой
    private Integer employeesCount; // Поле не может быть null, значение должно быть больше 0
    private OrganizationType type; // Поле может быть null
    private Address postalAddress; // Поле не может быть null

    /**
     * Конструктор для создания организации.
     *
     * @param name            имя организации
     * @param employeesCount  количество сотрудников
     * @param type            тип организации
     * @param postalAddress   почтовый адрес
     */
    public Organization(String name, Integer employeesCount, OrganizationType type, Address postalAddress) {
        this.id = generateId();
        setName(name);
        setEmployeesCount(employeesCount);
        this.type = type;
        this.postalAddress = Objects.requireNonNull(postalAddress, "Postal address cannot be null");
    }

    /**
     * Генерирует уникальный идентификатор для организации.
     *
     * @return уникальный идентификатор
     */
    private synchronized int generateId() {
        return ++idCounter;
    }

    // Геттеры и сеттеры

    public int getId() {
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

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Integer employeesCount) {
        if (employeesCount == null || employeesCount <= 0) {
            throw new IllegalArgumentException("Employees count must be greater than 0");
        }
        this.employeesCount = employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = Objects.requireNonNull(postalAddress, "Postal address cannot be null");
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeesCount=" + employeesCount +
                ", type=" + type +
                ", postalAddress=" + postalAddress +
                '}';
    }
}
