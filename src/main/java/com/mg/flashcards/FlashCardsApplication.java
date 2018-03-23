package com.mg.flashcards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlashCardsApplication {

	static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(FlashCardsApplication.class, args);
		logger.info("Helllo---------------------------");
	}

}
