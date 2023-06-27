package com.career_link.kenya.producers;


import com.career_link.kenya.entities.Message;
import com.career_link.kenya.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer  {


    /**
     * the following line reads a kafka template  availed to the app in its context(in our case , it will read from
     * our custom KafkaTemplate.)
     */

    @Autowired
    private KafkaTemplate<String, Message> messageKafkaTemplate;


    public void publishTextMessage(Message message) {

        messageKafkaTemplate.send(ApplicationConstants.TEXT_MESSAGES_TOPIC,message.getUserId(), message);
    }
}
