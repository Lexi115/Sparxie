package io.lexi115.sparxie.game.banners.dto;

import java.util.Map;

public record BannerDetails(
        String id,
        String name,
        String type,
        String currency,
        Map<Integer, Long> costs
) {
    public Long getCost(final int pullAmount) {
        if (pullAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return this.costs.getOrDefault(pullAmount, costs.getOrDefault(1, 0L) * pullAmount);
    }
}
