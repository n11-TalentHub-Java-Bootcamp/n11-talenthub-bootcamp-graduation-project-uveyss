package com.lns.n11loanapplication.api.errorHandling.handler;



import com.lns.n11loanapplication.api.errorHandling.response.ApiError;
import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UnhandledExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleUnhandledExceptionHandle(Exception exception) {
        log.error("UnhandledException: {} ", exception);
        ApiError apiError = ApiError.builder().message(exception.toString()).code("-1").build();
        return ApiResponse.error(apiError);
    }
}