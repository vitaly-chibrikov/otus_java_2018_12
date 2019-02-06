package ru.otus.l52.e00lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Example05Comparator {
    public static void main(String[] args) {
        final List<String> list = getList();

        // old way
        list.sort(new StringLengthComparator());

//        //noinspection Convert2Lambda
//        list.sort(new Comparator<>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return Integer.compare(o1.length(), o2.length());
//            }
//        });
//
//        //new way
//        //noinspection ComparatorCombinators
//        list.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
//
//        //noinspection Convert2MethodRef
//        list.sort(Comparator.comparing(s -> s.length()));
//
//        list.sort(Comparator.comparing(String::length));

        list.forEach(System.out::println);
    }

    private static final class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(o1.length(), o2.length());
        }
    }

    private static List<String> getList() {
        final List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bbb");
        list.add("c");
        list.add("dddd");
        return list;
    }
}
