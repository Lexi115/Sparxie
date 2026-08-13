package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.banner.dto.BannerItemDto;
import io.lexi115.sparxie.gacha.banner.dto.BannerPullResultDto;
import io.lexi115.sparxie.gacha.banner.dto.PulledBannerItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class BannerMapper {

    public Banner merge(final Banner template, final Banner edits) {
        if (template == null || edits == null) {
            return null;
        }
        var merged = template.clone();

        if (edits.getId() != null) {
            merged.setId(edits.getId());
        }
        if (edits.getName() != null) {
            merged.setName(edits.getName());
        }
        if (edits.getType() != null) {
            merged.setType(edits.getType());
        }
        if (edits.getCosts() != null) {
            var cost = Optional.ofNullable(merged.getCosts()).orElse(new HashMap<>());
            cost.putAll(edits.getCosts());
            merged.setCosts(cost);
        }
        if (edits.getWinPool() != null) {
            var winPool = Optional.ofNullable(merged.getWinPool()).orElse(new HashMap<>());
            edits.getWinPool().forEach((rarity, list)
                    -> winPool.put(rarity, new ArrayList<>(list)));
            merged.setWinPool(winPool);
        }
        if (edits.getLossPool() != null) {
            var lossPool = Optional.ofNullable(merged.getLossPool()).orElse(new HashMap<>());
            edits.getLossPool().forEach((rarity, list)
                    -> lossPool.put(rarity, new ArrayList<>(list)));
            merged.setLossPool(lossPool);
        }
        if (edits.getRarityRates() != null) {
            var rarityRates = Optional.ofNullable(merged.getRarityRates()).orElse(new HashMap<>());
            edits.getRarityRates().forEach((rarity, map)
                    -> rarityRates.put(rarity, new TreeMap<>(map)));
            merged.setRarityRates(rarityRates);
        }
        if (edits.getWinRates() != null) {
            var winRates = Optional.ofNullable(merged.getWinRates()).orElse(new HashMap<>());
            winRates.putAll(edits.getWinRates());
            merged.setWinRates(winRates);
        }

        return merged;
    }

    public BannerPullResultDto toDto(final BannerPullResult original) {
        var pulledItemsDtoList = original.items().stream().map(this::toDto).toList();
        return new BannerPullResultDto(original.bannerType().toString().toLowerCase(), pulledItemsDtoList);
    }

    public PulledBannerItemDto toDto(final PulledBannerItem original) {
        return new PulledBannerItemDto(toDto(original.item()), original.outcome().toString().toLowerCase());
    }

    public BannerItemDto toDto(final BannerItem original) {
        return new BannerItemDto(original.id(), original.rarity().toString().toLowerCase());
    }
}
