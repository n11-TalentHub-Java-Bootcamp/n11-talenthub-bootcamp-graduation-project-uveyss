package com.lns.n11loanapplication.engine.notificationEngine;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

public class SmsEngine {
    @NotBlank
    private final String phoneNumber; // destination
    @NotBlank
    private final String message;
    public SmsEngine(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getMessage() {
        return message;
    }
    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber= ..." + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
