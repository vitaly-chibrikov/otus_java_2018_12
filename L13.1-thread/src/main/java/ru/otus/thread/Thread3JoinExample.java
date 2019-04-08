package ru.otus.thread;

import java.util.stream.IntStream;

public class Thread3JoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> IntStream.range(1, 100000).forEach(System.out::println));
        t1.start();
        long start = System.currentTimeMillis();
        t1.join();
        System.out.println(System.currentTimeMillis() - start);
    }
}
