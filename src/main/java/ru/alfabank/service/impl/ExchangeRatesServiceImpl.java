package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.client.ExchangeRatesFeignClient;
import ru.alfabank.model.ExchangeRate;
import ru.alfabank.service.ExchangeRatesService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final ExchangeRatesFeignClient feignClient;

    @Override
    public List<String> takeAllCurrencies() {
        return feignClient
                .takeAllCurrencies()
                .keySet()
                .stream()
                .toList();
    }

    @Override
    public ExchangeRate takeRateByCode(String code) {
        return feignClient.takeRateByCode(code);
    }

    @Override
    public ExchangeRate takeYesterdayRateByCode(String code) {
        var dateOfYesterday = (LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"))
                .minusDays(1))
                .format(DateTimeFormatter.ISO_DATE);
        return feignClient.takeRateByDateAndCode(dateOfYesterday, code);
    }
}