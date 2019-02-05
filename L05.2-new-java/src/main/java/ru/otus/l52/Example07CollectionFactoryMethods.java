package ru.otus.l52;

import java.util.List;

public class Example07CollectionFactoryMethods {
    public static void main(String[] args) {
        final List<Integer> integerList = List.of(1, 2, 3, 4);
        integerList.add(5);
        System.out.println(integerList.size());
    }
}
