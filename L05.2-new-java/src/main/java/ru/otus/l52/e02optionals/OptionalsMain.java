package ru.otus.l52.e02optionals;

import java.util.Optional;
import java.util.Random;

public class OptionalsMain {
    private static final Random RND = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            final Optional<String> optionalValue = getOptionalValue();
//            if (optionalValue.isPresent()) { // "NEVER" do that
//                System.out.println(optionalValue.get());
//            } else {
//                System.out.println(getDefaultValue());
//            }
//
//              noinspection OptionalGetWithoutIsPresent
//            System.out.println(getOptionalValue().get().length());
//
//            System.out.println(getOptionalValue().orElse(getDefaultValue()));
//
//            System.out.println(getOptionalValue().orElseGet(OptionalsMain::getDefaultValue));
//
//            System.out.println(getOptionalValue().orElseThrow());
//
//            getOptionalValue().ifPresent(System.out::println);
//
//            getOptionalValue().or(OptionalsMain::getOptionalValue);

            System.out.println(getOptionalValue().map(String::length).orElse(0));
        }
    }

    private static String getValue() {
        if (RND.nextBoolean()) {
            return null;
        }
        return "value";
    }

    private static Optional<String> getOptionalValue() {
//        return Optional.ofNullable(getValue());

        if (RND.nextBoolean()) {
            return Optional.empty();
        }
        return Optional.of("optional value");
    }

    private static String getDefaultValue() {
        return "default value";
    }
}
