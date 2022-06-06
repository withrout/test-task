package ru.alfabank.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alfabank.client.GiphyFeignClient;
import ru.alfabank.dto.RatesDto;
import ru.alfabank.model.GiphyGif;
import ru.alfabank.service.ExchangeRatesService;
import ru.alfabank.service.GiphyService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GiphyServiceImplTest {

    @MockBean
    GiphyFeignClient giphyFeignClient;

    @MockBean
    ExchangeRatesService exchangeRatesService;

    @Autowired
    GiphyService giphyService;

    @Test
    void takeGifByCodeTest() {
        var title = "title";
        var gif = new GiphyGif(title, Map.of(1, "url"));
        var todayRatesDto = RatesDto.builder().rate(2.0).build();
        var yesterdayRatesDto = RatesDto.builder().rate(1.0).build();
        when(giphyFeignClient.getSearchGifResult(anyString())).thenReturn(gif);
        when(exchangeRatesService.takeRateByCode(anyString())).thenReturn(todayRatesDto);
        when(exchangeRatesService.takeYesterdayRateByCode(anyString())).thenReturn(yesterdayRatesDto);
        var result = giphyService.takeGifByCurrencyCode("RUB");
        assertNotNull(result);
        assertEquals(title, result.getTitle());
    }
}