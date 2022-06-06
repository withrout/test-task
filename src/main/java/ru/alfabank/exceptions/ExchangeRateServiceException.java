package ru.alfabank.exceptions;

public class ExchangeRateServiceException extends RuntimeException {
    public ExchangeRateServiceException(String message) {
        super(message);
    }
}
