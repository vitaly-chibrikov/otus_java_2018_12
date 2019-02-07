package ru.otus.l51.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    String author();
}
