package io.lexi115.sparxie.inventory.concurrent;

public interface Lock {
    boolean acquire(String lockName);

    void release(String lockName);
}
