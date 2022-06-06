package ru.alfabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.dto.GifDto;
import ru.alfabank.service.GiphyService;

/**
 * REST получения GIF
 */
@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final GiphyService giphyService;

    /**
     * Получить GIF как результат изменения курса валюты
     * @param code код валюты
     * @return модель GIF
     */
    @GetMapping("/api/gifs/gif")
    ResponseEntity<GifDto> takeGifByCode(@RequestParam String code) {
        return ResponseEntity.ok(giphyService.takeGifByCurrencyCode(code));
    }
}
