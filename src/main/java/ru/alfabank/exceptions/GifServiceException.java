package ru.alfabank.exceptions;

public class GifServiceException extends RuntimeException {
    public GifServiceException(String message) {
        super(message);
    }
}
