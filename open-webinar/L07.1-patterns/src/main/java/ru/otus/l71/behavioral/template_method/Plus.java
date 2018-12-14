package ru.otus.l71.behavioral.template_method;

/**
 * Created by tully.
 */
public class Plus extends Operation {

    @Override
    protected int apply(int a, int b) {
        return a + b;
    }
}
