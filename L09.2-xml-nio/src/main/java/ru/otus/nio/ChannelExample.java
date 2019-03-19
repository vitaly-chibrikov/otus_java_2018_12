package ru.otus.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author sergey
 * created on 25.09.18.
 */
public class ChannelExample {
    public static void main(String[] args) throws IOException, URISyntaxException {
        new ChannelExample().go();
    }

    private void go() throws IOException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("share.xml").toURI());
        FileChannel fileChannel = FileChannel.open(path);

        ByteBuffer buffer = ByteBuffer.allocate(10);

        int bytesCount;
        StringBuilder sb = new StringBuilder();
        do {
            bytesCount = fileChannel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                sb.append((char)buffer.get());
            }
            buffer.flip();
        } while (bytesCount > 0);
        fileChannel.close();
        System.out.println(sb);
    }
}
