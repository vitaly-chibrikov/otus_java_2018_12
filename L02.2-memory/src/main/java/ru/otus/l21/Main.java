package ru.otus.l21;

import java.lang.management.ManagementFactory;

/**
 * VM options -Xmx512m -Xms512m
 * -XX:+UseCompressedOops //on
 * -XX:-UseCompressedOops //off
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 *
 * VM options -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:4000
 */
@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement", "UnusedAssignment"})
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 20_000_000;

        System.out.println("Starting the loop");
        while (true) {
            long mem = getMem();
            System.out.println("Mem: " + mem);

            Object[] array = new Object[size];

            long mem2 = getMem();
            System.out.println("Reference size: " + (mem2 - mem) / array.length);

            for (int i = 0; i < array.length; i++) {
                //array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]);
                //array[i] = new String(new byte[0]);
                //array[i] = new MyClass();
                //array[i] = new byte[1]; //16 or 24 with -XX:-UseCompressedOops
                array[i] = getObject();
            }

            long mem3 = getMem();
            System.out.println("Class: " + array[0].getClass().getName() + " size: " + (mem3 - mem2) / array.length);

            array = null;
            System.out.println("Array is ready for GC");

            Thread.sleep(1000); //wait for 1 sec
        }
    }

    private static Object getObject() {
        return new String();
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static class MyClass {
        private boolean b = true;
        private boolean b2 = true;// +1
        private boolean b3 = true;
        private boolean b4 = true;// +1
        private boolean b5 = true;// +1
        //private byte b2 = 0;
        //private byte b3 = 0;     // +1
        //private byte b4 = 0;
        //private byte b5 = 0;
        //private int i = 0;      // +4
        //private long l = 1;     // +8
    }
}
