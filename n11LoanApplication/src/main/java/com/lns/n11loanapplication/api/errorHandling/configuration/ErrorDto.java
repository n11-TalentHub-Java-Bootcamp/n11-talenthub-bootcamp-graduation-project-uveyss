package com.lns.n11loanapplication.api.errorHandling.configuration;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    private String code;
    private String message;
}