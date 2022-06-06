package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final String TAKE_ALL_CURRENCIES_LOG = "Получен список курсов валют в количестве: {}";
    private static final String TAKE_RATE_LOG = "Запрошен курс валюты, код: {}";
    private static final String TAKE_YESTERDAY_RATE_LOG = "Запрошен вчерашний курс валюты, код: {}";

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRatesService.class);

    private final ExchangeRatesFeignClient feignClient;

    @Override
    public List<String> takeAllCurrencies() {
        try {
            var currenciesList = feignClient
                    .takeAllCurrencies()
                    .keySet()
                    .stream()
                    .toList();
            LOGGER.info(TAKE_ALL_CURRENCIES_LOG, currenciesList.size());
            return currenciesList;
        } catch (ExchangeRateServiceException e) {
            throw new ExchangeRateServiceException(TAKE_ALL_CURRENCIES_ERROR);
        }
    }

    @Override
    public RatesDto takeRateByCode(String code) {
        try {
            var rate = feignClient.takeRateByCode(code).getRates().get(code);
            LOGGER.info(TAKE_RATE_LOG, code);
            return RatesDto.builder()
                    .rate(rate)
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
            var rate = feignClient.takeRateByDateAndCode(dateOfYesterday, code).getRates().get(code);
            LOGGER.info(TAKE_YESTERDAY_RATE_LOG, code);
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