package com.assignment.sms_sender.service;

import com.assignment.sms_sender.dto.SmsRequest;
import com.assignment.sms_sender.dto.SmsResponse;
import com.assignment.sms_sender.model.SmsEvent;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final MockSmsVendorService mockSmsVendorService;
    private final RedisService redisService;
    private final KafkaProducerService kafkaProducerService;

    public SmsService(MockSmsVendorService mockSmsVendorService,
                    RedisService redisService,
                    KafkaProducerService kafkaProducerService) {

        this.mockSmsVendorService = mockSmsVendorService;
        this.redisService = redisService;
        this.kafkaProducerService = kafkaProducerService;
    }


    public SmsResponse sendSms(SmsRequest request) {

        boolean sent = mockSmsVendorService.sendSms(request);

        if (sent) {
            if (redisService.isBlocked(request.getPhoneNumber())) {

                return new SmsResponse(
                        "FAILED",
                        "Phone number is blocked."
                );
            }
            SmsEvent event = new SmsEvent(
                    request.getUserId(),
                    request.getPhoneNumber(),
                    request.getMessage()
            );

            kafkaProducerService.publishSmsEvent(event);
            return new SmsResponse(
                    "SUCCESS",
                    "SMS sent successfully."
            );
        }

        return new SmsResponse(
                "FAILED",
                "SMS sending failed."
        );
    }
}