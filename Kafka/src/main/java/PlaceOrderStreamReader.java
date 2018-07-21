import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.kstream.ValueMapper;
import java.util.Properties;
import com.google.gson.Gson;

import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class PlaceOrderStreamReader {
    public static void main(String[] args) {
        Properties placeStreamProperties = new Properties();
        placeStreamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        placeStreamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "placestream2");
        placeStreamProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        placeStreamProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        KStreamBuilder placeOrderStreamBuilder = new KStreamBuilder();
        final Serde<String> placeMsgSerde = Serdes.String();

        KStream<String, String> placeOrderStream = placeOrderStreamBuilder
                .stream(placeMsgSerde,placeMsgSerde, "placeorder");
        KStream<String, String> orderInfoStream = placeOrderStream.mapValues(new ValueMapper<String, String>() {
            @Override
            public String apply(String placeMsg) {
                String orderInfo = "";
                Gson gson = new Gson();
                PlaceOrder placeOrder = gson.fromJson(placeMsg, PlaceOrder.class);
                orderInfo = "{ \"orderNumber\":" + placeOrder.getCustomerOrder().getCustOrder()
                        + ", \"orderType\": \"" + placeOrder.getCustomerOrder().getOrderType()
                        + "\", \"store\":" + placeOrder.getCustomerOrder().getFulfillmentOrder().getNode().getNodeID()
                        + ", \"orderTotal\":" + placeOrder.getCustomerOrder().getOrderTotal()
                        + " }";
                System.out.println(orderInfo);
                return orderInfo;
            }
        });
        orderInfoStream.print();
        orderInfoStream.to("orderinfo");

        KStream<String, String> statusInfoStream = placeOrderStream.mapValues(new ValueMapper<String, String>() {
            @Override
            public String apply(String placeMsg) {
                String statusInfo = "";
                Gson gson = new Gson();
                PlaceOrder placeOrder = gson.fromJson(placeMsg, PlaceOrder.class);
                statusInfo = "{ \"orderNumber\":" + placeOrder.getCustomerOrder().getCustOrder()
                        +", \"status\":\"Placed\""
                        +", \"store\":" + placeOrder.getCustomerOrder().getFulfillmentOrder().getNode().getNodeID()
                        +", \"currentStatusTs\": \"" + placeOrder.getCustomerOrder().getOrderPlacedTs()
                        + "\" }";
                System.out.println(statusInfo);
                return statusInfo;
            }
        });
        statusInfoStream.print();
        statusInfoStream.to("statusinfo");

        KStream<String, String> itemInfoStream = placeOrderStream.flatMapValues( value ->
        {
            JSONObject data;
            String Message = "";
            try {
                JSONParser parser = new JSONParser();
                data = (JSONObject) parser.parse(value.toString());
                System.out.println("data " + data);
                JSONObject customerorder = (JSONObject) data.get("customerorder");
                JSONObject fulfillmentorder = (JSONObject) customerorder.get("fulfillmentorder");
                    JSONArray orderline = (JSONArray) fulfillmentorder.get("orderline");
                    for (int j = 0; j < orderline.size(); j++) {
                        JSONObject olobj = (JSONObject) orderline.get(j);
                        Message =  olobj.toString()+ ";" + Message;
                    }
        } catch (ParseException ex) {
                System.out.println(ex);
            }
            return Arrays.asList(Message);
        }).flatMapValues(value -> Arrays.asList(value.split(";")));

        itemInfoStream.print();
        itemInfoStream.to("iteminfo");

        KafkaStreams placeStream =new KafkaStreams(placeOrderStreamBuilder, placeStreamProperties);
        placeStream.start();
    }
}
