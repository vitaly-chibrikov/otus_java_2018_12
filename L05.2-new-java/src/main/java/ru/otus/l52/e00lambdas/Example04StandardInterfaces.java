package ru.otus.l52.e00lambdas;

import ru.otus.l52.e00lambdas.Example03LambdaMethodReference.Data;
import ru.otus.l52.e00lambdas.Example03LambdaMethodReference.DataExtractor;

import java.util.function.Function;

import static ru.otus.l52.e00lambdas.Example03LambdaMethodReference.extractInt;

/**
 * see {@link java.util.function} classes
 * Predicate
 * Consumer
 * Supplier
 * Function
 */
public class Example04StandardInterfaces {
    public static void main(String[] args) {
        final Data data = new Data(1);

        //noinspection Convert2MethodRef
        final DataExtractor extractor11 = d -> extractInt(d);
//
        final DataExtractor extractor12 = Example03LambdaMethodReference::extractInt;

        //noinspection Convert2Lambda,Anonymous2MethodRef
        final Function<Data, Integer> funExtractor1 = new Function<>() {
            @Override
            public Integer apply(Data data) {
                return data.getIntValue();
            }
        };

        //noinspection Convert2MethodRef
        final Function<Data, Integer> funExtractor2 = d -> extractInt(d);

        final Function<Data, Integer> funExtractor3 = Example03LambdaMethodReference::extractInt;

//        final Function<Data, Integer> funExtractor4 = extractor11; // ! not allowed !
    }
}
