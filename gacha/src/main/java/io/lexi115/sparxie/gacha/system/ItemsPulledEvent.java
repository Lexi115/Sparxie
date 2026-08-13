package io.lexi115.sparxie.gacha.system;

import java.util.List;

public record ItemsPulledEvent(String playerId, List<Long> itemIds) {
}
