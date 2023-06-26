package com.career_link.kenya.utils;

public class ApplicationConstants {

    /*KAFKA CONSTANTS */
    public static final int PARTITIONS = 3;
    public static final short REPLICATION_FACTOR = 1;
    public static final String TEXT_MESSAGES_TOPIC = "TEXT_MESSAGES_TOPIC";
    public static final String TEXT_MESSAGES_GROUP_ID = "PROGRESS_GROUP_ID";
    public static final String BOOTSTRAP_SERVERS = "localhost:29092";

    /*JWT AUTH CONSTANTS*/
    public static final String SECRET_KEY = "556B58703272357538782F413F4428472B4B6250655368566D59713374367639";
    public static final long JWT_EXPIRATION_TIME = 60 * 1000;




}
