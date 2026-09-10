package io.lexi115.sparxie.shop.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonHelper {
    private final ObjectMapper objectMapper;

    public <T> Collection<T> parseCollection(final InputStream stream, Class<T> tClass) {
        var listType = objectMapper.getTypeFactory().constructCollectionType(List.class, tClass);
        return objectMapper.readValue(stream, listType);
    }
}
