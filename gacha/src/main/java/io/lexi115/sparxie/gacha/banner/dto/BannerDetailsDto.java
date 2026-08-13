package io.lexi115.sparxie.gacha.banner.dto;

import java.util.Map;

public record BannerDetailsDto(
        String id,
        String name,
        String type,
        String currency,
        Map<Integer, Integer> costs
) {
    public int getCost(final int pullAmount) {
        if (pullAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return this.costs.getOrDefault(pullAmount, costs.getOrDefault(1, 0) * pullAmount);
    }
}
