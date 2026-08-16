package io.lexi115.sparxie.gacha.cache;

public interface Lock {
    boolean acquire(String lockName);

    void release(String lockName);
}
