package ru.otus.l52.e01lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Example07Exceptions {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bb");
        list.add("c");
        list.add("dddd");

//        final Predicate<String> predicate = Example07Exceptions::checkString;
//        list.stream()
//                .filter(predicate)
//                .forEach(System.out::println);
//
//
//        final StringLengthChecker checker = s -> s.length() > 2;
//        list.stream()
//                .filter(checker)
//                .forEach(System.out::println);
    }

    private static boolean checkString(String value) throws Exception {
        if (value.length() > 2) {
            return true;
        }
        throw new Exception("bad length");
    }

    interface StringLengthChecker {
        boolean checkLength(String value) throws Exception;
    }
}
