package ru.otus.l52.e06streams;

import java.util.stream.Stream;

public class Example01TerminalOperation {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(new Data(1), new Data(2), new Data(3))
                .map(Data::getValue);

        System.out.println("doing other stuff 1");

        integerStream = integerStream
                .map(i -> i * i);

        System.out.println("doing other stuff 2");

        integerStream.forEach(System.out::println);
    }

    static class Data {
        private final int value;

        Data(int value) {
            this.value = value;
        }

        public int getValue() {
            System.out.println("returning value " + value);
            return value;
        }
    }
}
