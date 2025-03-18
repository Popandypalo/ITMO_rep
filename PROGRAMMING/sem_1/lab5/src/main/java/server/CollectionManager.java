package main.java.server;

import main.models.Product;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс для управления коллекцией продуктов.
 */
public class CollectionManager {
    private HashSet<Product> products;
    private LocalDateTime initializationTime;
    private List<String> commandHistory;

    public CollectionManager() {
        this.products = new HashSet<>();
        this.initializationTime = LocalDateTime.now();
        this.commandHistory = new LinkedList<>();
    }

    /**
     * Добавляет продукт в коллекцию.
     *
     * @param product продукт
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Удаляет продукт по id.
     *
     * @param id идентификатор продукта
     * @return true, если удалено
     */
    public boolean removeById(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    /**
     * Очищает коллекцию.
     */
    public void clearCollection() {
        products.clear();
    }

    /**
     * Получает коллекцию.
     */
    public HashSet<Product> getProducts() {
        return products;
    }

    /**
     * Возвращает информацию о коллекции.
     */
    public String getInfo() {
        return "Тип коллекции: " + products.getClass().getName() +
                "\nДата инициализации: " + initializationTime +
                "\nКоличество элементов: " + products.size();
    }

    /**
     * Добавляет команду в историю.
     */
    public void addToHistory(String command) {
        if (commandHistory.size() >= 6) {
            commandHistory.remove(0);
        }
        commandHistory.add(command);
    }

    /**
     * Добавляет продукт с указанным ID (используется для update).
     */
    public void addProductWithId(Product product, Long id) {
        try {
            java.lang.reflect.Field idField = Product.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(product, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка установки ID продукта вручную.", e);
        }
        products.add(product);
    }

    /**
     * Возвращает историю команд.
     */
    public List<String> getCommandHistory() {
        return new ArrayList<>(commandHistory);
    }

    /**
     * Выводит среднее значение поля price.
     */
    public double getAveragePrice() {
        return products.stream()
                .filter(p -> p.getPrice() != null)
                .mapToInt(Product::getPrice)
                .average()
                .orElse(0);
    }

    /**
     * Считает количество продуктов, у которых цена больше заданной.
     */
    public long countGreaterThanPrice(int price) {
        return products.stream()
                .filter(p -> p.getPrice() != null && p.getPrice() > price)
                .count();
    }

    /**
     * Удаляет все продукты с ценой равной заданной.
     */
    public void removeAllByPrice(int price) {
        products.removeIf(p -> p.getPrice() != null && p.getPrice() == price);
    }

    /**
     * Находит максимальный элемент в коллекции.
     */
    public Optional<Product> getMaxProduct() {
        return products.stream().max(Comparator.naturalOrder());
    }
}
