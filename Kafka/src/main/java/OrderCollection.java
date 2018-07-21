import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.ArrayList;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.google.gson.Gson;

public class OrderCollection {
        public static void main(String[] args) {
            Gson gson = new Gson();
            MongoClient client = new MongoClient();
            MongoDatabase db = client.getDatabase("onlineorderdb");
            MongoCollection<Document> orderInfoDocument = db.getCollection("Order");
            ArrayList<String> orderInfoTopic = new ArrayList<String>();
            Properties orderInfoConsumerProp = new Properties();

            orderInfoConsumerProp.put("bootstrap.servers", "localhost:9092");
            orderInfoConsumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            orderInfoConsumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            orderInfoConsumerProp.put("group.id", "order");
            orderInfoConsumerProp.put("auto.offset.reset", "earliest");
            orderInfoConsumerProp.put("client.id", "orderconsumer");
            KafkaConsumer myconsumer = new KafkaConsumer(orderInfoConsumerProp);
            orderInfoTopic.add("orderinfo");
            //UpdateOptions updateOptions = new UpdateOptions();

            myconsumer.subscribe(orderInfoTopic);
            try {
                while (true) {
                    ConsumerRecords<String, String> myConsumerRecords = myconsumer.poll(10);
                    for (ConsumerRecord<String, String> myConsumerRecord : myConsumerRecords) {
                        Order orderInfo = gson.fromJson(myConsumerRecord.value(), Order.class);
                        System.out.println("orderInfo " + orderInfo.toString());
                        System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s",
                                myConsumerRecord.topic(), myConsumerRecord.partition()
                                , myConsumerRecord.offset(), myConsumerRecord.key(), myConsumerRecord.value()));
                        orderInfoDocument.insertOne(orderInfo.getOrderInfoAsDocument());
                        //Bson filter = Filters.eq("_id", orderInfo.getOrderNumber());
                        //Bson update = new Document("$set",
                                //orderInfo.getOrderInfoAsDocument());
                        //orderInfoDocument.updateOne(filter,
                          //      update,updateOptions.upsert(true));
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                myconsumer.close();
            }

        }
}
