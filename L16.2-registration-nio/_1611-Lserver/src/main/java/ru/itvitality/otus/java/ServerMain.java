package ru.itvitality.otus.java;

import ru.itvitality.otus.java.runner.ProcessRunnerImpl;
import ru.itvitality.otus.java.server.BlockingEchoSocketMsgServer;
import ru.itvitality.otus.java.server.EchoSocketMessageServer;
import ru.itvitality.otus.java.server.NonBlockingEchoSocketMsgServer;
import ru.itvitality.otus.java.server.NonBlockingLogSocketMsgServer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class ServerMain {
    private static final String CLIENT_START_COMMAND = "java -jar ../_1611-client/target/client.jar -port 5051";
    private static final int CLIENT_START_DELAY_SEC = 5;

    public static void main(String[] args) throws Exception {
        new ServerMain().start();
    }

    private void start() throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

//        startClient( executorService );

//        startLogServer();
//        startEchoWithRegistrationServer();
        startEchoServer();
//        startNonBlockingEchoServer();

        executorService.shutdown();

    }

    private void startNonBlockingEchoServer() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Server");
        NonBlockingEchoSocketMsgServer server = new NonBlockingEchoSocketMsgServer();
        mbs.registerMBean(server, name);

        server.start();
    }

    private void startEchoServer() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("ru.itvitality.otus.java:type=Server");
        EchoSocketMessageServer server = new EchoSocketMessageServer();
        mBeanServer.registerMBean(server, objectName);
        server.start();
    }

    private void startLogServer() throws Exception {
        new NonBlockingLogSocketMsgServer().start();
    }

    private void startEchoWithRegistrationServer() throws Exception {
        new BlockingEchoSocketMsgServer().start();
    }

    private void startClient(ScheduledExecutorService executorService) {
        executorService.schedule(() -> {
            try {
                new ProcessRunnerImpl().start(CLIENT_START_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, CLIENT_START_DELAY_SEC, TimeUnit.SECONDS);

    }
}
