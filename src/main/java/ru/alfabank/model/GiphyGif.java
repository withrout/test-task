package ru.alfabank.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.alfabank.deserialaizer.GiphyGifDeserializer;

@Setter
@Getter
@AllArgsConstructor
@JsonDeserialize(using = GiphyGifDeserializer.class)
public class GiphyGif {
    private String title;
    private String url;
}