package ru.otus.l142;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.lang.String.format;
import static java.lang.System.out;

//TODO 1) FIXME with ReeentrantLock
// 2) tryLock()
// 3) conditions
public class FixMe1WithReentrantLockUnitTest {
    @Test
    public void testReentrantLockWorksGreat() throws InterruptedException {
        out.println("start");

        long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        List<Throwable> throwables = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            int count = 0;
            try {
                latch.await();
                for (int i = 0; i < 1000; i++) {
                    count++;
                    list.add(i);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
            out.println(format("add called %s times", count));
        });
        Thread t2 = new Thread(() -> {
            int count = 0;
            try {
                latch.await();
                for (int i = 0; i < 1000; i++) {
                    count++;
                    sum(list);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
            out.println(format("sum called %s times", count));
        });

        t1.start();
        t2.start();

        latch.countDown();

        t1.join();
        t2.join();

        out.println(format("execution time = %s millis", (System.currentTimeMillis() - start)));

        if (!throwables.isEmpty()) {
            Assert.fail(throwables.toString());
        }
    }

    private void sum(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        BigDecimal sum = new BigDecimal(0);
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            sum = sum.add(BigDecimal.valueOf(next));
        }
        out.println(sum);
    }
}
