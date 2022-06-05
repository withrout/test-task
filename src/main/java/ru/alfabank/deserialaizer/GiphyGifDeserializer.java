package ru.alfabank.deserialaizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.alfabank.model.GiphyGif;

import java.io.IOException;
import java.util.HashMap;

public class GiphyGifDeserializer extends StdDeserializer<GiphyGif> {

    public GiphyGifDeserializer() {
        this(null);
    }

    protected GiphyGifDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GiphyGif deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var array = (JsonNode) p.getCodec().readTree(p);
        var urlsGifMap = new HashMap<Integer, String>();
        var title = "";
        for (int i = 0; i < array.get("data").size(); i++) {
            var field = array.get("data").get(i);
            title = field.get("title").asText();
            urlsGifMap.put(i + 1, field.get("images").get("original").get("url").asText());
        }
        return new GiphyGif(title, urlsGifMap);
    }
}