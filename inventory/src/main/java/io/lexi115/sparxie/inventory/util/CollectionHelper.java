package io.lexi115.sparxie.inventory.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CollectionHelper {

    public <K, V> Map<K, V> subMap(final Map<K, V> original, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index cannot be greater than end index");
        }
        var subMap = new HashMap<K, V>();
        var it = original.entrySet().iterator();
        for (int i = 0; i <= endIndex && it.hasNext(); i++) {
            var entry = it.next();
            if (i >= startIndex) {
                subMap.put(entry.getKey(), entry.getValue());
            }
        }
        return subMap;
    }
}
