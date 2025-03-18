package main.java.server.processor;

import main.models.*;

import java.util.Map;

/**
 * Вспомогательный класс для сборки Product из аргументов.
 */
public class ProductBuilder {
    public static Product buildProduct(Map<String, String> args) {
        // Name
        String name = args.get("name");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Поле name обязательно.");

        // Coordinates
        Integer x = Integer.parseInt(args.get("x"));
        Long y = Long.parseLong(args.get("y"));
        Coordinates coordinates = new Coordinates(x, y);

        // Price
        Integer price = null;
        if (args.get("price") != null && !args.get("price").isEmpty()) {
            price = Integer.parseInt(args.get("price"));
        }

        // PartNumber
        String partNumber = args.get("partNumber");
        if (partNumber == null || partNumber.length() > 78) throw new IllegalArgumentException("Некорректный partNumber.");

        // UnitOfMeasure
        UnitOfMeasure unit = null;
        if (args.get("unit") != null && !args.get("unit").isEmpty()) {
            unit = UnitOfMeasure.valueOf(args.get("unit"));
        }

        // Manufacturer
        String orgName = args.get("orgName");
        Integer empCount = Integer.parseInt(args.get("employeesCount"));
        OrganizationType type = null;
        if (args.get("orgType") != null && !args.get("orgType").isEmpty()) {
            type = OrganizationType.valueOf(args.get("orgType"));
        }
        String street = args.get("street");
        Address address = new Address(street);
        Organization org = new Organization(orgName, empCount, type, address);

        return new Product(name, coordinates, price, partNumber, unit, org);
    }
}