package ru.otus.l52;

import java.util.StringJoiner;

public class Example03SmallFeatures {
    public static void main(String[] args) {
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
    }
}
