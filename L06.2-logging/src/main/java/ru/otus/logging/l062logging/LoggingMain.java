package ru.otus.logging.l062logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingMain {
    /***
     * see log live template
     */
    private static final Logger LOG = LoggerFactory.getLogger(LoggingMain.class);

    public static void main(String... args) {
        //noinspection InfiniteLoopStatement
        for (; ; ) {
            LOG.info("info message: {}, {}, {}", "string data", 10, 1.0d);
            if (args == null || args.length == 0) {
                LOG.error("error!", new IllegalArgumentException("args are empty"));
            }

            new ChildClass().doSomething();
        }
    }
}
