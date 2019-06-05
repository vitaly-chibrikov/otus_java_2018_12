package ru.itvitality.otus.java.messages;

public class PingMessage extends Message {
    private final long time;

    public PingMessage() {
        super(PingMessage.class);
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PingMessage{");
        sb.append("time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
