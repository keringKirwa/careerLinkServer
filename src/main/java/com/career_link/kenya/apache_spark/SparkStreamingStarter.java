package com.career_link.kenya.apache_spark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SparkStreamingStarter implements CommandLineRunner {
    @Autowired
    private SparkMessageConsumer sparkMessageConsumer;

    @Override
    public void run(String... args) throws Exception {

        sparkMessageConsumer.consumeMessagesFromKafka();
    }
}
