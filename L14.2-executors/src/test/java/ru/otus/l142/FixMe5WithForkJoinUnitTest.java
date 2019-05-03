package ru.otus.l142;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.lang.System.out;

//TODO 1) посмотрим Executors.newWorkStealingPool()
// 2) создаем руками ForkJoinPool, есть action и task - RecursiveAction & RecursiveTask
// 3) with ForkJoin и RecursiveTask и переделываем sum на рекурсивный вариант
public class FixMe5WithForkJoinUnitTest {
    @Test
    public void testForkJoinWorksGreat() throws InterruptedException {
        out.println("start");

        long start = System.currentTimeMillis();
        final List<Integer> list = new CopyOnWriteArrayList<>();
        final List<Throwable> throwables = new ArrayList<>();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

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
                        class SumRecursiveTask extends RecursiveTask<BigDecimal> {
                            private final List<Integer> list;

                            public SumRecursiveTask(List<Integer> list) {
                                this.list = list;
                            }

                            @Override
                            protected BigDecimal compute() {
                                if (list.size() > 16) {
                                    int edge = list.size() / 2;
                                    List<Integer> left = list.subList(0, edge);
                                    List<Integer> right = list.subList(edge, list.size() - 1);
                                    SumRecursiveTask leftTask = new SumRecursiveTask(left);
                                    leftTask.fork();
                                    SumRecursiveTask rightTask = new SumRecursiveTask(right);
                                    rightTask.fork();
                                    return leftTask.join()
                                            .add(rightTask.join());
                                } else {
                                    return sum(list);
                                }
                            }
                        }

                        forkJoinPool.invoke(new SumRecursiveTask(List.copyOf(list)));
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

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);

        out.println(format("execution time = %s millis", (System.currentTimeMillis() - start)));

        if (!throwables.isEmpty()) {
            Assert.fail(throwables.toString());
        }
    }

    private BigDecimal sum(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        BigDecimal sum = new BigDecimal(0);
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            sum = sum.add(BigDecimal.valueOf(next));
        }
        out.println(sum);
        return sum;
    }
}
