package ru.alfabank.service;

import ru.alfabank.dto.GifDto;

/**
 * Сервис получения GIF
 */
public interface GiphyService {

    /**
     * Поулчить GIF согласно результатов проверки курса валюты
     * @param code код валюты
     * @return GIF DTO
     */
    GifDto takeGifByCurrencyCode(String code);
}