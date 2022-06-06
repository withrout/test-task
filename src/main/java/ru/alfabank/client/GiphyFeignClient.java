package ru.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.model.GiphyGif;

/**
 * Клиент для работы с API GIF
 */
@FeignClient(name = "giphy", url = "${giphy.urls.base}?api_key=${giphy.api.key}&limit=${giphy.limit}")
public interface GiphyFeignClient {
    /**
     * Найти GIF по ключевому слову
     * @param q ключевое слово
     * @return модель GIF
     */
    @GetMapping("${giphy.urls.search}")
    GiphyGif getSearchGifResult(@RequestParam String q);
}