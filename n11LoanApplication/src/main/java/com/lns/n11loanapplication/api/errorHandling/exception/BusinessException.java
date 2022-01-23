package com.lns.n11loanapplication.api.errorHandling.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String errorKey;
    private Object[] args;

    public BusinessException(String errorKey) {
        this.errorKey = errorKey;
    }

    public BusinessException(String errorKey, Object... args) {
        this.errorKey = errorKey;
        this.args = args;
    }
}
