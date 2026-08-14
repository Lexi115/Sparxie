package io.lexi115.sparxie.game.economy;

public interface EconomyClient {
    int getBalance(String playerId, String currency);

    void setBalance(String playerId, String currency, final int amount);

    void deposit(String playerId, String currency, final int amount);

    void withdraw(String playerId, String currency, final int amount);
}
