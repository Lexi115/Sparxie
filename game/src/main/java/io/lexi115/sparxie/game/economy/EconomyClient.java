package io.lexi115.sparxie.game.economy;

public interface EconomyClient {
    int getBalance(String playerId, String currency);

    void setBalance(String playerId, String currency, int amount);

    void deposit(String playerId, String currency, int amount);

    void withdraw(String transactionId, String playerId, String currency, int amount);
}
