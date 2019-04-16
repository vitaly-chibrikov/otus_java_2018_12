package ru.otus.l141.app;

import ru.otus.l141.messageSystem.Addressee;

/**
 * Created by tully.
 */
public interface FrontendService extends Addressee {
    void init();

    void handleRequest(String login);

    void addUser(int id, String name);
}

