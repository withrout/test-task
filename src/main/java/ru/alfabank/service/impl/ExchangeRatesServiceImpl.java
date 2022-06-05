package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alfabank.client.ExchangeRatesFeignClient;
import ru.alfabank.dto.RatesDto;
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
    public RatesDto takeRateByCode(String code) {
        return RatesDto.builder()
                .rate(feignClient.takeRateByCode(code)
                        .getRates()
                        .get(code))
                .build();
    }

    @Override
    public RatesDto takeYesterdayRateByCode(String code) {
        var dateOfYesterday = (LocalDate.ofInstant(Instant.now(), ZoneId.of("UTC"))
                .minusDays(1))
                .format(DateTimeFormatter.ISO_DATE);
        return RatesDto.builder()
                .rate(feignClient.takeRateByDateAndCode(dateOfYesterday, code)
                        .getRates()
                        .get(code))
                .build();
    }
}