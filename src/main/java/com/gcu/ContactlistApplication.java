package com.gcu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactlistApplication {
    private static final Logger logger = LoggerFactory.getLogger(ContactlistApplication.class);

	public static void main(String[] args) {
        logger.info("Entering method main");
		SpringApplication.run(ContactlistApplication.class, args);
        logger.info("Exiting method main");
	}

}
