package ru.alfabank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GifDto {
    private String title;
    private String url;
}
