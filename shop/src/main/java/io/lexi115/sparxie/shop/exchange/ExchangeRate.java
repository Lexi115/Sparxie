package io.lexi115.sparxie.shop.exchange;

import io.lexi115.sparxie.shop.game.GameCurrency;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExchangeRate {
    private final Map<String, Long> exchangeMap = new HashMap<>(Map.of(
            GameCurrency.STANDARD_TICKET.name(), 160L,
            GameCurrency.LIMITED_TICKET.name(), 160L
    ));

    public Long getCostInJades(final String itemId) {
        return exchangeMap.getOrDefault(itemId, null);
    }
}
