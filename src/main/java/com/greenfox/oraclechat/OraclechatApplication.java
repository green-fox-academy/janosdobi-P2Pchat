package com.greenfox.oraclechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OraclechatApplication {

    public static String CHAT_APP_LOGLEVEL = System.getenv("CHAT_APP_LOGLEVEL");
    public static String CHAT_APP_UNIQUE_ID = System.getenv("CHAT_APP_UNIQUE_ID");
    public static String CHAT_APP_PEER_ADDRESS = System.getenv("CHAT_APP_PEER_ADDRESS");

	public static void main(String[] args) {

        org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
        logger4j.setLevel(org.apache.log4j.Level.toLevel(CHAT_APP_LOGLEVEL));
		SpringApplication.run(OraclechatApplication.class, args);
	}
}