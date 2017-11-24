import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class PowerData_Producer {

    public static void main(String[] args) throws JSONException, IOException{

        Properties props = new Properties();
        props.put("bootstrap.servers", "140.128.98.31:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("準備傳送");



        //先抓網頁API的JSON

        URLConnection connection = new URL("http://140.128.197.129:8080/rest/buildingMeter/powerUsage/").openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());

        String PowerData = scanner.useDelimiter("\\A").next();
        System.out.println(PowerData);


        //在測試抓JSON的資料


        JSONArray k;
        JSONObject i;

        k = new JSONArray(PowerData);

        System.out.println("傳送開始");

        for (int p=0; p<k.length(); p++){

            i = k.getJSONObject(p);

            //轉13
            long unixSeconds = Long.parseLong(k.getJSONObject(p).getString("time_stamp"));
            Date date = new Date(unixSeconds); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT+8")); // give a timezone reference for formating (see comment at the bottom
            String formattedDate = sdf.format(date);


            System.out.print(i.getString("location") + ",");
            System.out.println(formattedDate + "," + i.getString("KW"));


            producer.send(new ProducerRecord<String, String>("powerdata_minute", i.getString("location"), formattedDate + "," + i.getString("location") + "," + i.getString("KW")));

            System.out.println(new ProducerRecord<String, String>("powerdata_minute", i.getString("location"), formattedDate + "," + i.getString("location") + "," + i.getString("KW")));



        }

        System.out.println("傳送結束");




        producer.close();
        System.out.println("Message sent successfully");





    }
}
