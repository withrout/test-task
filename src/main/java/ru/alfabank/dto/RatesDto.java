package ru.alfabank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO для отображения курса валют
 */
@Setter
@Getter
@Builder
public class RatesDto {
    private Double rate;
}
