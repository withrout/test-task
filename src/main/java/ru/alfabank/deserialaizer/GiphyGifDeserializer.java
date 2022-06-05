package ru.alfabank.deserialaizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.alfabank.model.GiphyGif;

import java.io.IOException;

public class GiphyGifDeserializer extends StdDeserializer<GiphyGif> {

    public GiphyGifDeserializer() {
        this(null);
    }

    protected GiphyGifDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GiphyGif deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var array = (JsonNode) p.getCodec().readTree(p).get("data").get(0);
        var url = array.get("images").get("original").get("webp").asText();
        var name = array.get("title").asText();
        return new GiphyGif(name, url);
    }
}
