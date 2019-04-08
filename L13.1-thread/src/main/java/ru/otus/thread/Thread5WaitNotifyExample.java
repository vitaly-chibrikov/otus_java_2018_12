package ru.otus.thread;

import static java.lang.System.out;

public class Thread5WaitNotifyExample {

    private static class Hamburger {
    }

    private static Hamburger hamburger;

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {

        Thread consumer = new Thread(() -> {
            while (true) {
                out.println("consumer before MONITOR");
                synchronized (MONITOR) {
                    out.println("consumer in MONITOR");
                    try {
                        if (hamburger != null) {
                            out.println("consumer eats hamburger");
                            hamburger = null;
                            out.println("consumer notifies");
                            MONITOR.notify();
                            out.println("consumer notified");
                        }
                        out.println("consumer is going to wait");
                        MONITOR.wait();
                        out.println("consumer is awaken");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                out.println("consumer after MONITOR");
            }
        });
        consumer.start();

        Thread producer = new Thread(() -> {
            while (true) {
                out.println("producer before MONITOR");
                synchronized (MONITOR) {
                    out.println("producer in MONITOR");
                    try {
                        if (hamburger == null) {
                            out.println("producer makes hamburger");
                            hamburger = new Hamburger();
                            out.println("producer notifies");
                            MONITOR.notify();
                            out.println("producer notified");
                        }
                        out.println("producer is going to wait");
                        MONITOR.wait();
                        out.println("producer is awaken");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                out.println("producer after MONITOR");
            }
        });
        producer.start();
    }
}
