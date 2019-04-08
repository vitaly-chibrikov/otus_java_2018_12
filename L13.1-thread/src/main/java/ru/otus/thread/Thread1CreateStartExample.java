package ru.otus.thread;

import java.util.stream.IntStream;

public class Thread1CreateStartExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> IntStream.range(1, 1000).forEach(System.out::println));
        t1.start();
        System.out.println("finish");
    }
}
