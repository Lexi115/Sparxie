package io.lexi115.sparxie.shop.concurrent;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisLock implements Lock {

    private final RedissonClient redissonClient;

    @Override
    public boolean acquire(final String lockName) {
        try {
            var lock = redissonClient.getLock(lockName);
            return lock.tryLock(0, TimeUnit.SECONDS);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void release(final String lockName) {
        var lock = redissonClient.getLock(lockName);
        lock.unlock();
    }
}
