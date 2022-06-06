package ru.alfabank.service;

import ru.alfabank.dto.RatesDto;

import java.util.List;
import java.util.Map;

/**
 * Сервис работы с API валют
 */
public interface ExchangeRatesService {

    /**
     * Получить все доступные валють
     * @return словарь валют кодов
     */
    Map<String, String> takeAllCurrencies();

    /**
     * Поулчить курс валюты по коду
     * @param code код валюты
     * @return DTO курса валюты
     */
    RatesDto takeRateByCode(String code);

    /**
     * Поулчить вчерашний курс валюты по коду
     * @param code code код валюты
     * @return DTO курса валюты
     */
    RatesDto takeYesterdayRateByCode(String code);
}