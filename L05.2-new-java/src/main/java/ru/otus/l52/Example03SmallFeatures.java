package ru.otus.l52;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class Example03SmallFeatures {
    public static void main(String[] args) throws IOException {
        final String[] strings = {"aa", "bbb", "c", "dddd"};

        final StringBuilder builder = new StringBuilder();
        builder.append(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            final String s = strings[i];
            builder.append(',').append(s);
        }
        System.out.println("StringBuilder: " + builder);

        System.out.println("String.join: " + String.join(",", strings));

        final StringJoiner jsonArrayJoiner = new StringJoiner("\", \"", "[\"", "\"]");
        for (String s : strings) {
            jsonArrayJoiner.add(s);
        }
        System.out.println("StringJoiner: " + jsonArrayJoiner.toString());

        //some comment
        Files.lines(Paths.get("/Users/egorshubin/git/otus_java_2018_12/L05.2-new-java/src/main/java/ru/otus/l52/Example01SmallFeatures.java"))
                //DO_NOT_COMMIT .filter(s -> !s.trim().isEmpty() && !s.trim().startsWith("//") && !s.startsWith("import") && !s.startsWith("package"))
                .forEach(System.out::println);
    }
}
