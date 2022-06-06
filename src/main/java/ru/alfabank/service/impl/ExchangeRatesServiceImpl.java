package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.client.ExchangeRatesFeignClient;
import ru.alfabank.dto.RatesDto;
import ru.alfabank.exceptions.ExchangeRateServiceException;
import ru.alfabank.service.ExchangeRatesService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private static final String TAKE_ALL_CURRENCIES_ERROR = "Произошла ошибка при получении списка валют";
    private static final String TAKE_RATE_ERROR = "Произошла ошибка при получении курса валюты";
    private static final String TAKE_YESTERDAY_RATE_ERROR = "Произошла ошибка при получении вчерашнего курса валюты";

    private final ExchangeRatesFeignClient feignClient;

    @Override
    public List<String> takeAllCurrencies() {
        try {
            return feignClient
                    .takeAllCurrencies()
                    .keySet()
                    .stream()
                    .toList();
        } catch (ExchangeRateServiceException e) {
            throw new ExchangeRateServiceException(TAKE_ALL_CURRENCIES_ERROR);
        }
    }

    @Override
    public RatesDto takeRateByCode(String code) {
        try {
            return RatesDto.builder()
                    .rate(feignClient.takeRateByCode(code)
                            .getRates()
                            .get(code))
                    .build();
        } catch (ExchangeRateServiceException e) {
            throw new ExchangeRateServiceException(TAKE_RATE_ERROR);
        }
    }

    @Override
    public RatesDto takeYesterdayRateByCode(String code) {
        try {
            var dateOfYesterday = (LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"))
                    .minusDays(1))
                    .format(DateTimeFormatter.ISO_DATE);
            return RatesDto.builder()
                    .rate(feignClient.takeRateByDateAndCode(dateOfYesterday, code)
                            .getRates()
                            .get(code))
                    .build();
        } catch (ExchangeRateServiceException e) {
            throw new ExchangeRateServiceException(TAKE_YESTERDAY_RATE_ERROR);
        }
    }
}