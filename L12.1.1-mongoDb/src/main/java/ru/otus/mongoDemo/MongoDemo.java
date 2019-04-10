package ru.otus.mongoDemo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.bson.BsonDocument;
import org.bson.BsonJavaScript;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class MongoDemo {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public MongoDemo() throws IOException {

        EmbeddedMongo embeddedMongo = new EmbeddedMongo();
        embeddedMongo.run();

        String bindIp = embeddedMongo.getBindIp();
        int port = embeddedMongo.getPort();
        mongoClient = new MongoClient(bindIp, port);
        database = mongoClient.getDatabase("test");

        functionDemo();
    }

    public static void main(String[] args) throws Throwable {
        new MongoDemo();
    }

    private void functionDemo() {
        BsonDocument myAddFunction = new BsonDocument("value",
                new BsonJavaScript("function (x, y){ return x + y; }"));

        database.getCollection("system.js").updateOne(
                new Document("_id", "myAddFunction"),
                new Document("$set", myAddFunction),
                new UpdateOptions().upsert(true));

        database.runCommand(new Document("$eval", "db.loadServerScripts()"));

        Document result = database.runCommand(new Document("$eval", "myAddFunction(2, 3)"));
        System.out.println(result.toJson());
    }

    private static class EmbeddedMongo {

        private String bindIp;
        private int port;

        public String getBindIp() {
            return bindIp;
        }

        public int getPort() {
            return port;
        }

        public void run() throws IOException {
            MongodStarter starter = MongodStarter.getDefaultInstance();

            bindIp = Network.getLocalHost().getHostAddress();
            port = 27017;

            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                    .build();

            MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();
        }
    }
}
