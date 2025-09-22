package com.applab.library_notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${notification.email.from}")
    private String fromEmail;

    @Value("${notification.email.to.librarian}")
    private String librarianEmail;

    @Value("${notification.email.to.user}")
    private String userEmail;

    @Value("${notification.email.subjects.book-added}")
    private String bookAddedSubject;

    @Value("${notification.email.subjects.book-borrowed}")
    private String bookBorrowedSubject;

    @Value("${notification.email.subjects.book-returned}")
    private String bookReturnedSubject;

    public void sendBookBorrowedNotification(String bookDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userEmail);
            message.setSubject(bookBorrowedSubject);
            message.setText("Hello!\n\nA book has been borrowed from the library:\n\n" + bookDetails + 
                          "\n\nPlease update your records accordingly.\n\nBest regards,\nLibrary Notification System");
            message.setFrom(fromEmail);
            
            mailSender.send(message);
            logger.info("✅ Book Borrowed email notification sent successfully for: {}", bookDetails);
            
        } catch (Exception e) {
            logger.error("❌ Failed to send book borrowed email notification: {}", e.getMessage());
        }
    }

    public void sendBookReturnedNotification(String bookDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(librarianEmail);
            message.setSubject(bookReturnedSubject);
            message.setText("Hello!\n\nA book has been returned to the library:\n\n" + bookDetails + 
                          "\n\nThe book is now available for other borrowers.\n\nBest regards,\nLibrary Notification System");
            message.setFrom(fromEmail);
            
            mailSender.send(message);
            logger.info("✅ Book Returned email notification sent successfully for: {}", bookDetails);
            
        } catch (Exception e) {
            logger.error("❌ Failed to send book returned email notification: {}", e.getMessage());
        }
    }
}
