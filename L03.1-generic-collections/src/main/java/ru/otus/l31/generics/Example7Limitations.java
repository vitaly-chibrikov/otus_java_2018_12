package ru.otus.l31.generics;

import java.util.AbstractList;
import java.util.List;

public class Example7Limitations {
//    private static class MyException<T> extends Throwable {
//    }

//    private enum MyEnum<T> {
//    }

    public static void main(String[] args) {
        final List<String> list = new AbstractList<String>() {
            @Override
            public String get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };

//        final Runnable<T> runnable = new Runnable() {
//            @Override
//            public void run() {
//            }
//        };

        final MyRunnable<String> myRunnable = new MyRunnable<String>();
//        final MyRunnable<T> myRunnable = new MyRunnable<T>();
    }

    private static class MyRunnable<T> implements Runnable {
        @Override
        public void run() {
        }
    }
}
