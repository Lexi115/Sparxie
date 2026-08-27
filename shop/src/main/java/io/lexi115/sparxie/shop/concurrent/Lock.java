package io.lexi115.sparxie.shop.concurrent;

public interface Lock {
    boolean acquire(String lockName);

    void release(String lockName);
}
