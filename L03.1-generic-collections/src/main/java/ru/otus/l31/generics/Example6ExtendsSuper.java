package ru.otus.l31.generics;

import java.util.ArrayList;
import java.util.List;

public class Example6ExtendsSuper {
    public static void main(String[] args) {
        final List<Number> numberList = new ArrayList<>();
        numberList.add(0L);

//        tryPutExtends(numberList);
//        tryPutSuper(numberList);
//        tryPutWildcard(numberList);

//        tryGetExtends(numberList);
//        tryGetSuper(numberList);
//        tryGetWildcard(numberList);

        final List<Integer> integerList = new ArrayList<>();
        integerList.add(1);

//        tryPutExtends(integerList);
//        tryPutSuper(integerList);
//        tryPutWildcard(integerList);

//        tryGetExtends(integerList);
//        tryGetSuper(integerList);
//        tryGetWildcard(integerList);

        final List<Double> doubleList = new ArrayList<>();
        doubleList.add(2.0d);

//        tryPutExtends(doubleList);
//        tryPutSuper(doubleList);
//        tryPutWildcard(doubleList);

//        tryGetExtends(doubleList);
//        tryGetSuper(doubleList);
//        tryGetWildcard(doubleList);
    }

    private static void tryPutExtends(List<? extends Number> list) {
//        list.add(new Integer(1));
//        list.add(new Double(2.0d));
//        list.add(new Long(3L));
    }

    private static void tryPutSuper(List<? super Number> list) {
//        list.add(new Integer(1));
//        list.add(new Double(2.0d));
//        list.add(new Long(3L));
//        list.add(new Object());
    }

    private static void tryPutWildcard(List<?> list) {
//        list.add(new Object());
//        list.add("some string");
//        list.add(new Double(2.0d));
//        list.add(new Long(3L));
    }

    private static void tryGetExtends(List<? extends Number> list) {
//        final Number number = list.get(0);
//        final Integer integer = list.get(0);
//        final Object object = list.get(0);
    }

    private static void tryGetSuper(List<? super Number> list) {
//        final Number number = list.get(0);
//        final Integer integer = list.get(0);
//        final Object object = list.get(0);
    }

    private static void tryGetWildcard(List<?> list) {
//        final Number number = list.get(0);
//        final Integer integer = list.get(0);
//        final Object object = list.get(0);
    }
}
