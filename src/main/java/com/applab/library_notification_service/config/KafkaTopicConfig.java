package com.applab.library_notification_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic bookBorrowedTopic() {
        return TopicBuilder.name("book-borrowed")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic bookReturnedTopic() {
        return TopicBuilder.name("book-returned")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
