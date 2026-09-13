package io.lexi115.sparxie.shop.payments;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGateway {
    void pay(final UUID transactionId, final UUID playerId, final BigDecimal price);
}
