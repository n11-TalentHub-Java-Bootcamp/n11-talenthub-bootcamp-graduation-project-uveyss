package com.lns.n11loanapplication.api.errorHandling.handler;

import com.lns.n11loanapplication.api.errorHandling.configuration.ErrorDto;
import com.lns.n11loanapplication.api.errorHandling.configuration.ErrorMessageLocator;
import com.lns.n11loanapplication.api.errorHandling.exception.BusinessException;
import com.lns.n11loanapplication.api.errorHandling.response.ApiError;
import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessExceptionHandler {

    private final ErrorMessageLocator errorMessageLocator;

    @ExceptionHandler(BusinessException.class)
    public ApiResponse handleBusinessException(BusinessException exception) {

        ErrorDto error = errorMessageLocator.error(exception.getErrorKey(),exception.getArgs());
        ApiError apiError = ApiError.builder().code(error.getCode()).message(error.getMessage()).build();
        return ApiResponse.error(apiError);
    }

}
