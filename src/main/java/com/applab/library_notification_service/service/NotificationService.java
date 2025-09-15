package com.applab.library_notification_service.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    public void handleBookBorrowed(String message) {
        // Business logic for borrowed book event
    }

    public void handleBookReturned(String message) {
        // Business logic for returned book event
    }
}
