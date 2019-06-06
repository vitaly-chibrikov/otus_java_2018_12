package ru.otus.dataclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private int currentValue = 0;

    private String generateValue() {
        currentValue++;
        return "value" + currentValue;
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws InterruptedException {
        ByteBuf buffer = null;
        try {
            for (int idx = 0; idx < Integer.MAX_VALUE; idx++) {
                buffer = Unpooled.copiedBuffer(generateValue(), CharsetUtil.UTF_8);
                ChannelFuture future = channelHandlerContext.writeAndFlush(buffer);
                future.await();
                buffer.clear();
                sleep();
            }
        } finally {
            if (buffer != null) {
                ReferenceCountUtil.release(buffer);
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
