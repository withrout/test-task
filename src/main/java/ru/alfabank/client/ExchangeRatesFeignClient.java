package ru.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.model.ExchangeRate;

import java.util.Map;

/**
 * Клиент для работы с API курсов валют
 */
@FeignClient(name = "exchangeRates", url = "${exchange.api.urls.base}?app_id=${exchange.api.key}")
public interface ExchangeRatesFeignClient {

    /**
     * Получения списка доступных валют
     * @return map валют
     */
    @GetMapping("${exchange.api.urls.currencies}")
    Map<String, String> takeAllCurrencies();

    /**
     * Получение курса валюты по ее коду
     * @param symbols код валюты
     * @return модель курса валют
     */
    @GetMapping("${exchange.api.urls.latest}")
    ExchangeRate takeRateByCode(@RequestParam String symbols);

    /**
     * Получить курс валюты на дату
     * @param date дата для проверки курса
     * @param symbols код валюты
     * @return модель курса валют
     */
    @GetMapping("${exchange.api.urls.historical}/{date}.json")
    ExchangeRate takeRateByDateAndCode(@PathVariable String date, @RequestParam String symbols);

}