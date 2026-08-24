package io.lexi115.sparxie.inventory.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class UuidHelper {
    public UUID generateNameUuid(final String input) {
        byte[] keyBytes = input.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(keyBytes);
    }
}
