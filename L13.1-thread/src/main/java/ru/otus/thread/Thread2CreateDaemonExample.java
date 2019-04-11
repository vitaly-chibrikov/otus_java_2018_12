package ru.otus.thread;

import java.util.stream.IntStream;

public class Thread2CreateDaemonExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 =
                new Thread(() -> IntStream.range(1, Integer.MAX_VALUE)
                        .forEach(System.out::println));
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(1000);
        System.out.println("finish");
    }
}
