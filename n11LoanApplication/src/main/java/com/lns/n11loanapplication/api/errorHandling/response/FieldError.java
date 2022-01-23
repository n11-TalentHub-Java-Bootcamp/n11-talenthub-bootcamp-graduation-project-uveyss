package com.lns.n11loanapplication.api.errorHandling.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FieldError {
    private String field;
    private Object rejectedValue;
    private String message;
}