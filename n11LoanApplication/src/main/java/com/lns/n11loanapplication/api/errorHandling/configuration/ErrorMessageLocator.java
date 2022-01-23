package com.lns.n11loanapplication.api.errorHandling.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class ErrorMessageLocator {

    private final ResourceBundleMessageSource messageSource;

    public ErrorDto error(String key, Object... args) {
        return ErrorDto.builder()
                .code(messageSource.getMessage(key + ".code", args, Locale.ENGLISH))
                .message(messageSource.getMessage(key + ".message", args, Locale.ENGLISH))
                .build();
    }
}
