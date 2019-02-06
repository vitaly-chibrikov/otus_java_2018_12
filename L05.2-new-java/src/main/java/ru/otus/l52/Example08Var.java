package ru.otus.l52;

import java.util.List;
import java.util.function.Function;

public class Example08Var {
    @SuppressWarnings("Convert2MethodRef")
    public static void main(String[] args) {
        final var v = "value";
        System.out.println(v.length());

        final var numbers = List.of(1, 2, 3, 4, 5);

        for (var number : numbers) {
            System.out.println(number);
        }

        for (var i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        // java 11
        final Function<String, Integer> function1 = s -> s.length(); // @NotNull ?
        final Function<String, Integer> function2 = (@NotNull String s) -> s.length();
        final Function<String, Integer> function3 = (@NotNull var s) -> s.length();
    }

    @interface NotNull {
    }
}
