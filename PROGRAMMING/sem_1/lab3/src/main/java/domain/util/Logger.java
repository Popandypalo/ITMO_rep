package main.java.domain.util;

public class Logger {

    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String GRAY = "\u001B[37m";

    public enum LogType {
        CREATION("Создание", BLUE),
        ACTIVITY("Активность", GREEN),
        ERROR("Ошибка", RED),
        INTERACTION("Взаимодействие", CYAN),
        GENERAL("Лог", GRAY);

        private final String label;
        private final String color;

        LogType(String label, String color) {
            this.label = label;
            this.color = color;
        }

        public String getLabel() {
            return label;
        }

        public String getColor() {
            return color;
        }
    }

    private static final int LABEL_WIDTH = 16;

    public static void log(LogType type, String message) {
        String formattedMessage = formatMessage(type, message);
        System.out.println(formattedMessage);
    }

    public static void log(String message) {
        log(LogType.GENERAL, message);
    }

    private static String formatMessage(LogType type, String message) {
        String label = String.format("%-" + LABEL_WIDTH + "s", "[" + type.getLabel() + "]");
        return type.getColor() + label + " |   " + message + RESET;
    }
}