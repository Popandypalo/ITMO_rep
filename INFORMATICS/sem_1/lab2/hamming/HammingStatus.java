package hamming;
public enum HammingStatus {
    NO_ERROR("\u001B[32mОшибок нет\u001B[0m"), // Зеленый текст
    ERROR("\u001B[31mОшибка в кодовом слове\u001B[0m"); // Красный текст

    private final String message;

    HammingStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
