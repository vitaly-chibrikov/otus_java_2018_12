package ru.otus.l52;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Example05MapFilterReduce {
    public static void main(String[] args) {
        final ArrayList<Double> list = new ArrayList<>();
        list.add(4d);
        list.add(9d);
        list.add(16d);
        System.out.println("Initial list: ");
        print(list);

        final List<Double> mappedList = map(list, e -> Math.sqrt(e));
        System.out.println("Mapped list: ");
        print(mappedList);

        final List<Double> filteredList = filter(mappedList, e -> e % 2 == 0);
        System.out.println("Filtered list: ");
        print(filteredList);
    }

    static <T, R> List<R> map(Collection<T> src, Function<T, R> op) {
        final List<R> result = new ArrayList<>();
        for (T t : src) {
            result.add(op.apply(t));
        }
        return result;
    }

    static <T> List<T> filter(List<T> src, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (T t : src) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static void print(List<?> list) {
        list.forEach(System.out::println);
    }
}
