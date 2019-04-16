package ru.otus.l141;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

//TODO please FIXME with ConcurrentHashMap
// Вопросы:
// - Какую коллекцию будем менять и на какую?
// - Фиксим тест сейчас!
// - *Для какого сценария по нагрузке больше всего подходит ConcurrentHashMap?
public class FixMe3WithConcurrentHashMapUnitTest {
    @Test
    public void testConcurrentHashMapWorksGreat() throws InterruptedException {

        final Map<String, String> map = new HashMap<>();
        final CountDownLatch latch = new CountDownLatch(1);
        List<Throwable> throwables = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            try {
                latch.await();
                for (int i = 0; i < 1000; i++) {
                    out.println("starting adding email " + i);
                    String s = randomAlphabetic(10) + "@gmail.com";
                    map.put(s, s);
                    out.println("finishing adding email " + i);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                latch.await();
                for (int i = 0; i < 1000; i++) {
                    out.println("starting read iteration " + i);
                    map.forEach((k, v) -> out.println(k));
                    out.println("finishing read iteration " + i);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
        });

        t1.start();
        t2.start();

        latch.countDown();

        t1.join();
        t2.join();

        if (!throwables.isEmpty()) {
            Assert.fail(throwables.toString());
        }
    }
}
