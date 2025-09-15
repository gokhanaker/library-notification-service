package com.applab.library_notification_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "book-added", groupId = "notification-service-group")
    public void handleBookAdded(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("=== NOTIFICATION SERVICE RECEIVED BOOK ADDED ===");
        logger.info("Topic: {}", topic);
        logger.info("Partition: {}", partition);
        logger.info("Offset: {}", offset);
        logger.info("Message: {}", message);
        
        // Parse the message and extract book information
        // Example: "Book added: Spring Boot in Action (ISBN: 9781617292545)"
        
        // Your business logic here:
        // - Send email notifications
        // - Update search indexes
        // - Send push notifications
        // - Update recommendation systems
        // etc.
        
        processBookAddedNotification(message);
        
        logger.info("=== NOTIFICATION PROCESSING COMPLETE ===");
    }
    
    private void processBookAddedNotification(String message) {
        // Extract book details from message
        // Implement your notification logic
        logger.info("Processing notification for: {}", message);
        
        // Example: Send email, update cache, etc.
    }
}