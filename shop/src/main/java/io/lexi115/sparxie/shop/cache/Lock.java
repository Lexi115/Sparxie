package io.lexi115.sparxie.shop.cache;

public interface Lock {
    boolean acquire(String lockName);

    void release(String lockName);
}
