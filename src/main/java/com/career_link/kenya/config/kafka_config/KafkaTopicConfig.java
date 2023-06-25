package com.career_link.kenya.config.kafka_config;


import com.career_link.kenya.lib.ApplicationConstants;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstants.BOOTSTRAP_SERVERS);
        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic messagesTopic() {
        return TopicBuilder.name(ApplicationConstants.TEXT_MESSAGES_TOPIC)
                .partitions(ApplicationConstants.PARTITIONS)
                .replicas(ApplicationConstants.REPLICATION_FACTOR)
                .compact()
                .build();
    }

}

