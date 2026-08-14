package io.lexi115.sparxie.game.banner.dto;

import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

@Builder
public record BannerDto(
        String id,
        String name,
        String type,
        String currency,
        Map<Integer, Integer> costs,
        Map<Integer, List<String>> winPool,
        Map<Integer, List<String>> lossPool,
        Map<Integer, NavigableMap<Integer, Double>> rarityRates,
        Map<Integer, Double> winRates
) {
}
