// Package declaration specifying that this class belongs to the 'com.gcu' package
package com.gcu;

// Importing necessary classes for logging and Spring Boot application setup
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The @SpringBootApplication annotation marks this class as a Spring Boot application entry point.
// It combines @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations.
// This annotation enables Spring Boot's auto-configuration feature and component scanning.
@SpringBootApplication
public class ContactlistApplication {

    // Logger instance to log messages, typically used for tracking the application's behavior
    private static final Logger logger = LoggerFactory.getLogger(ContactlistApplication.class);

    // The main method is the entry point to the Spring Boot application.
    public static void main(String[] args) {
        
        // Logging an informational message to indicate the start of the main method
        logger.info("Entering method main");

        // Launches the Spring Boot application by calling SpringApplication.run().
        // This method initializes the application context and starts the embedded web server (e.g., Tomcat).
        SpringApplication.run(ContactlistApplication.class, args);

        // Logging an informational message to indicate that the main method has completed
        logger.info("Exiting method main");
    }
}
