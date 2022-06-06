package ru.alfabank.service;

import ru.alfabank.dto.GifDto;

public interface GiphyService {
    GifDto takeGifByCurrencyCode(String code);
}