package ru.alfabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.model.GiphyGif;
import ru.alfabank.service.GiphyService;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

//    private final ExchangeRatesService ratesService;
    private final GiphyService giphyService;

    @GetMapping("/api/gifs/gif")
    ResponseEntity<GiphyGif> getGif(@RequestParam String code) {
        return ResponseEntity.ok(giphyService.takeGif(code));
    }
}
