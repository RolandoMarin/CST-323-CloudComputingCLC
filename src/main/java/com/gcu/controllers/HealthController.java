package com.gcu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    // Logger instance to log important information during method execution
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    /**
     * Endpoint to check if the application is up and running.
     * @return A ResponseEntity with a "200 OK" status and a message indicating the application is running
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        // Log entry into the method for tracking purposes
        logger.info("Entering method healthCheck");
        
        // Prepare a response with status 200 (OK) and a simple message
        ResponseEntity<String> response = ResponseEntity.ok("Application is running!");
        
        // Log exit from the method after processing the health check
        logger.info("Exiting method healthCheck");
        
        // Return the ResponseEntity with the status and message
        return response;
    }
}
