package ru.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.model.ExchangeRate;

import java.util.Map;

@FeignClient(name = "exchangeRates", url = "${exchange.api.urls.base}?app_id=${exchange.api.key}")
public interface ExchangeRatesFeignClient {

    @GetMapping("${exchange.api.urls.currencies}")
    Map<String, String> takeAllCurrencies();

    @GetMapping("${exchange.api.urls.latest}")
    ExchangeRate takeRateByCode(@RequestParam String symbols);

    @GetMapping("${exchange.api.urls.historical}/{date}.json")
    ExchangeRate takeRateByDateAndCode(@PathVariable String date, @RequestParam String symbols);

}