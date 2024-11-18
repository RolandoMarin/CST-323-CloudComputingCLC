package com.gcu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        logger.info("Entering method healthCheck");
        ResponseEntity<String> response = ResponseEntity.ok("Application is running!");
        logger.info("Exiting method healthCheck");
        return response;
    }
}
