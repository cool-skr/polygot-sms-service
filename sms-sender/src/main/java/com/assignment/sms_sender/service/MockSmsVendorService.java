package com.assignment.sms_sender.service;

import com.assignment.sms_sender.dto.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public class MockSmsVendorService {

    public boolean sendSms(SmsRequest request) {

        System.out.println("--------------------------------");
        System.out.println("Mock SMS Vendor");
        System.out.println("Sending SMS...");
        System.out.println("Phone : " + request.getPhoneNumber());
        System.out.println("Message : " + request.getMessage());
        System.out.println("--------------------------------");

        return true;
    }
}