package io.lexi115.sparxie.shop.payments.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PaymentTransaction {
    private UUID transactionId;
    private UUID playerId;
    private Instant createdAt;
    private BigDecimal price;
}
