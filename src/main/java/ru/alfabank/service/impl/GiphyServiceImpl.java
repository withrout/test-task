package ru.alfabank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alfabank.client.GiphyFeignClient;
import ru.alfabank.dto.GifDto;
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
    public GifDto takeGif(String code) {
        return getGiphy(code);
    }

    private GifDto getGiphy(String code) {
        var toDayRate = ratesService.takeRateByCode(code).getRate();
        var yesterdayRate = ratesService.takeYesterdayRateByCode(code).getRate();
        var gif = getGifByResultCompare(toDayRate, yesterdayRate);
        return GifDto.builder()
                .title(gif.getTitle())
                .url(gif.getUrls().get(new Random().nextInt(Integer.parseInt(limit))))
                .build();
    }

    private GiphyGif getGifByResultCompare(Double toDayRate, Double yesterdayRate) {
        var compareResult = Double.compare(toDayRate, yesterdayRate);
        if (compareResult > 0) {
            return feignClient.getSearchGifResult(rich);
        } else if (compareResult < 0) {
            return feignClient.getSearchGifResult(broke);
        } else {
            return feignClient.getSearchGifResult(zero);
        }
    }
}