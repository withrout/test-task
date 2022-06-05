package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.GiphyFeignClient;
import ru.alfabank.model.GiphyGif;
import ru.alfabank.service.ExchangeRatesService;
import ru.alfabank.service.GiphyService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GiphyServiceImpl implements GiphyService {

    @Value("${giphy.q.rich}")
    private String rich;

    @Value("${giphy.q.broke}")
    private String broke;

    @Value("${giphy.q.zero}")
    private String zero;

    @Value("${giphy.limit}")
    private String limit;

    private final ExchangeRatesService ratesService;
    private final GiphyFeignClient feignClient;

    @Override
    public GiphyGif takeGif(String code) {
        return getGiphy(code);
    }

    private GiphyGif getGiphy(String code) {
        var toDayRate = ratesService.takeRateByCode(code).getRates().get(code);
        var yesterdayRate = ratesService.takeYesterdayRateByCode(code).getRates().get(code);
        return getGifByResultCompare(toDayRate, yesterdayRate);
    }

    private GiphyGif getGifByResultCompare(Double toDayRate, Double yesterdayRate) {
        var compareResult = Double.compare(toDayRate, yesterdayRate);
        if (compareResult > 0) {
            return feignClient.getSearchGifResult(rich, limit);
        } else if (compareResult < 0) {
            return feignClient.getSearchGifResult(broke, limit);
        } else {
            return feignClient.getSearchGifResult(zero, limit);
        }
    }
}