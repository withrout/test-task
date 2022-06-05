package ru.alfabank.service;

import ru.alfabank.model.ExchangeRate;

import java.util.List;

/**
 * Сервис работы с валютами
 */
public interface ExchangeRatesService {
    List<String> takeAllCurrencies();
    ExchangeRate takeRateByCode(String code);
    ExchangeRate takeYesterdayRateByCode(String code);
}