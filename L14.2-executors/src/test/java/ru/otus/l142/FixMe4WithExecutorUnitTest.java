package ru.otus.l142;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;
import static java.lang.System.out;

//TODO 1) FIXME with Executors.newSingleThreadExecutor(), обратите внимание что у нас один поток и неограниченная очередь, пришлите время выполнения
// 2) Посмотрите результаты вывода в консоль, нет ли вопросов? Если нужно, внесите правки чтобы вопросы решились;
// 3) Поменяйте реализацию на Executors.newFixedThreadPool(10), запустите и пришлите время выполнения
// 4) Поменяйте реализацию на Executors.newCachedThreadPool и пришлите время выполнения
// 5) *Посмотрите на на Executors.newScheduledThreadPool(), приведите примеры прикладных задач для чего он может быть полезен?
// 6) Поменяйте реализацию на new ThreadPoolExecutor() c ограниченной очередью и политикой отказов, выполняющей задачу в текущем потоке и пришлите время выполнения
// 7) Поменяйте Runnable на Callable, возвращайте бинарный результат добавления (true/false) во Future<Boolean> и блокируйтесь пока нет результата через Future.get(), пришлите время выполнения
// 8) А теперь решите без блокировки: проверяйте готово или не готово с помощью Future.isDone() и идите дальше, пришлите время выполнения
public class FixMe4WithExecutorUnitTest {
    @Test
    public void testExecutorWorksGreat() throws InterruptedException {
        out.println("start");

        long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        final List<Throwable> throwables = new ArrayList<>();

        class Populator extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        list.add(i);
                        out.println(format("add %s", i));
                    } catch (Throwable throwable) {
                        throwables.add(throwable);
                    }
                }
            }
        }

        class Summator extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        sum(list);
                        out.println(format("sum called %s time", i));
                    } catch (Throwable throwable) {
                        throwables.add(throwable);
                    }
                }
            }
        }

        Populator p1 = new Populator();
        Populator p2 = new Populator();

        Summator s1 = new Summator();
        Summator s2 = new Summator();

        p1.start();
        p2.start();
        s1.start();
        s2.start();

        p1.join();
        p2.join();
        s1.join();
        s2.join();

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
