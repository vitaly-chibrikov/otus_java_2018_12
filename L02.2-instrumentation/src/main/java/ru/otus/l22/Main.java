package ru.otus.l22;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by tully.
 * VM options -Xmx512m -Xms512m
 * VM options  -javaagent:../L02.2-agent/target/agent.jar
 * VM options  -XX:+UseSerialGC
 * -XX:+UseTLAB Uses thread-local object allocation blocks.
 */

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        System.out.println("Object size: " + Agent.instance().getInstrumentation().getObjectSize(new Object()));
    }
}
