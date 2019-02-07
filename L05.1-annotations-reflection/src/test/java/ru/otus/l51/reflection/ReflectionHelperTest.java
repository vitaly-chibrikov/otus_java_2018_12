package ru.otus.l51.reflection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by tully.
 */
class ReflectionHelperTest {
    @Test
    void instantiate() {
        final TestClass testClass = ReflectionHelper.instantiate(TestClass.class);
        assertEquals(TestClass.DEFAULT_A, testClass.getA());
        assertEquals(TestClass.DEFAULT_S, testClass.getS());

        assertEquals(1, ReflectionHelper.instantiate(TestClass.class, 1).getA());
        assertEquals("A", ReflectionHelper.instantiate(TestClass.class, 1, "A").getS());
    }

    @Test
    void getFieldValue() {
        assertEquals("A", ReflectionHelper.getFieldValue(new TestClass(1, "A"), "s"));
        assertEquals(1, ReflectionHelper.getFieldValue(new TestClass(1, "B"), "a"));
    }

    @Test
    void setFieldValue() {
        final TestClass test = new TestClass(1, "A");
        assertEquals("A", test.getS());
        ReflectionHelper.setFieldValue(test, "s", "B");
        assertEquals("B", test.getS());
    }

    @Test
    void callMethod() {
        assertEquals("A", ReflectionHelper.callMethod(new TestClass(1, "A"), "getS"));

        final TestClass test = new TestClass(1, "A");
        ReflectionHelper.callMethod(test, "setDefault");
        assertEquals("", test.getS());
    }

}
