package ru.otus.messaging_system;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

//TODO please FIXME with monitor
// Вопросы:
// - Что делает это многопоточное приложение?
// - Какие есть проблемы в данном многопоточном приложении?
// - Из какого потока летит исключение?
// - Из какого метода летит исключение?
// - Какие есть варианты решения этой проблемы?
// - Какой объект может быть монитором?
// - Чем плохо решение с монитором?
// - Какие проблемы остаются в коде?
// - Для чего тут нужен CountDownLatch?*
// - Зачем вызывать join() на потоках?*
public class UnitTest1 {
    @Test
    public void testIteratorFailFast() throws InterruptedException {

        final List<String> list = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(1);
        List<Throwable> throwables = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            try {
                latch.await();
                for (int i = 0; i < 10000; i++) {
                    System.out.println("starting adding email " + i);
                    list.add(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");
                    System.out.println("finishing adding email " + i);
                }
            } catch (Throwable throwable) {
                throwables.add(throwable);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                latch.await();
                for (int i = 0; i < 1000; i++) {
                    System.out.println("starting read iteration" + i);
                    list.forEach(System.out::println);
                    System.out.println("finishing read iteration" + i);
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
