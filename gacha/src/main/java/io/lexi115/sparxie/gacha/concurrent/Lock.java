package io.lexi115.sparxie.gacha.concurrent;

public interface Lock {
    boolean acquire(String lockName);

    void release(String lockName);
}
