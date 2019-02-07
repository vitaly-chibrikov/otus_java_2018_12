package ru.otus.l52.e01lambdas;

public class Example00FunctionalInterface {
    public static void main(String[] args) {
        //noinspection Convert2Lambda
        final Runnable oldStyle = new Runnable() {
            @Override
            public void run() {
                System.out.println("doing stuff old style");
            }
        };
        oldStyle.run();

        final Runnable newStyle = () -> System.out.println("doing stuff new style");
        newStyle.run();
    }

    @FunctionalInterface
    interface Runnable {
        void run();
    }
}
