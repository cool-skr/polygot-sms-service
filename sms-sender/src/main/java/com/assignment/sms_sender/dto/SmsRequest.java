package com.assignment.sms_sender.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String message;
}