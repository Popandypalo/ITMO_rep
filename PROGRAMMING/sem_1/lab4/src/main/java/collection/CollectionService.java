package main.java.collection;

public interface CollectionService<T> {
    void add(T item);
    void remove(T item);
    boolean contains(T item);
    
    void printAll();
    
}