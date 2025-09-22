package com.applab.library_notification_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.applab.library_notification_service.service.EmailNotificationService;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private EmailNotificationService emailNotificationService;
    
    @KafkaListener(topics = "book-borrowed", groupId = "notification-service-group")
    public void handleBookBorrowed(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("=== NOTIFICATION SERVICE RECEIVED BOOK BORROWED ===");
        logger.info("Topic: {}", topic);
        logger.info("Partition: {}", partition);
        logger.info("Offset: {}", offset);
        logger.info("Message: {}", message);
        
        processBookBorrowedNotification(message);
    }

    @KafkaListener(topics = "book-returned", groupId = "notification-service-group")
    public void handleBookReturned(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("=== NOTIFICATION SERVICE RECEIVED BOOK RETURNED ===");
        logger.info("Topic: {}", topic);
        logger.info("Partition: {}", partition);
        logger.info("Offset: {}", offset);
        logger.info("Message: {}", message);
        
        processBookReturnedNotification(message);
    }

    private void processBookBorrowedNotification(String message) {
        emailNotificationService.sendBookBorrowedNotification(message);
        logger.info("📧 Email notification sent for: {}", message);
    }

    private void processBookReturnedNotification(String message) {
        emailNotificationService.sendBookReturnedNotification(message);
        logger.info("📧 Email notification sent for: {}", message);
    }
}