package io.lexi115.sparxie.shop.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ShopTransaction {
    private UUID transactionId;
    private UUID playerId;
    private Instant createdAt;
    private Map<String, Long> items;
}
