package main.java.client;


import main.models.OrganizationType;
import main.models.UnitOfMeasure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Обработчик пользовательского ввода для создания Product.
 */
public class CommandInputHandler {
    private final Scanner scanner;

    public CommandInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private int promptInt(String message) {
        while (true) {
            String input = prompt(message);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число.");
            }
        }
    }

    private long promptLong(String message) {
        while (true) {
            String input = prompt(message);
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число.");
            }
        }
    }

    private String promptEnum(String message, String[] options) {
        while (true) {
            System.out.println(message + " " + String.join(", ", options));
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return null;
            for (String option : options) {
                if (option.equalsIgnoreCase(input)) return option;
            }
            System.out.println("Некорректное значение. Повторите ввод.");
        }
    }

    /**
     * Читает все поля продукта и возвращает как аргументы команды.
     */
    public Map<String, String> readProductFields() {
        Map<String, String> args = new HashMap<>();
        args.put("name", prompt("Введите имя продукта: "));
        args.put("x", String.valueOf(promptInt("Введите координату X (целое число): ")));
        args.put("y", String.valueOf(promptLong("Введите координату Y (число): ")));

        String priceInput = prompt("Введите цену продукта (целое число > 0, можно оставить пустым): ");
        if (!priceInput.isEmpty()) args.put("price", priceInput);

        args.put("partNumber", prompt("Введите partNumber (до 78 символов): "));

        String unit = promptEnum("Введите единицу измерения (или пусто):", getEnumOptions(UnitOfMeasure.class));
        if (unit != null) args.put("unitOfMeasure", unit);

        args.put("manufacturerName", prompt("Введите имя производителя: "));
        args.put("employeesCount", String.valueOf(promptInt("Введите количество сотрудников (>0): ")));
        String orgType = promptEnum("Введите тип организации (или пусто):", getEnumOptions(OrganizationType.class));
        if (orgType != null) args.put("organizationType", orgType);

        args.put("address", prompt("Введите адрес (строка): "));

        return args;
    }

    private <T extends Enum<T>> String[] getEnumOptions(Class<T> enumClass) {
        T[] constants = enumClass.getEnumConstants();
        String[] options = new String[constants.length];
        for (int i = 0; i < constants.length; i++) {
            options[i] = constants[i].name();
        }
        return options;
    }
}

