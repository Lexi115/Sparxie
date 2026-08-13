package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.BannerCurrency;

public interface EconomyClient {
    int getBalance(String playerId, BannerCurrency currency);
    void setBalance(String playerId, BannerCurrency currency, final int amount);
    void deposit(String playerId, BannerCurrency currency, final int amount);
    void withdraw(String playerId, BannerCurrency currency, final int amount);
}
