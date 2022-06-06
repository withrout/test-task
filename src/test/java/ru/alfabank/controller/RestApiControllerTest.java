package ru.alfabank.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.dto.GifDto;
import ru.alfabank.service.GiphyService;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RestApiController.class)
class RestApiControllerTest {

    @MockBean
    private GiphyService giphyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void takeGifByCode() throws Exception {
        var gif = GifDto.builder().title("title").url("url").build();
        when(giphyService.takeGifByCurrencyCode(anyString())).thenReturn(gif);
        mockMvc.perform(get("/api/gifs/gif")
                .param("code", "EUR")
                        .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.url").value("url"));
    }
}