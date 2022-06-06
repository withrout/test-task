package ru.alfabank.exceptions;

/**
 * Исключения при работе с курсами валют
 */
public class ExchangeRateServiceException extends RuntimeException {
    public ExchangeRateServiceException(String message) {
        super(message);
    }
}
