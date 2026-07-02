package com.assignment.sms_sender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsResponse {

    private String status;
    private String message;
}