package com.greenfox.oraclechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OraclechatApplication {

    public static String CHAT_APP_LOGLEVEL = System.getenv("CHAT_APP_LOGLEVEL");
    public static String CHAT_APP_UNIQUE_ID = System.getenv("CHAT_APP_UNIQUE_ID");
    public static String CHAT_APP_PEER_ADDRESS = System.getenv("CHAT_APP_PEER_ADDRESS");

	public static void main(String[] args) {
		SpringApplication.run(OraclechatApplication.class, args);
	}
}