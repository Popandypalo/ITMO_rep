package main.java.domain.strategy;


public interface ActionStrategy<T> {
    void execute(T actor);
}