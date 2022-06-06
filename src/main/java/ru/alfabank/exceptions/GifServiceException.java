package ru.alfabank.exceptions;

/**
 * Исключения при работе с GIF
 */
public class GifServiceException extends RuntimeException {
    public GifServiceException(String message) {
        super(message);
    }
}
