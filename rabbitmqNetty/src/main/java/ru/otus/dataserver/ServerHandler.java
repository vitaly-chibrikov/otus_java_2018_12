package ru.otus.dataserver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private final static String QUEUE_NAME = "QUEUE_DATA";
    private final ConnectionFactory factory;
    private final Connection connection;
    private final Channel channel;

    ServerHandler() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        connection.close();
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        ByteBuf in = (ByteBuf) msg;
        StringBuilder sb = new StringBuilder();
        try {
            while (in.isReadable()) {
                sb.append((char) in.readByte());
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
        String data = sb.toString();
        logger.info("data from client: {}, length:{}", data, data.length());
        putToQueue(data);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private void putToQueue(String message) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    }
}