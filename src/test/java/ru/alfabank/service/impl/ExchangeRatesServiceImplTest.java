package ru.alfabank.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alfabank.client.ExchangeRatesFeignClient;
import ru.alfabank.model.ExchangeRate;
import ru.alfabank.service.ExchangeRatesService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ExchangeRatesServiceImplTest {

    @MockBean
    ExchangeRatesFeignClient exchangeRatesFeignClient;

    @Autowired
    ExchangeRatesService exchangeRatesService;

    @Test
    void takeAllCurrencies() {
        var title = "TITLE";
        var code = "code";
        when(exchangeRatesFeignClient.takeAllCurrencies()).thenReturn(
                Map.of(code, title)
        );
        var result = exchangeRatesService.takeAllCurrencies();
        assertNotNull(result);
        assertEquals(title, result.get(code));
    }

    @Test
    void takeRateByCode() {
        var code = "code";
        var rateMap = Map.of(code, 1.0);
        var rate = new ExchangeRate(
                "disclaimer", "license", 123, "base", rateMap
        );
        when(exchangeRatesFeignClient.takeRateByCode(anyString())).thenReturn(rate);
        var result = exchangeRatesService.takeRateByCode(code);
        assertNotNull(result);
        assertEquals(1.0, rate.getRates().get(code));
    }

    @Test
    void takeYesterdayRateByCode() {
        var code = "code";
        var rateMap = Map.of(code, 1.0);
        var rate = new ExchangeRate(
                "disclaimer", "license", 123, "base", rateMap
        );
        when(exchangeRatesFeignClient.takeRateByDateAndCode(anyString(), anyString())).thenReturn(rate);
        var result = exchangeRatesService.takeYesterdayRateByCode(code);
        assertNotNull(result);
        assertEquals(1.0, rate.getRates().get(code));
    }
}