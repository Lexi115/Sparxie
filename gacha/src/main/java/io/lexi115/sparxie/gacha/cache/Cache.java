package io.lexi115.sparxie.gacha.cache;

public interface Cache<T> {
    void set(String key, T value);
    void set(String key, T value, long millis);
    T get(String key);
}
