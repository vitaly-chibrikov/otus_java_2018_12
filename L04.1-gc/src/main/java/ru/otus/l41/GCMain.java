package ru.otus.l41;

/*
 -agentlib:jdwp=transport=dt_socket,address=14000,server=y,suspend=n
 -Xms512m
 -Xmx512m
 -XX:MaxMetaspaceSize=256m
 -verbose:gc
 -Xlog:gc*:file=./logs/gc_%t_pid_%p.log
 -Dcom.sun.management.jmxremote.port=15000
 -Dcom.sun.management.jmxremote.authenticate=false
 -Dcom.sun.management.jmxremote.ssl=false
 -XX:+HeapDumpOnOutOfMemoryError
 -XX:HeapDumpPath=./dumps
 -XX:OnOutOfMemoryError="kill -3 %p"
 -XX:+UseG1GC
 -XX:+UseConcMarkSweepGC
 -XX:+UseSerialGC
 -XX:+UseParallelGC

 jps -- list vms or ps -e | grep java
 jstack <pid> >> threaddumps.log -- get dump from pid
 jinfo -- list VM parameters
 jvisualvm-- analyze heap dump
 */

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class GCMain {
    public static Map<Long, Object[]> STORAGE = new HashMap<>();

    @SuppressWarnings("OverlyBroadThrowsClause")
    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        final int size = 5 * 1000 * 1000;
//        final int size = 50 * 1000 * 1000;//for OOM with -Xms512m
//        final int size = 50 * 1000 * 100; //for small dump

        final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        final ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        final Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setSize(size);
        mbean.run();
    }
}
