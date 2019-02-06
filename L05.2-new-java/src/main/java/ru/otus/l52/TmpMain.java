package ru.otus.l52;

public class TmpMain {
    public static void main(String[] args) {
        int a = 3;

        final Runnable r = () -> System.out.println(a);
        r.run();

//        a++;
    }
}
