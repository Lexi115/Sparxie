package io.lexi115.sparxie.gacha.cache;

import java.util.Optional;

public interface Cache<T> {
    void set(String key, T value);

    void set(String key, T value, long millis);

    Optional<T> get(String key);
}
