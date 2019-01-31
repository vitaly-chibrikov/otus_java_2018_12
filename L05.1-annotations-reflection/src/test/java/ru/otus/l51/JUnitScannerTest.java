package ru.otus.l51;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * see {@link Class#getDeclaredMethods()} with {@code "JUnitScannerTest".equals(this.getSimpleName())}
 * see {@link org.junit.jupiter.engine.discovery.predicates.IsTestClassWithTests#isTestMethod}
 */
@SuppressWarnings("JavadocReference")
class JUnitScannerTest {
    @Test
    void test() {
        Assertions.assertEquals(4, 2 + 2);
    }
}
