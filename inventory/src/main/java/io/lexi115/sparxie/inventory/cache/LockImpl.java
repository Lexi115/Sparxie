package io.lexi115.sparxie.inventory.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LockImpl implements Lock {

    private final Map<String, Boolean> lockMap = new HashMap<>();

    @Override
    public boolean acquire(final String lockName) {
        var previous = lockMap.get(lockName);
        if (previous == null) {
            lockMap.put(lockName, true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void release(final String lockName) {
        lockMap.remove(lockName);
    }
}
