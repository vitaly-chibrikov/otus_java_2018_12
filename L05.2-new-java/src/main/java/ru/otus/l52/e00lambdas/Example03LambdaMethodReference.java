package ru.otus.l52.e00lambdas;

public class Example03LambdaMethodReference {
    public static void main(String[] args) {
        final Data data = new Data(1);

        //noinspection Convert2MethodRef
        final DataExtractor extractor11 = d -> extractInt(d);

        final DataExtractor extractor12 = Example03LambdaMethodReference::extractInt;

        //noinspection Convert2MethodRef
        final DataExtractor extractor21 = d -> d.getIntValue();

        final DataExtractor extractor22 = Data::getIntValue; // ???
        // see bytecode
    }

    static final class Data {
        private final int intValue;

        Data(int intValue) {
            this.intValue = intValue;
        }

        int getIntValue() {
            return intValue;
        }
    }

//    static int extractInt(Data data) throws Exception {
    static int extractInt(Data data) {
        return data.getIntValue();
    }

    @FunctionalInterface
    interface DataExtractor {
        int extractData(Data data) throws Exception;
    }
}
