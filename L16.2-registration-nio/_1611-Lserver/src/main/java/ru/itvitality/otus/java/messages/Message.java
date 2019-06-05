package ru.itvitality.otus.java.messages;

public abstract class Message {
    public static final String CLASS_NAME_VARIABLE = "className";
    private final String className;

    public Message(Class<?> klass) {
        this.className = klass.getName();
    }
}
