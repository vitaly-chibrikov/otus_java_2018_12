package ru.otus.l062logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("Duplicates")
public class LogbackMain {
    private static final Logger LOG = LoggerFactory.getLogger(LogbackMain.class);

    public static void main(String[] args) {
        //noinspection InfiniteLoopStatement
        for (; ; ) {
            LOG.info("info message");
            if (args == null || args.length == 0) {
                LOG.error("error!", new IllegalArgumentException("args are empty"));
            }

            new ChildClass().doSomething();
        }
    }
}
