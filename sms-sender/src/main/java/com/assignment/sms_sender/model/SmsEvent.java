package com.assignment.sms_sender.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsEvent {

    private String userId;
    private String phoneNumber;
    private String message;
}