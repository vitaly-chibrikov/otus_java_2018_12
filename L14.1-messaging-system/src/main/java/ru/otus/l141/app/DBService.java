package ru.otus.l141.app;

import ru.otus.l141.messageSystem.Addressee;

/**
 * Created by tully.
 */
public interface DBService extends Addressee {
    void init();

    int getUserId(String name);
}
