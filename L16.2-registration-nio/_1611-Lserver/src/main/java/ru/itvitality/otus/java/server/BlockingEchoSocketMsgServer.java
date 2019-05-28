package ru.itvitality.otus.java.server;

import ru.itvitality.otus.java.annotation.Blocks;
import ru.itvitality.otus.java.messages.Message;
import ru.itvitality.otus.java.workers.MessageWorker;
import ru.itvitality.otus.java.workers.SocketMessageWorker;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tully.
 */
public class BlockingEchoSocketMsgServer {
    private static final Logger logger = Logger.getLogger(NonBlockingEchoSocketMsgServer.class.getName());

    private static final int THREADS_NUMBER = 1;
    private static final int PORT = 5050;
    private static final int ECHO_DELAY = 100;

    private final ExecutorService executor;
    private final List<MessageWorker> workers;

    public BlockingEchoSocketMsgServer() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        workers = new CopyOnWriteArrayList<>();
    }

    @Blocks
    public void start() throws Exception {
        executor.submit(this::echo);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket socket = serverSocket.accept(); //blocks
                SocketMessageWorker worker = new SocketMessageWorker(socket);

                worker.addShutdownRegistration(() -> workers.remove(worker));

                worker.init();
                workers.add(worker);
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void echo() {
        while (true) {
            for (MessageWorker client : workers) {
                System.out.println(" workers:" + workers.size());
                Message msg = client.pool(); //get
                while (msg != null) {
                    System.out.println("Echoing the message: " + msg.toString());
                    client.send(msg);
                    msg = client.pool();
                }
            }
            try {
                Thread.sleep(ECHO_DELAY);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.toString());
            }
        }
    }
}
