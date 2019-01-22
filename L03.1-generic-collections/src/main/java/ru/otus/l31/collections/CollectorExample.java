package ru.otus.l31.collections;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorExample {
    public static void main(String[] args) {
        final List<Integer> intList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .collect(new ListCollector<>());
        System.out.println("Int list: ");
        intList.forEach(System.out::println);

        final List<String> stringList = Stream.of("a", "b", "c", "d", "e", "f", "g")
                .collect(new ListCollector<>());
        System.out.println("String list: ");
        stringList.forEach(System.out::println);
    }

    private static final class ListCollector<T> implements Collector<T, List<T>, List<T>> {
        /**
         * create a list to accumulate results into
         */
        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        /**
         * accumulate an item into created list
         */
        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        /**
         * combine 2 accumulated sub-lists
         */
        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        /**
         * create a resulting list.
         * since we accumulate intermediate results into List,
         * we can return it as is
         */
        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        /**
         * IDENTITY_FINISH shows that finisher doesn't do anything,
         * so finishing step can be skipped
         */
        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Collector.Characteristics.IDENTITY_FINISH);
        }
    }
}
