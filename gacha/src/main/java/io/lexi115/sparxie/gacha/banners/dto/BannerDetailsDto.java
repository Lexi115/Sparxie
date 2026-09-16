package io.lexi115.sparxie.gacha.banners.dto;

import java.util.Map;

public record BannerDetailsDto(
        String id,
        String name,
        String type,
        String currency,
        Map<Integer, Long> costs
) {
}
