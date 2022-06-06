package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.GiphyFeignClient;
import ru.alfabank.dto.GifDto;
import ru.alfabank.exceptions.GifServiceException;
import ru.alfabank.service.ExchangeRatesService;
import ru.alfabank.service.GiphyService;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GiphyServiceImpl implements GiphyService {

    private static final String GIPHY_ERROR = "Произошла ошибка при взаимодействии с сервисом гифов";

    @Value("#{${giphy.q}}")
    private Map<Integer, String> keys;

    @Value("${giphy.limit}")
    private String limit;

    private final GiphyFeignClient feignClient;
    private final ExchangeRatesService ratesService;

    @Override
    public GifDto takeGifByCurrencyCode(String code) {
        try {
            return getGiphy(code);
        } catch (GifServiceException e) {
            throw new GifServiceException(GIPHY_ERROR);
        }
    }

    private GifDto getGiphy(String code) {
        var toDayRate = ratesService.takeRateByCode(code).getRate();
        var yesterdayRate = ratesService.takeYesterdayRateByCode(code).getRate();
        var gif = feignClient.getSearchGifResult(keys.get(Double.compare(toDayRate, yesterdayRate)));
        return GifDto.builder()
                .title(gif.getTitle() != null ? gif.getTitle() : "")
                .url(gif.getUrls().get(new Random().nextInt(Integer.parseInt(limit))))
                .build();
    }
}