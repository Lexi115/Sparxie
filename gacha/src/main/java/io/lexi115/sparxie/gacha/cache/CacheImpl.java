package io.lexi115.sparxie.gacha.cache;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheImpl<T> implements Cache<T> {

    private final Map<String, CacheEntry<T>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void set(String key, T value) {
        var entry = new CacheEntry<>(value);
        cacheMap.put(key, entry);
    }

    @Override
    public void set(String key, T value, long millis) {
        var expirationDate = Instant.now().plusMillis(millis);
        var entry = new CacheEntry<>(value, expirationDate);
        cacheMap.put(key, entry);
    }

    @Override
    public T get(String key) {
        var entry = cacheMap.get(key);
        if (entry == null) {
            return null;
        }
        if (entry.isExpired()) {
            cacheMap.remove(key);
            return null;
        }
        return entry.getValue();
    }
}
