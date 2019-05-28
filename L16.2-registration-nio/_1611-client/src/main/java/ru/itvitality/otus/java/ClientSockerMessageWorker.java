package ru.itvitality.otus.java;

import ru.itvitality.otus.java.workers.SocketMessageWorker;

import java.io.IOException;
import java.net.Socket;

public class ClientSockerMessageWorker extends SocketMessageWorker {


    private final Socket socket;

    public ClientSockerMessageWorker(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public ClientSockerMessageWorker(Socket socket) {
        super(socket);
        this.socket = socket;
    }

    @Override
    public void close() throws IOException {
        super.close();
        socket.close();
    }
}
