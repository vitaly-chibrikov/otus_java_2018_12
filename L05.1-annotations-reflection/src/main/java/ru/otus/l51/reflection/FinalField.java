package ru.otus.l51.reflection;

public class FinalField {
    private final int value;

    public FinalField(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
