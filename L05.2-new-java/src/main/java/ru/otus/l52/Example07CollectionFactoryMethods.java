package ru.otus.l52;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Example07CollectionFactoryMethods {
    public static void main(String[] args) {
        final List<Integer> integerList = List.of(1, 2, 3, 4);
//        integerList.add(5); // unmodifiable collection!
        System.out.println(integerList.size());

        final Map<Integer, String> map = Map.of(0, "a", 1, "b", 3, "c", 4, "d");
//        map.put(5, "e"); // unmodifiable map!
        System.out.println(map.size());

        final ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        final List<String> copiedList = List.copyOf(list);
//        copiedList.add("e"); // unmodifiable !
        list.add("e");

        System.out.println(list.size());
        System.out.println(copiedList.size());
    }
}
