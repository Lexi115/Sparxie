package io.lexi115.sparxie.gacha.cache;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CacheEntry<T> {
    private final Instant creationDate = Instant.now();
    private final Instant expirationDate;
    private T value;

    public CacheEntry(T value) {
        this(value, null);
    }

    public CacheEntry(T value, Instant expirationDate) {
        this.value = value;
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return expirationDate != null && Instant.now().isAfter(expirationDate);
    }
}
