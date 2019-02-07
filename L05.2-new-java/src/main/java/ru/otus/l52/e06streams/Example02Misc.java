package ru.otus.l52.e06streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://habr.com/en/post/337350/
 * <p>
 * see {@link ru.otus.l31.collections.CollectorExample}
 */
@SuppressWarnings("JavadocReference")
public class Example02Misc {
    public static void main(String[] args) {
        final List<String> stringList = Stream.of("a", "b", "c", "d")
                .collect(Collectors.toList());

        final List<Integer> integerList = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toList());

        final List<Integer> intList = IntStream.of(1, 2, 3, 4)
                .filter(d -> d % 2 == 0)
                .boxed() // won't work without it
                .collect(Collectors.toList());

        final Map<Integer, List<Student>> map = Example00Students.getStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getCourse));

        for (Map.Entry<Integer, List<Student>> entry : map.entrySet()) {
            System.out.println("Course: " + entry.getKey());
            final String studentNames = entry.getValue().stream()
                    .map(Student::getName)
                    .collect(Collectors.joining(", "));
            System.out.println("Students: " + studentNames);
        }
    }
}
