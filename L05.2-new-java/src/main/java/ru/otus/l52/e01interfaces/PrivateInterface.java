package ru.otus.l52.e01interfaces;

public interface PrivateInterface {
    default void printState() {
        print("State: {}");
    }

    default void printClass() {
        print(this.getClass().getCanonicalName());
    }

    private void print(String value) {
        systemOutPrintln(value);
    }

    private static void systemOutPrintln(Object value) {
        System.out.println(value);
    }
}
