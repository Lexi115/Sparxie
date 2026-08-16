package io.lexi115.sparxie.game.economy;

import io.lexi115.sparxie.game.player.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EconomyClientImpl implements EconomyClient {

    private final Map<String, Map<String, Integer>> map = new HashMap<>(Map.of(
            "1bbad0cc-4a14-4c5e-8254-f851ffa30907", new HashMap<>(Map.of(
                    "LIMITED_TICKET", 999999999,
                    "STANDARD_TICKET", 2
            )))
    );

    private final Map<String, String> transactionsMap = new HashMap<>();

    @Override
    public int getBalance(String playerId, String currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        var playerEcon = map.get(playerId);
        if (playerEcon == null) {
            throw new PlayerNotFoundException();
        }
        return playerEcon.getOrDefault(currency, 0);
    }

    @Override
    public void setBalance(String playerId, String currency, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must not be negative");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null");
        }
        var playerEcon = map.computeIfAbsent(playerId, _ -> new HashMap<>());
        playerEcon.put(currency, amount);
    }

    @Override
    public void deposit(String playerId, String currency, int amount) {
        setBalance(playerId, currency, getBalance(playerId, currency) + amount);
    }

    @Override
    public void withdraw(String transactionId, String playerId, String currency, int amount) {
        if (transactionsMap.containsKey(transactionId)) {
            return;
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must not be negative");
        }
        var currentBalance = getBalance(playerId, currency);
        var newBalance = currentBalance - amount;
        System.out.println("cur: " + currentBalance + " | new: " + newBalance);
        if (newBalance < 0) {
            throw new InsufficientFundsException("Not enough currency");
        }
        setBalance(playerId, currency, newBalance);
        transactionsMap.put(transactionId, "done");
    }
}
