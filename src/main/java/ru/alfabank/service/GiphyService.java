package ru.alfabank.service;

import ru.alfabank.model.GiphyGif;

public interface GiphyService {
    GiphyGif takeGif(String code);
}