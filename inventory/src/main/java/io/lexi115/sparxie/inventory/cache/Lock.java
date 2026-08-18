package io.lexi115.sparxie.inventory.cache;

public interface Lock {
    boolean acquire(String lockName);

    void release(String lockName);
}
