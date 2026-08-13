package io.lexi115.sparxie.gacha.banner.dto;

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
        Map<String, List<Long>> winPool,
        Map<String, List<Long>> lossPool,
        Map<String, NavigableMap<Integer, Double>> rarityRates,
        Map<String, Double> winRates
) {
}
