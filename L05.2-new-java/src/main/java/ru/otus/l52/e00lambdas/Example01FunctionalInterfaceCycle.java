package ru.otus.l52.e00lambdas;

public class Example01FunctionalInterfaceCycle {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //noinspection Convert2Lambda
            final MyInterface oldStyle = new MyInterface() {
                @Override
                public void doStuff() {
                    System.out.println("doing stuff old style");
                }
            };
            System.out.println(oldStyle.toString());
            oldStyle.doStuff();
        }

        for (int i = 0; i < 10; i++) {
            final MyInterface newStyle = () -> System.out.println("doing stuff new style");
            System.out.println(newStyle.toString());
            newStyle.doStuff();
        }
    }

    @FunctionalInterface
    interface MyInterface {
        void doStuff();
    }
}
