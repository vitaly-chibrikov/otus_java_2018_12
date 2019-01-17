package ru.otus.l31.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

/**
 * export JAVA_HOME=$(/usr/libexec/java_home -v11)
 * mvn package
 * java -cp target/benchmarks.jar ru.otus.l31.benchmark.ListBenchmark
 */

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ListBenchmark {
    @State(Scope.Thread)
    public static class ListsState {
        private List<String> arrayList = new ArrayList<>();
        private List<String> linkedList = new LinkedList<>();

        @Setup(Level.Iteration)
        public void setup() {
            for (int i = 0; i < 1000; i++) {
                final String value = String.valueOf(i);
                arrayList.add(value);
                linkedList.add(value);
            }
        }
    }

    @Benchmark
    public void arrayListInsert10v1(ListsState state, Blackhole blackhole) {
        insert100v1(state.arrayList, "value1");
        blackhole.consume(state.arrayList);
    }

    @Benchmark
    public void linkedListInsert10v1(ListsState state, Blackhole blackhole) {
        insert100v1(state.linkedList, "value2");
        blackhole.consume(state.linkedList);
    }

    @Benchmark
    public void arrayListInsert10v2(ListsState state, Blackhole blackhole) {
        insert100v2(state.arrayList, "value1");
        blackhole.consume(state.arrayList);
    }

    @Benchmark
    public void linkedListInsert10v2(ListsState state, Blackhole blackhole) {
        insert100v2(state.linkedList, "value2");
        blackhole.consume(state.linkedList);
    }

    private static <T> void insert100v1(List<T> list, T value) {
        for (int i = 0; i < 100; i++) {
            //noinspection MagicNumber
            list.add(450, value);
        }
    }

    private static <T> void insert100v2(List<T> list, T value) {
        final ListIterator<T> iterator = list.listIterator();
        //noinspection MagicNumber
        for (int i = 0; i < 450; i++) {
            iterator.next();
        }
        for (int i = 0; i < 100; i++) {
            iterator.add(value);
        }
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(ListBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(50)
                .measurementIterations(50)
                .build();

        new Runner(options).run();
    }
}
