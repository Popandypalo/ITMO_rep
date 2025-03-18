package main.java.collection;

import java.util.TreeSet;

public class CollectionWrapper<T> {
    private TreeSet<T> collection;

    public CollectionWrapper(){
        this.collection = new TreeSet<>();
    }

    public void add(T item) {
        collection.add(item);
    }

    public void remove(T item){
        collection.remove(item);
    }

    public boolean contains(T item){
        return collection.contains(item);
    }

    public void printAll(){
        for (T item : collection){
            System.out.println(item);
        }
    }
}
