package ru.otus.l31.generics;

import java.util.List;

import static java.util.Collections.singletonList;

public final class Example2GenericMethod {
    private static <T> T getFirst(List<T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        System.out.println(Example2GenericMethod.getFirst(singletonList(10)));
        System.out.println(Example2GenericMethod.getFirst(singletonList("string")));
    }
}
