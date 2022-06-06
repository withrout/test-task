package ru.alfabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.service.ExchangeRatesService;
import ru.alfabank.service.GiphyService;

/**
 * Контроллер для фронт представления
 */
@Controller
@RequiredArgsConstructor
public class WebController {

    private final ExchangeRatesService ratesService;
    private final GiphyService giphyService;

    /**
     * Отображение главной страницы с выбором курса для проверки
     * @param model модель представления
     * @return html шаблон
     */
    @RequestMapping
    public String index(Model model) {
        model.addAttribute("currencies", ratesService.takeAllCurrencies());
        return "index";
    }

    /**
     * Отображение страницы с найденным GIF
     * @param model модель представления
     * @param code код валюты
     * @return html шаблон
     */
    @GetMapping("/giphy")
    public String giphy(Model model, @RequestParam String code) {
        var gif = giphyService.takeGifByCurrencyCode(code);
        model.addAttribute("gif", gif);
        return "giphy";
    }
}