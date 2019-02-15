package ru.otus.l062log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("Duplicates")
public class Log4jMain {
    private static final Logger LOG = LoggerFactory.getLogger(Log4jMain.class);

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
