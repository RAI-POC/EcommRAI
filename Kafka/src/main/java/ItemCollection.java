import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Properties;

public class ItemCollection {
        public static void main(String[] args) {
            Gson gson = new Gson();
            MongoClient client = new MongoClient();
            MongoDatabase db = client.getDatabase("onlineorderdb");
            MongoCollection<Document> itemInfoDocument = db.getCollection("Item");
            ArrayList<String> itemInfoTopic = new ArrayList<String>();
            Properties itemInfoConsumerProp = new Properties();

            itemInfoConsumerProp.put("bootstrap.servers", "localhost:9092");
            itemInfoConsumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            itemInfoConsumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            itemInfoConsumerProp.put("group.id", "item");
            itemInfoConsumerProp.put("auto.offset.reset", "earliest");
            itemInfoConsumerProp.put("client.id", "itemconsumer");
            KafkaConsumer myconsumer = new KafkaConsumer(itemInfoConsumerProp);
            itemInfoTopic.add("iteminfo");

            myconsumer.subscribe(itemInfoTopic);
            try {
                while (true) {
                    ConsumerRecords<String, String> myConsumerRecords = myconsumer.poll(10);
                    for (ConsumerRecord<String, String> myConsumerRecord : myConsumerRecords) {
                        Item itemInfo = gson.fromJson(myConsumerRecord.value(), Item.class);
                        System.out.println("orderInfo " + itemInfo.toString());
                        System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s",
                                myConsumerRecord.topic(), myConsumerRecord.partition()
                                , myConsumerRecord.offset(), myConsumerRecord.key(), myConsumerRecord.value()));
                        itemInfoDocument.insertOne(itemInfo.getItemInfoAsDocument());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                myconsumer.close();
            }

        }
}
