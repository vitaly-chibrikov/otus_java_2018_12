package ru.otus.l52.e00lambdas;

public class Example00FunctionalInterface {
    public static void main(String[] args) {
        //noinspection Convert2Lambda
        final MyInterface oldStyle = new MyInterface() {
            @Override
            public void doStuff() {
                System.out.println("doing stuff old style");
            }
        };
        oldStyle.doStuff();

        final MyInterface newStyle = () -> System.out.println("doing stuff new style");
        newStyle.doStuff();
    }

    @FunctionalInterface
    interface MyInterface {
        void doStuff();
    }
}
