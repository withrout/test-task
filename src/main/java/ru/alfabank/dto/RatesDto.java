package ru.alfabank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RatesDto {
    private Double rate;
}
