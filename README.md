# Polyglot SMS Service

A simple event-driven SMS processing system built using **Spring Boot**, **Apache Kafka**, **Go**, **Redis**, and **MongoDB**.

The project demonstrates a polyglot microservices architecture where a Java service produces SMS events and a Go service consumes them asynchronously.

---

# Architecture

```
                   POST /v1/sms/send
                           │
                           ▼
                  Spring Boot API
                           │
                 Validate Request
                           │
                           ▼
              Check Redis Blocklist
                    │            │
             Blocked            Allowed
                │                  │
          Return Error      Mock SMS Vendor
                                   │
                                   ▼
                          Publish to Kafka
                                   │
                                   ▼
                          Kafka Topic
                                   │
                                   ▼
                         Go Consumer Service
                                   │
                                   ▼
                          Store in MongoDB
```

---

# Tech Stack

- Java 17
- Spring Boot
- Apache Kafka
- Redis
- MongoDB
- Go
- Docker & Docker Compose
- Maven

---

# Project Structure

```
polyglotservice/
│
├── docker-compose.yml
│
├── sms-sender/          # Spring Boot Producer
│
└── sms-consumer/        # Go Kafka Consumer
```

---

# Prerequisites

## Windows

Install the following:

- Java 17
- Apache Maven
- Go (latest stable version)
- Git
- Docker Desktop

Verify installation:

```bash
java -version
mvn -version
go version
docker --version
docker compose version
git --version
```

---

## macOS

Using Homebrew:

```bash
brew install openjdk@17
brew install maven
brew install go
brew install git
brew install --cask docker
```

Verify:

```bash
java -version
mvn -version
go version
docker --version
docker compose version
```

---

# Infrastructure Setup

From the project root:

```bash
docker compose up -d
```

Verify containers:

```bash
docker ps
```

Expected containers:

- Kafka
- Zookeeper
- Redis
- MongoDB

---

# Running the Spring Boot Service

Navigate to:

```bash
cd sms-sender
```

Run:

Windows

```bash
mvn spring-boot:run
```

or

```bash
.\mvnw.cmd spring-boot:run
```

macOS

```bash
./mvnw spring-boot:run
```

The service starts on:

```
http://localhost:8080
```

---

# Running the Go Consumer

Navigate to:

```bash
cd sms-consumer
```

Run:

```bash
go run .
```

Expected output:

```
Starting SMS Consumer...
Connected to MongoDB
Kafka Consumer Started...
```

---

# API

## Send SMS

**POST**

```
http://localhost:8080/v1/sms/send
```

### Request

```json
{
  "userId": "user123",
  "phoneNumber": "9876543210",
  "message": "Hello from Polyglot Service!"
}
```

### Success Response

```json
{
  "status": "SUCCESS",
  "message": "SMS sent successfully."
}
```

If the phone number exists in the Redis blocklist, the request is rejected.

---

# Kafka

Topic used:

```
sms-events
```

The Spring Boot application publishes SMS events to Kafka.

The Go service continuously listens to this topic and processes incoming messages.

---

# MongoDB

Database:

```
smsdb
```

Collection:

```
messages
```

Verify stored messages:

```bash
docker exec -it polygotservice-mongodb-1 mongosh
```

Inside MongoDB:

```javascript
use smsdb

db.messages.find().pretty()
```

---

# Redis

Redis is used to maintain a blocklist of phone numbers.

Before publishing an SMS event to Kafka, the Spring Boot service checks whether the recipient number is blocked.

---

# End-to-End Flow

1. Client sends a POST request.
2. Spring Boot validates the request.
3. Redis blocklist is checked.
4. Mock SMS vendor simulates SMS delivery.
5. Event is published to Kafka.
6. Go consumer receives the event.
7. Event is stored in MongoDB.

---

# Useful Docker Commands

Start containers:

```bash
docker compose up -d
```

Stop containers:

```bash
docker compose down
```

Restart:

```bash
docker compose restart
```

View running containers:

```bash
docker ps
```

View logs:

```bash
docker logs <container-name>
```

---