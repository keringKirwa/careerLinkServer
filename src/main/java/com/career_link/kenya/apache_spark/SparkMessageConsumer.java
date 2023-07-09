package com.career_link.kenya.apache_spark;

import com.career_link.kenya.entities.Message;
import com.career_link.kenya.utils.ApplicationConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SparkMessageConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void consumeMessagesFromKafka() throws InterruptedException {

        SparkConf sparkConf = new SparkConf()
                .setAppName("SparkMessageConsumer")
                .setMaster("localhost:9090");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaStreamingContext streamingContext = new JavaStreamingContext(sparkContext, new Duration(1000));

        Set<String> topics = Collections.singleton(ApplicationConstants.TEXT_MESSAGES_TOPIC);


        JavaInputDStream<ConsumerRecord<String, String>> kafkaStream = KafkaUtils.createDirectStream(
                streamingContext,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.Subscribe(topics, ApplicationConstants.getKafkaProps())
        );

        kafkaStream.foreachRDD(rdd -> {
            rdd.foreach(record -> {
                String progressString = record.value();

                // Process the received message
                Message message = objectMapper.readValue(progressString, Message.class);
                System.out.println(">>> CONSUMER ONE: " + message);
            });
        });


        streamingContext.start();
        streamingContext.awaitTermination();
        System.out.println("THE APPLICATION STOPPED");
    }

}

