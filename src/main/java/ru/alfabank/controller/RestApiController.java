package ru.alfabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.dto.GifDto;
import ru.alfabank.service.GiphyService;

@RestController
@RequiredArgsConstructor
public class RestApiController {

//    private final ExchangeRatesService ratesService;
    private final GiphyService giphyService;

    @GetMapping("/api/gifs/gif")
    ResponseEntity<GifDto> getGif(@RequestParam String code) {
        return ResponseEntity.ok(giphyService.takeGif(code));
    }
}
