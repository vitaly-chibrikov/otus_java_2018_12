package ru.itvitality.otus.java.server;

/**
 * Created by tully.
 */
public interface NonBlockingEchoSocketMsgServerMBean {
    boolean getRunning();

    void setRunning(boolean running);
}
