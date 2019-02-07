package ru.otus.l52.e01lambdas;

public class Example06Capturing {
    public static void main(String[] args) {
//        final int a = 3;
        int a = 3;

        final Runnable r = () -> System.out.println(a);
        r.run();

//        a++;
    }
}
