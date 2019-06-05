package ru.itvitality.otus.java.workers;

import ru.itvitality.otus.java.annotation.Blocks;
import ru.itvitality.otus.java.messages.Message;

import java.io.IOException;

public interface MessageWorker {
    Message pool();

    void send(Message message);

    @Blocks
    Message take() throws InterruptedException;

    void close() throws IOException;
}
