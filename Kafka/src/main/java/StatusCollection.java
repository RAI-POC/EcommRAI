import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Properties;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

public class StatusCollection {
        public static void main(String[] args) {
            Gson gson = new Gson();
            MongoClient client = new MongoClient();
            MongoDatabase db = client.getDatabase("onlineorderdb");
            MongoCollection<Document> statusInfoDocument = db.getCollection("Status");
            ArrayList<String> statusInfoTopic = new ArrayList<String>();
            Properties statusInfoConsumerProp = new Properties();

            statusInfoConsumerProp.put("bootstrap.servers", "localhost:9092");
            statusInfoConsumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            statusInfoConsumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            statusInfoConsumerProp.put("group.id", "status");
            statusInfoConsumerProp.put("auto.offset.reset", "earliest");
            statusInfoConsumerProp.put("client.id", "statusconsumer");
            KafkaConsumer myconsumer = new KafkaConsumer(statusInfoConsumerProp);
            statusInfoTopic.add("statusinfo");
            UpdateOptions updateOptions = new UpdateOptions();

            myconsumer.subscribe(statusInfoTopic);
            try {
                while (true) {
                    ConsumerRecords<String, String> myConsumerRecords = myconsumer.poll(10);
                    for (ConsumerRecord<String, String> myConsumerRecord : myConsumerRecords) {
                        Status statusInfo = gson.fromJson(myConsumerRecord.value(), Status.class);
                        System.out.println("statusInfo " + statusInfo.toString());
                        System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s",
                                myConsumerRecord.topic(), myConsumerRecord.partition()
                                , myConsumerRecord.offset(), myConsumerRecord.key(), myConsumerRecord.value()));
                        Bson filter = Filters.eq("_id", statusInfo.getOrderNumber());
                        Bson update = new Document("$set",
                               statusInfo.getStatusAsDocument());
                        statusInfoDocument.updateOne(filter,
                              update,updateOptions.upsert(true));
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                myconsumer.close();
            }

        }
}
