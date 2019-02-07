package ru.otus.l52.e00interfaces;

/**
 * See also {@link java.util.Collection}
 */
public class DefaultInterfaceMain {
    public static void main(String[] args) {
        final SmallClass smallState = new SmallClass("small state");
        smallState.printClass();
        smallState.printFullInfo();

        final BigClass bigState = new BigClass("big state");
        bigState.printClass();
        bigState.printFullInfo();
    }

    private static final class SmallClass implements DefaultInterface {
        private final String state;

        private SmallClass(String state) {
            this.state = state;
        }

        @Override
        public String getState() {
            return state;
        }
    }

    private static final class BigClass implements DefaultInterface {
        private final String state;

        private BigClass(String state) {
            this.state = state;
        }

        @Override
        public String getState() {
            return state;
        }

        @SuppressWarnings("RedundantMethodOverride")
        @Override
        public void printFullInfo() {
            DefaultInterface.super.printFullInfo();
        }
    }
}
