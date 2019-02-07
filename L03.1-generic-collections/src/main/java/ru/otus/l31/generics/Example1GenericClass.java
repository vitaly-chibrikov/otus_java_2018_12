package ru.otus.l31.generics;

public final class Example1GenericClass<T> {
    private final T value;

    private Example1GenericClass(T value) {
        this.value = value;
    }

    private T getValue() {
        return value;
    }

    public static void main(String[] args) {
        final Example1GenericClass<Integer> integerHolder = new Example1GenericClass<>(10);
        System.out.println(integerHolder.getValue());

        final Example1GenericClass<String> stringHolder = new Example1GenericClass<>("string");
        System.out.println(stringHolder.getValue());
    }
}
