package com.lns.n11loanapplication.api.errorHandling.exception;

import com.lns.n11loanapplication.api.errorHandling.response.ApiError;
import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private String message;
    private Object [] objects;



    public NotFoundException(String message, Object... args) {
        this.message=message;
        this.objects=args;
        handleUnhandledExceptionHandle();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleUnhandledExceptionHandle()  throws NotFoundException{
        log.error("NOTFoundException: {} ", message);
        ApiError apiError = ApiError.builder().message(message).code("404").build();
        return ApiResponse.error(apiError);
    }
}
