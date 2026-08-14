package io.lexi115.sparxie.game.economy;

import org.springframework.stereotype.Service;

@Service
public class EconomyService {
    private final EconomyClient economyClient;

    public EconomyService(final EconomyClient economyClient) {
        this.economyClient = economyClient;
    }

    public void withdraw(final String playerId, final String currency, final int amount) {
        economyClient.withdraw(playerId, currency, amount);
    }
}
