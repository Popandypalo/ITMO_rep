package main.java.collection;

public class CollectionServiceImpl<T> implements CollectionService<T> {
    private CollectionWrapper<T> collectionWrapper;

    public CollectionServiceImpl() {
        this.collectionWrapper = new CollectionWrapper<>();
    }

    @Override
    public void add(T item) {
        // Добавляем дополнительную логику (например, валидацию)
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        collectionWrapper.add(item);
    }

    @Override
    public void remove(T item) {
        // Логирование перед удалением
        System.out.println("Removing item: " + item);
        collectionWrapper.remove(item);
    }

    @Override
    public boolean contains(T item) {
        return collectionWrapper.contains(item);
    }

    @Override
    public void printAll() {
        // Логирование перед выводом
        System.out.println("Printing all items:");
        collectionWrapper.printAll();
    }
}