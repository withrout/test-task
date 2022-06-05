package ru.alfabank.service;

import ru.alfabank.dto.RatesDto;
import ru.alfabank.model.ExchangeRate;

import java.util.List;

/**
 * Сервис работы с валютами
 */
public interface ExchangeRatesService {
    List<String> takeAllCurrencies();
    RatesDto takeRateByCode(String code);
    RatesDto takeYesterdayRateByCode(String code);
}