package ru.otus.mongoDemo;


import com.mongodb.MongoTimeoutException;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.MINUTES;


/**
 * @author sergey
 * created on 16.10.18.
 */
public class SubscriberHelpers {

    public static class ObservableSubscriber<T> implements Subscriber<T> {
        private final CountDownLatch latch = new CountDownLatch(1);
        private final List<T> results = new ArrayList<>();
        private final boolean printResults = true;

        private volatile Subscription subscription;
        private volatile Throwable error;

        public ObservableSubscriber() {
        }

        @Override
        public void onSubscribe(final Subscription s) {
            subscription = s;
            subscription.request(Integer.MAX_VALUE);
        }

        @Override
        public void onNext(final T t) {
            results.add(t);
            if (printResults) {
                System.out.println(t);
            }
         }

        @Override
        public void onError(final Throwable t) {
            error = t;
            System.out.println(t.getMessage());
            onComplete();
        }

        @Override
        public void onComplete() {
            latch.countDown();
        }

        public List<T> getResults() {
            return results;
        }

        public void await() throws Throwable {
            if (!latch.await(10, MINUTES)) {
                throw new MongoTimeoutException("Publisher timed out");
            }
            if (error != null) {
                throw error;
            }
        }
    }

    public static class OperationSubscriber<T> extends ObservableSubscriber<T> {
        @Override
        public void onSubscribe(final Subscription s) {
            super.onSubscribe(s);
            s.request(Integer.MAX_VALUE);
        }
    }

    public static class PrintDocumentSubscriber extends OperationSubscriber<Document> {
        @Override
        public void onNext(final Document document) {
            super.onNext(document);
            System.out.println(document.toJson());
        }
    }
}
