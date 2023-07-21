package com.career_link.kenya.consumers;

import com.career_link.kenya.entities.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.career_link.kenya.utils.ApplicationConstants.TEXT_MESSAGES_GROUP_ID;
import static com.career_link.kenya.utils.ApplicationConstants.TEXT_MESSAGES_TOPIC;

@Service
public class MessageConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(topics = TEXT_MESSAGES_TOPIC, groupId = TEXT_MESSAGES_GROUP_ID)
    public void onMessageArrival(String progressString) throws JsonProcessingException {

        Message message = objectMapper.readValue(progressString, Message.class);

        System.out.println(">>> CONSUMER ONE" + message);

    }
}