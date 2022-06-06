package ru.alfabank.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.alfabank.deserialaizer.GiphyGifDeserializer;

import java.util.Map;

/**
 * Модель GIF
 */
@Setter
@Getter
@AllArgsConstructor
@JsonDeserialize(using = GiphyGifDeserializer.class)
public class GiphyGif {
    private String title;
    private Map<Integer, String> urls;
}