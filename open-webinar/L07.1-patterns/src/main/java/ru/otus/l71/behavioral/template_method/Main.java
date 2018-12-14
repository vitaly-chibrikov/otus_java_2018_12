package ru.otus.l71.behavioral.template_method;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) {
        Operation plus = new Plus();
        Operation minus = new Minus();

        plus.printResult(1,2);
        minus.printResult(1,2);
    }
}
