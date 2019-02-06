package ru.otus.l52;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Example09Misc {
    public static void main(String[] args) throws IOException {
        System.out.println("Statistics");

        final IntSummaryStatistics statistics = new IntSummaryStatistics();
        for (int i = 0; i <= 100; i++) {
            statistics.accept(i);
        }

        System.out.println("Max: " + statistics.getMax());
        System.out.println("Min: " + statistics.getMin());
        System.out.println("Avg: " + statistics.getAverage());
        System.out.println("Cnt: " + statistics.getCount());
        System.out.println("Sum: " + statistics.getSum());

        System.out.println("\n====\n");
        System.out.println("Files.lines");

        //some comment
        Files.lines(Paths.get(".gitignore"))
                .filter(s -> !s.trim().isEmpty() && !s.trim().startsWith("#"))
                .forEach(System.out::println);

        System.out.println("\n====\n");
        System.out.println("Files.readString");

        final String readString = Files.readString(Paths.get(".gitignore"));
        System.out.println(readString);

        System.out.println("\n====\n");
        System.out.println("try(variable)");

        try (final BufferedReader reader = new BufferedReader(new FileReader(new File(".gitignore")))) {
//        final BufferedReader reader = new BufferedReader(new FileReader(new File(".gitignore")));
//        try (reader) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        }

        System.out.println("\n====\n");
        System.out.println("String methods");

        //noinspection ConstantConditions
        System.out.println("".isEmpty());
        System.out.println("".isBlank());

        //noinspection ConstantConditions
        System.out.println(" ".isEmpty());
        System.out.println(" ".isBlank());

        final List<String> lines = "a\nb\nc\nd".lines()
                .collect(Collectors.toList());
        System.out.println(lines);

        final String str = "abcd".repeat(3);
        System.out.println(str);
    }
}
