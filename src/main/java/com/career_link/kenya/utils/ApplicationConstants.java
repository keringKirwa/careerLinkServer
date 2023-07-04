package com.career_link.kenya.utils;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApplicationConstants {

    /*KAFKA CONSTANTS */
    public static final int PARTITIONS = 3;
    public static final short REPLICATION_FACTOR = 1;
    public static final String TEXT_MESSAGES_TOPIC = "TEXT_MESSAGES_TOPIC";
    public static final String TEXT_MESSAGES_GROUP_ID = "PROGRESS_GROUP_ID";
    public static final String BOOTSTRAP_SERVERS = "localhost:29092";

    /*JWT AUTH CONSTANTS*/
    public static final String SECRET_KEY = "556B58703272357538782F413F4428472B4B6250655368566D59713374367639";
    public static final String ROOT_PATH = "/api/v1";
    public static final long JWT_EXPIRATION_TIME = 1000 * 1000;

    public static final PasswordEncoder passwordEncoder =   new BCryptPasswordEncoder();

    private static final Logger logger = Logger.getLogger(ApplicationConstants.class.getName());


    public static Map<String, Object> getKafkaProps(){

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", ApplicationConstants.TEXT_MESSAGES_GROUP_ID);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        return kafkaParams;
    }




    public static void LOG(String message, LoggingTypes loggingTypes ){

        if (loggingTypes == LoggingTypes.INFO){
            logger.info("INFO MESSAGE **** ::: **** " + message);

        }
        else if (loggingTypes == LoggingTypes.ERROR){
            logger.warning("APPLICATION ERROR  **** ::: **** : " + message);

        }


    }






}
