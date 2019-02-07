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
        System.out.println(list);

        //noinspection Convert2MethodRef
        final List<Double> mappedList = map(list, e -> Math.sqrt(e));
        System.out.println("Mapped list: ");
        System.out.println(mappedList);

        final List<Double> filteredList = filter(list, e -> e % 2 == 0);
        System.out.println("Filtered list: ");
        System.out.println(filteredList);

//        final double reduceResult = reduce(list, ...)
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
}
