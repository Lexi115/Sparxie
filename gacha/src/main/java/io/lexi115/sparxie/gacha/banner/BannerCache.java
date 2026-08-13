package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.cache.Cache;
import io.lexi115.sparxie.gacha.cache.CacheEntry;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BannerCache implements Cache<Banner> {

    private final Map<String, CacheEntry<Banner>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void set(String key, Banner value) {
        var entry = new CacheEntry<>(value);
        cacheMap.put(key, entry);
    }

    @Override
    public void set(String key, Banner value, long millis) {
        var expirationDate = Instant.now().plusMillis(millis);
        var entry = new CacheEntry<>(value, expirationDate);
        cacheMap.put(key, entry);
    }

    @Override
    public Banner get(String key) {
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
