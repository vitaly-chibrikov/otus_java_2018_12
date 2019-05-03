package ru.otus.l142;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

//TODO 1) FIXME with ReeentrantLock
// 2) tryLock()
// 3) conditions
public class FixMe1WithReentrantLockUnitTest {
    @Test
    public void testMonitorWorksGreat() throws InterruptedException {

        long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        List<Throwable> throwables = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    list.forEach(out::println);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        out.println(String.format("run time = %s millis", (System.currentTimeMillis() - start)));

        if (!throwables.isEmpty()) {
            Assert.fail(throwables.toString());
        }
    }
}
