package ru.otus.logging.l062logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ChildClass {
    private static final Logger LOG = LoggerFactory.getLogger(ChildClass.class);

    void doSomething() {
        LOG.warn("child class warning");
    }
}
