package ru.otus.l31.generics;

import java.util.ArrayList;
import java.util.List;

public class Example5PECS {
    public static void main(String[] args) {
        final List<Number> numbers = new ArrayList<>();
        consume(numbers);
        produce(numbers);
    }

    // list is a producer of elements
    private static void produce(List<? extends Number> list) {
        final Number number = list.get(0);
        System.out.println(number);
//        list.add(1);
    }

    // list is a consumer of elements
    private static void consume(List<? super Number> list) {
        list.add(1);
        list.add(1.);
//        Number n = list.get(1);
    }
}

