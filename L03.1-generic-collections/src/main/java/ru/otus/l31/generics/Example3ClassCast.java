package ru.otus.l31.generics;

import java.util.ArrayList;
import java.util.List;

public class Example3ClassCast {
    public static void main(String[] args) {
        final List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

//        final List<String> stringList = (List<String>) intList; // doesn't work
        final List<?> list = intList;
        final List<String> stringList = (List<String>) list;

        stringList.add("4");
        stringList.add("5");
        stringList.add("6");

        System.out.println(intList.size());
        System.out.println(stringList.size());

        intList.forEach(System.out::println);
        stringList.forEach(System.out::println);
    }
}
