package ru.otus.l52;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Example04Containers {
    public static void main(String[] args) {
        final ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bbb");
        list.add("c");
        list.add("dddd");

        list.removeIf(s -> s.length() > 2);

        //noinspection Convert2MethodRef
        list.forEach(s -> System.out.println(s));

        final Map<Integer, String> map = new LinkedHashMap<>();
        map.put(0, "a");
        map.put(1, "b");
        map.put(2, "c");
        map.put(3, "d");

        final Function<Integer, String> mapper = i -> Character.toString('a' + i);

        final String mappedValue = map.computeIfAbsent(4, mapper);
        System.out.println("mapped value: " + mappedValue);

        map.forEach((i, s) -> System.out.println(i + " -> " + s));
    }
}
