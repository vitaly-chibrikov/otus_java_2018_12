package ru.otus.domain;

public class MessageForClient {
    private String messageStr;

    public MessageForClient() {
    }

    public MessageForClient(String messageStr) {
        this.messageStr = messageStr;
    }

    public String getMessageStr() {
        return messageStr;
    }

    public void setMessageStr(String messageStr) {
        this.messageStr = messageStr;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageStr='" + messageStr + '\'' +
                '}';
    }
}
