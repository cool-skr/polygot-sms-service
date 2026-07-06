package com.assignment.sms_sender.controller;

import com.assignment.sms_sender.dto.SmsRequest;
import com.assignment.sms_sender.dto.SmsResponse;
import com.assignment.sms_sender.service.SmsService;
import com.assignment.sms_sender.service.RedisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/v1/sms")
public class SmsController {

    private final SmsService smsService;
    private final RedisService redisService;

    public SmsController(SmsService smsService, RedisService redisService) {
        this.smsService = smsService;
        this.redisService = redisService;
    }

    @PostMapping("/send")
    public SmsResponse sendSms(@Valid @RequestBody SmsRequest request) {
        return smsService.sendSms(request);
    }
    @PostMapping("/block/{phoneNumber}")
    public String blockNumber(@PathVariable String phoneNumber) {

        redisService.blockNumber(phoneNumber);

        return "Number blocked successfully";
    }
}