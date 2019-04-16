package ru.otus.jmm;

/**
 * @author sergey
 * created on 07.11.18.
 */
public class CounterBroken {
    private int count = 0;
    private final static int limit = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        new CounterBroken().go();
    }

    private void inc() {
        for (int i = 0; i < limit; i++) {
            count++;
        }
    }

    private void go() throws InterruptedException {
        Thread thread1 = new Thread(this::inc);
        Thread thread2 = new Thread(this::inc);
        Thread thread3 = new Thread(this::inc);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("CounterBroken:" + count);
    }
}

