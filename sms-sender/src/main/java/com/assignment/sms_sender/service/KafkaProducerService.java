package com.assignment.sms_sender.service;

import com.assignment.sms_sender.model.SmsEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, SmsEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, SmsEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSmsEvent(SmsEvent event) {

        kafkaTemplate.send("sms-events", event);

        System.out.println("Published SMS Event to Kafka");
    }
}