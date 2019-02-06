package ru.otus.l52.e00lambdas;

@SuppressWarnings("Duplicates")
public class Example02FunctionalInterfaceCycleSameObject {
    public static void main(String[] args) {
        MyInterface previous = null;
        for (int i = 0; i < 10; i++) {
            //noinspection Convert2Lambda
            final MyInterface oldStyle = new MyInterface() {
                @Override
                public void doStuff() {
                    System.out.println("doing stuff old style");
                }
            };
            if (previous != null) {
                System.out.println(oldStyle == previous);
            }
            previous = oldStyle;
            System.out.println(oldStyle.toString());
            oldStyle.doStuff();
        }

        previous = null;
        for (int i = 0; i < 10; i++) {
            final MyInterface newStyle = () -> System.out.println("doing stuff new style");
            if (previous != null) {
                System.out.println(newStyle == previous);
            }
            previous = newStyle;
            System.out.println(newStyle.toString());
            newStyle.doStuff();
        }
    }

    @FunctionalInterface
    interface MyInterface {
        void doStuff();
    }
}
