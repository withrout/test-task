package ru.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.model.GiphyGif;


@FeignClient(name = "giphy", url = "${giphy.urls.base}?api_key=${giphy.api.key}")
public interface GiphyFeignClient {
    @GetMapping("${giphy.urls.search}")
    GiphyGif getSearchGifResult(@RequestParam String q, @RequestParam String limit);
}