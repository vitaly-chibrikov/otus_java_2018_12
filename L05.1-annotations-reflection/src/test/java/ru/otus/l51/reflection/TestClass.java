package ru.otus.l51.reflection;

/**
 * Created by tully.
 */
@SuppressWarnings("unused")
public class TestClass {
    public static final int DEFAULT_A  = 0;
    public static final String DEFAULT_S = "";

    private int a;
    private String s;

    public TestClass() {
        this(DEFAULT_A, DEFAULT_S);
    }

    public TestClass(int a) {
       this(a, DEFAULT_S);
    }

    public TestClass(Integer a) {
        this(a, DEFAULT_S);
    }

    TestClass(Integer a, String s) {
        this.a = a;
        this.s = s;
    }

    int getA() {
        return a;
    }

    String getS() {
        return s;
    }

    private void setDefault(){
        a = DEFAULT_A;
        s = DEFAULT_S;
    }
}
