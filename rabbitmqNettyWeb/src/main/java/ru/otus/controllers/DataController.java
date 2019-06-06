package ru.otus.controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.otus.domain.MessageForClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Controller
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    private static final int ATTEMPT_LIMIT = 10;
    private final static String QUEUE_NAME = "QUEUE_DATA";

    private Channel channel;
    private final SimpMessagingTemplate template;

    DataController(SimpMessagingTemplate template) throws IOException, InterruptedException {
        this.template = template;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        for (int attempt = 0; attempt < ATTEMPT_LIMIT; attempt++) {
            try {
                logger.info("connection to Rabbit, attempt:{}", attempt);
                connection = factory.newConnection();
            } catch (Exception ex) {
                logger.error("connection to Rabbit failed:" + ex.getMessage());
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            }
        }
        if (connection != null) {
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            queueClient();
        } else {
            logger.error("Application failed to start");
        }

    }

    private void queueClient() {
        try {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String data = new String(delivery.getBody(), "UTF-8");
                logger.info("Received '{}'", data);
                this.template.convertAndSend("/topic/response", new MessageForClient(HtmlUtils.htmlEscape(data)));
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            logger.error("error", e);
        }
    }

}
