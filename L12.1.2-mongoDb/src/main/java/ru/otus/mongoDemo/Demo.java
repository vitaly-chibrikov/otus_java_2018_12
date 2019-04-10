package ru.otus.mongoDemo;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.reactivestreams.client.ChangeStreamPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.reactivestreams.client.Success;
import org.bson.Document;

import static ru.otus.mongoDemo.SubscriberHelpers.ObservableSubscriber;
import static ru.otus.mongoDemo.SubscriberHelpers.PrintDocumentSubscriber;

/**
 * @author sergey
 * created on 16.10.18.
 */
public class Demo {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private MongoCollection<Document> collection;

    public Demo() {
        mongoClient = MongoClients.create("mongodb://localhost");
        database = mongoClient.getDatabase("test");
        collection = database.getCollection("products");
    }

    public static void main(String[] args) throws Throwable {
        Demo demo = new Demo();
        demo.start();
        demo.writer();
        demo.reader();
    }

    private void start() throws Throwable {
        Document doc = new Document("key", System.currentTimeMillis())
                .append("item", "apple")
                .append("qty", 11);

        ObservableSubscriber<Success> subscriber = new ObservableSubscriber<>();
        collection.insertOne(doc).subscribe(subscriber);
        subscriber.await();

        PrintDocumentSubscriber subscriberPrinter = new PrintDocumentSubscriber();
        collection.find(new Document("item", "apple")).subscribe(subscriberPrinter);
        subscriberPrinter.await();
    }

    private void writer() {
        new Thread(() -> {
            try {
                int counter = 0;
                ObservableSubscriber<Success> subscriber = new ObservableSubscriber<>();
                while (true) {
                    System.out.println("counter:" + counter);
                    Document doc = new Document("key", System.currentTimeMillis())
                            .append("item", "apple")
                            .append("counter", counter++)
                            .append("qty", 11);

                    collection.insertOne(doc).subscribe(subscriber);
                    subscriber.await();
                    Thread.sleep(3_000);
                }
            } catch (Throwable ex) {
                System.out.println(ex);
            }
        }).start();
    }

    private void reader() throws Throwable {
        // Create the change stream publisher.
        ChangeStreamPublisher<Document> publisher = collection.watch();

        // Create a subscriber
        ObservableSubscriber<ChangeStreamDocument<Document>> subscriber = new ObservableSubscriber<>();
        publisher.subscribe(subscriber);

        subscriber.await();
    }

}
