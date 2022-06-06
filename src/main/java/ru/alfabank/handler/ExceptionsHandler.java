package ru.alfabank.handler;

import feign.FeignException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alfabank.exceptions.ExchangeRateServiceException;
import ru.alfabank.exceptions.GifServiceException;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler({ExchangeRateServiceException.class, GifServiceException.class, RuntimeException.class, FeignException.class})
    public String error() {
        return "error";
    }
}