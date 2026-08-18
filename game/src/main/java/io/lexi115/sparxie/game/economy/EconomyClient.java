package io.lexi115.sparxie.game.economy;

import java.util.UUID;

public interface EconomyClient {
    int getBalance(UUID playerId, String currency);

    void setBalance(UUID playerId, String currency, int amount);

    void deposit(UUID playerId, String currency, int amount);

    void withdraw(UUID transactionId, UUID playerId, String currency, int amount);
}
