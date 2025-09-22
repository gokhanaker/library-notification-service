# Library Notification Service

A Spring Boot microservice that consumes library events from Apache Kafka and sends email notifications to library staff and users.

## 📋 Overview

This service acts as a notification hub for library events. It listens to Kafka topics published by the `library-management-service` and sends appropriate email notifications based on the event type.

## 🏗️ Architecture

```
┌─────────────────────────┐    Kafka Topics    ┌──────────────────────────┐
│                         │   book-borrowed    │                          │
│  Library Management     │   book-returned    │  Library Notification    │
│  Service                │ ──────────────────►│  Service                 │
│  (Producer)             │                    │  (Consumer)              │
└─────────────────────────┘                    └──────────────────────────┘
                                                           │
                                                           │ Email
                                                           │ Notifications
                                                           ▼
                                               ┌──────────────────────────┐
                                               │  📧 Email Recipients:    │
                                               │  • Librarians           │
                                               │  • Users                 │
                                               └──────────────────────────┘
```

## 🚀 Features

- **Kafka Consumer**: Listens to library events from multiple topics
- **Email Notifications**: Sends contextual email notifications based on event type

## 📨 Supported Events

| Event Type        | Topic           | Recipient | Description                                |
| ----------------- | --------------- | --------- | ------------------------------------------ |
| **Book Borrowed** | `book-borrowed` | User      | Notifies user when a book is borrowed      |
| **Book Returned** | `book-returned` | Librarian | Notifies librarian when a book is returned |

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Kafka** - For consuming Kafka messages
- **Spring Mail** - For sending email notifications
- **Apache Kafka** - Message broker
- **Maven** - Build tool

### Email Setup (Gmail)

For Gmail integration:

1. Enable 2-Factor Authentication on your Gmail account
2. Generate an App Password:
   - Go to Google Account Settings
   - Security → 2-Step Verification → App Passwords
   - Generate password for "Mail"
3. Update `application.yml` with your Gmail credentials

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/gokhanaker/library-notification-service
cd library-notification-service
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Start Kafka (if not already running)

```bash
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka
bin/kafka-server-start.sh config/server.properties
```

### 4. Start the Application

```bash
mvn spring-boot:run
```

The service will start on port `8091` by default.
