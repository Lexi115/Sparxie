package io.lexi115.sparxie.shop.payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGateway {
    void pay(final UUID transactionId, final UUID playerId, final BigDecimal price);
}
