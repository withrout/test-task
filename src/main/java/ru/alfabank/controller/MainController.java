package ru.alfabank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.service.ExchangeRatesService;
import ru.alfabank.service.GiphyService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ExchangeRatesService ratesService;
    private final GiphyService giphyService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("currencies", ratesService.takeAllCurrencies());
        return "index";
    }

    @GetMapping("/giphy")
    public String giphy(Model model, @RequestParam String code) {
        var gif = giphyService.takeGif(code);
        model.addAttribute("gif", gif);
        return "giphy";
    }
}