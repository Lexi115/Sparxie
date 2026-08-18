package io.lexi115.sparxie.game.economy;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EconomyService {
    private final EconomyClient economyClient;

    public EconomyService(final EconomyClient economyClient) {
        this.economyClient = economyClient;
    }

    public void withdraw(final UUID transactionId, final UUID playerId, final String currency, final int amount) {
        economyClient.withdraw(transactionId, playerId, currency, amount);
    }
}
