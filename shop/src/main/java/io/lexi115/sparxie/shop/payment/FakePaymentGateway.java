package io.lexi115.sparxie.shop.payment;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FakePaymentGateway implements PaymentGateway {

    private final Map<UUID, PaymentTransaction> paymentMap = new HashMap<>();

    @Override
    public void pay(UUID transactionId, UUID playerId, BigDecimal price) {
        var oldTransaction = paymentMap.get(transactionId);
        if (oldTransaction != null) {
            return;
        }
        var transaction = new PaymentTransaction(transactionId, playerId, Instant.now(), price);
        paymentMap.put(transactionId, transaction);
    }
}
