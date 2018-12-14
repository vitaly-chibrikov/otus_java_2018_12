package ru.otus.l71.behavioral.template_method;

/**
 * Created by tully.
 * <p>
 * Framework class in the Template method pattern.
 */
public abstract class Operation {

    public void printResult(int a, int b) {
       System.out.println(apply(a, b));
    }


    /**
     * Different algorithms of the subclasses.
     *
     * @param a the first argument
     * @param b the second argument
     * @return result of the algorithm
     */
    protected abstract int apply(int a, int b);
}
